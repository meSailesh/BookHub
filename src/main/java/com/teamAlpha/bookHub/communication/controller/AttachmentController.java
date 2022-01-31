package com.teamAlpha.bookHub.communication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.service.AttachmentService;

@RestController
@RequestMapping("api/v1/attachment")
public class AttachmentController {
	
	@Autowired
	AttachmentService attachmentService;
	
	@RequestMapping
	public String att() {
		return "This is attachment"; 
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file, Attachment attachment) {
		Attachment a = attachmentService.saveAttachment(file, attachment);
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body(a);
	}

}
