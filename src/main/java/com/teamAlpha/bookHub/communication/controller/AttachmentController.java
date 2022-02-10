package com.teamAlpha.bookHub.communication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.exception.AttachmentDetailNotFoundException;
import com.teamAlpha.bookHub.communication.model.AttachmentDto;
import com.teamAlpha.bookHub.communication.service.AttachmentService;

@RestController
@RequestMapping("api/v1/attachments")
public class AttachmentController {

	@Autowired
	AttachmentService attachmentService;

	@RequestMapping
	public String att() {
		return "This is attachment";
	}

	@PostMapping("/save")
	public ResponseEntity<AttachmentDto> saveFile(@RequestParam("file") MultipartFile file, Attachment attachment) {
		AttachmentDto savedAttachment = attachmentService.saveAttachment(file, attachment);
		return ResponseEntity.status(HttpStatus.OK).body(savedAttachment);
	}

	@GetMapping("/list")
	public ResponseEntity<List<AttachmentDto>> getAllAttachmentDetails() {
		try {
			List<AttachmentDto> attachments = attachmentService.getAllAttachmentDetails();
			return ResponseEntity.status(HttpStatus.OK).body(attachments);
		} catch (AttachmentDetailNotFoundException e) {
			throw new AttachmentDetailNotFoundException("No attachment details record found");
					
		}		
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<AttachmentDto> singleAttchmentDetail(@PathVariable("id") Integer attachmentId) {
		try {
			AttachmentDto attachment = attachmentService.singleAttachmentDetail(attachmentId);     
			return ResponseEntity.status(HttpStatus.OK).body(attachment);
		} catch (AttachmentDetailNotFoundException e) {
			throw new AttachmentDetailNotFoundException(attachmentId);
		}
        
    }

}
