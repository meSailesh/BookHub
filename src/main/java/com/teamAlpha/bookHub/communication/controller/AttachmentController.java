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

import com.teamAlpha.bookHub.catalog.entity.ProductCategory;
import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.model.AttachmentDto;
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
	public ResponseEntity<AttachmentDto> saveFile(@RequestParam("file") MultipartFile file, Attachment attachment) {
		AttachmentDto savedAttachment = attachmentService.saveAttachment(file, attachment);
		return ResponseEntity.status(HttpStatus.OK).body(savedAttachment);
	}

	@GetMapping("/viewAll")
	public ResponseEntity<List<AttachmentDto>> getAllAttachmentDetails() {
		List<AttachmentDto> attachments = attachmentService.getAllAttachmentDetails();

		if (attachments.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(attachments);
	}
	
	@GetMapping("/view/{id}")
    public ResponseEntity<AttachmentDto> singleAttchmentDetail(@PathVariable("id") Integer attachmentId) {
        AttachmentDto attachment = attachmentService.singleAttachmentDetail(attachmentId);     
		return ResponseEntity.status(HttpStatus.OK).body(attachment);
    }

}
