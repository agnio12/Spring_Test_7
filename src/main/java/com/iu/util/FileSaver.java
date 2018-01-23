package com.iu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSaver {
	
	//파일을 하나 올릴때 사용
	public String saver(MultipartFile file, String filePath) throws Exception{
		String fileName = file.getOriginalFilename(); //원본 파일명
		fileName = fileName.substring(fileName.indexOf(".")); //원본 파일명에서 확장자만 빼내기
		fileName = UUID.randomUUID().toString()+fileName; //빼낸 확장자 앞에 이상한 문자열 더하기
		/* 확장자만 빼내는 과정을 제외 시키고 바로 이상한 문자열 + _ + 파일명 을 해도 가능하다 (이상한 문자열 _ iu.jpg)
		String fileName = file.getOriginalFilename();
		fileName = UUID.randomUUID().toString()+"_"+fileName; */
		
		File f = new File(filePath, fileName);
		
		//FileCopyUtil 방식
		try {
			FileCopyUtils.copy(file.getBytes(), f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//file.transferTo(f); → Transfer 방식
		
		return fileName;
	}
	
	//올리는 파일이 다수일 경우 위에 메서드를 호출하여 for문을 돌려준다 / 여기에 파일명을 저장시켜주는 코드를 작성후 for문을 돌려도 가능
	public List<String> saver(MultipartFile [] file, String filePath) throws Exception{
		List<String> fileNames = new ArrayList<>();
		for (MultipartFile f : file) {
			String fileName = this.saver(f, filePath);
			fileNames.add(fileName);
		}
		return fileNames;
	}

}
