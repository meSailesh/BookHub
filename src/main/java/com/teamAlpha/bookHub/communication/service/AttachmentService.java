package com.teamAlpha.bookHub.communication.service;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
	
	public void init();
	
	public void saveAttachment(MultipartFile file);
	
	public Resource loadSingleAttachment(String fileName);

}
