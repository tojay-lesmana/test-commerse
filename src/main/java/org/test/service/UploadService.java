package org.test.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	
	@Value("${file.summary.dir}")
	private String uploadConfig;
	
	public String uploadImage(MultipartFile upload, boolean createThumbnail) throws IOException{
		return uploadImage(upload.getOriginalFilename(), upload, createThumbnail);
	}
	
	public String uploadImage(String name, MultipartFile upload, boolean createThumbnail) throws IOException{
		if(upload.getContentType() != null){
			String dirString = uploadConfig;
			File dir = new File(dirString);
			if(!dir.exists()){
				dir.mkdirs();
			}			
			Files.copy(upload.getInputStream(), Paths.get(dirString, upload.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);			
	        return upload.getOriginalFilename();
		}else{
			return null;
		}
	}	
	
}
