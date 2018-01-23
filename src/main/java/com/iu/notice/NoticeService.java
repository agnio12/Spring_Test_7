package com.iu.notice;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;
import com.iu.util.ListData;
import com.iu.util.PageMaker;

@Service
public class NoticeService implements BoardService {
	
	@Inject
	private NoticeDAO noticeDAO;
	@Inject
	private FileDAO fileDAO;

	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount = noticeDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return noticeDAO.selectList(listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return noticeDAO.selectOne(num);
	}

	@Override
	public int insert(BoardDTO boardDTO, MultipartFile[] file, HttpSession session) throws Exception {
		/*Mapper에서  selectKey 해준 num이  boardDTO의 num의 값으로 들어가 있다 
		   그 값을 밑에 fileDTO.setNum(boardDTO.getNum()); 해줘서 파일의 num에 현재 글번호를 넣어주는 것
	       그러면 0 이 아닌 현재 글 번호를 가져올 수 있다
	       파일 컬럼에 num은 현재글의 번호를 가져와야 하기 때문에 먼저 notice를 insert 해준후 파일을insert 해줘야 한다*/
		noticeDAO.insert(boardDTO);
		
		FileSaver fs = new FileSaver();
		String filePath = session.getServletContext().getRealPath("resources/upload");
		System.out.println(filePath);
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		List<String> names = fs.saver(file, filePath);
		for (int i=0; i<names.size(); i++) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFname(names.get(i));
			fileDTO.setOname(file[i].getOriginalFilename());
			fileDTO.setNum(boardDTO.getNum()); //현재 글번호
			fileDAO.insert(fileDTO);
		}
		return 1;
	}

	@Override
	public int update(BoardDTO boardDTO, MultipartFile[] file, HttpSession session) throws Exception {
		FileSaver fs = new FileSaver();
		String filePath = session.getServletContext().getRealPath("resources/upload");
		System.out.println(filePath);
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		List<String> names = fs.saver(file, filePath);
		for (int i=0; i<names.size(); i++) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFname(names.get(i));
			fileDTO.setOname(file[i].getOriginalFilename());
			fileDTO.setNum(boardDTO.getNum()); //현재 글번호
			fileDAO.insert(fileDTO);
		}
		int result = noticeDAO.update(boardDTO);
		return result;
	}

	@Override
	public int delete(int num, HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		List<FileDTO> ar =  fileDAO.selectList(num);
		int result = noticeDAO.delete(num);
		int f_del = fileDAO.delete(num);
		for (FileDTO fileDTO : ar) {
			try {
				File file = new File(filePath, fileDTO.getFname()); 
				file.delete(); // 지우는 작업을 하는 도중에 에러가 발생하면 넘어가고 그대로 for문을 실행 할수 있도록 해주기 위해 try문 작성
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
