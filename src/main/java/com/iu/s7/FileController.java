package com.iu.s7;

import java.io.File;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.file.SeFileDTO;

@Controller
@RequestMapping(value="/file/**")
public class FileController {
	
	@Inject
	private FileDAO fileDAO;
	
	@RequestMapping(value="seUpload", method=RequestMethod.POST)
	public String seUpload(SeFileDTO seFileDTO, HttpSession session) throws Exception{
		String file_result = "";
		String callback = seFileDTO.getCallback();
		String callback_func = seFileDTO.getCallback_func();
		
		if(seFileDTO.getFiledata() != null && seFileDTO.getFiledata().getOriginalFilename() !=null && !seFileDTO.getFiledata().getOriginalFilename().equals("")){
			//하디디스크에 저장 순서
			//1. 파일명 지정
			String fileName = seFileDTO.getFiledata().getOriginalFilename(); //원본 파일명 
			fileName = fileName.substring(fileName.lastIndexOf(".")); //확장자 자르기
			fileName = UUID.randomUUID().toString()+fileName; //자른 확장자 앞에 이상한 문자열 붙이기 (중복 방지)

			//2. 저장경로
			String filepath = session.getServletContext().getRealPath("resources/upload");
			File f = new File(filepath);
			if(!f.exists()){
				f.mkdirs();
			}
			
			//3. 파일 저장
			f = new File(filepath, fileName);
			seFileDTO.getFiledata().transferTo(f);
			
			file_result += "&bNewLine=true&sFileName="+seFileDTO.getFiledata().getOriginalFilename();
			file_result += "&sFileURL=/s7/resources/upload/"+fileName;
		}else{
			file_result += "&errstr=error";
		}
		return "redirect:"+callback+"?callback_func="+callback_func+file_result;
	}

	@RequestMapping(value="fileDown")
	public ModelAndView fileDown(FileDTO fileDTO, HttpSession session) throws Exception{
		String filePath = session.getServletContext().getRealPath("resources/upload");
		File file = new File(filePath, fileDTO.getFname());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("down", file);
		mv.addObject("oname", fileDTO.getOname());
		mv.setViewName("fileDown");
		return mv;
	}
	
	@RequestMapping(value="fileDelete")
	public String delete(FileDTO fileDTO, HttpSession session, Model model) throws Exception{
		String filePath = session.getServletContext().getRealPath("resources/upload");
		int result =  fileDAO.deleteFnum(fileDTO.getFnum());
		if(result > 0){
			File file = new File(filePath, fileDTO.getFname());
			if(file.delete()){
				result = 1;
			}else{
				result = 0;
			}
		}
		model.addAttribute("result", result);
		return "common/fileResult";
	}

}
