package com.teamAlpha.bookHub.communication.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.repository.AttachmentRepository;
import com.teamAlpha.bookHub.communication.utils.FileUtils;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	private Path path = null;

	@Autowired
	AttachmentRepository attachmentRepository;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub

		try {
			Files.createDirectories(path);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Attachment saveAttachment(MultipartFile file, Attachment attachment) {
		// TODO Auto-generated method stub
		
		
		try {
			path = FileUtils.pathFinders(file, attachment.getAttachmentTypeId());
			Files.createDirectories(path);
			attachment.setFileHash(FileUtils.fileHash(file).toString());
			Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
			System.out.println(attachment.getAttachmentTypeId());
			return attachmentRepository.save(attachment);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 

	}

	@Override
	public Resource loadSingleAttachment(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	private Path pathName() {

		// Base64Coder

		return null;
	}

	

	

}
