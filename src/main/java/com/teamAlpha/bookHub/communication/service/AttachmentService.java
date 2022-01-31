package com.teamAlpha.bookHub.communication.service;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.entity.Attachment;

public interface AttachmentService {
	
	public void init();
	
	public Attachment saveAttachment(MultipartFile file, Attachment attachment);
	
	public Resource loadSingleAttachment(String fileName);

	
}
