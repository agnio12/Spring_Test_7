package com.iu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//클래스 자체가 view(보여주는 것) 역할 & 다운로드를 실행하는 곳
public class FileDown extends AbstractView{ 
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File file = (File)model.get("down");
		response.setCharacterEncoding("UTF-8");
		response.setContentLength((int)file.length()); // Content의 길이 (파일 크기) / 리턴타입이 int이기때문에 long타입인 file.length() 를 형변환 해준다
		
		String fileName = URLEncoder.encode((String)model.get("oname"), "UTF-8"); //가져오는 파일이름을 인코딩 작업
		
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary"); //전송시 전송형태 지정 / 파일은 비트코드 형태로 전송
		
		FileInputStream fi = new FileInputStream(file); //파일을 읽어오는 것 
		OutputStream os = response.getOutputStream();
		
		FileCopyUtils.copy(fi, os);
		
		fi.close();
		os.close();
		
	}

}
