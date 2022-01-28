package com.teamAlpha.bookHub.communication.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	public String pathFinder() {
		
		return null; 
	}
	
	public Boolean fileType(MultipartFile file) {
		
		
		if(file.getContentType().equals("jpg") || 
				file.getContentType().equals("png") || 
				file.getContentType().equalsIgnoreCase("jpeg")) {
			
			return true; 
		}
				
	
		return false; 
	}

}
