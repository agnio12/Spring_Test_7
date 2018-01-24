package com.iu.member;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.util.FileSaver;

@Service
public class MemberService {

	@Inject
	private MemberDAO memberDAO;

	//Join
	public int memberJoin(MemberDTO memberDTO, MultipartFile file, HttpSession session) throws Exception{
		String filePath = session.getServletContext().getRealPath("resources/upload");
		FileSaver fs = new FileSaver();
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		String name = fs.saver(file, filePath);
		memberDTO.setFname(name);
		memberDTO.setOname(file.getOriginalFilename());
		int result = memberDAO.memberJoin(memberDTO);
		return result;
	}

	//ID Check
	public MemberDTO memberIdCheck(String id) throws Exception{
		return memberDAO.memberIdCheck(id);
	}

	//Login
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception{
		return memberDAO.memberLogin(memberDTO);
	}

	//MyPage
	public MemberDTO memberMyPage(String id) throws Exception{
		return memberDAO.memberMyPage(id);
	}

	//Update
	public int memberUpdate(MemberDTO memberDTO)throws Exception{
		return memberDAO.memberUpdate(memberDTO);
	}
	
	//Delete
	public int memberDelete(MemberDTO memberDTO, HttpSession session)throws Exception{
		String filePath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(filePath, memberDTO.getFname());
		f.delete();
		return memberDAO.memberDelete(memberDTO);
	}


}
