package com.iu.util;

import java.io.File;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSaver {
	
	//FileCopyUtil
	public String save(MultipartFile f, String filePath) throws Exception{
		String fileName = f.getOriginalFilename();
		fileName = fileName.substring(fileName.indexOf("."));
		fileName = UUID.randomUUID().toString()+fileName;
		
		File file = new File(filePath, fileName);
		
		FileCopyUtils.copy(f.getBytes(), file);
		return fileName;
	}

}
