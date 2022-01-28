package com.teamAlpha.bookHub.communication.service;

import java.beans.Encoder;
import java.nio.file.Path;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class AttachmentServiceImpl implements AttachmentService {
	
	private Path path = null; 

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAttachment(MultipartFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public Resource loadSingleAttachment(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Path pathName() {
		
		//Base64Coder
		
		return null; 
	}

}
