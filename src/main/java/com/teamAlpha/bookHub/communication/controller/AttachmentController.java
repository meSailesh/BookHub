package com.teamAlpha.bookHub.communication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.model.AttachmentDto;
import com.teamAlpha.bookHub.communication.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

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
		List<AttachmentDto> attachments = attachmentService.getAllAttachmentDetails();

		if (attachments.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(attachments);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<AttachmentDto> singleAttchmentDetail(@PathVariable("id") Integer attachmentId) {
        AttachmentDto attachment = attachmentService.singleAttachmentDetail(attachmentId);     
		return ResponseEntity.status(HttpStatus.OK).body(attachment);
    }

	@GetMapping("/{id}/download")
	public void downloadAttachment(HttpServletResponse response, @PathVariable("id") Integer attachmentId){
		attachmentService.downloadAttachment(response, attachmentId);
//		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}/delete")
	public void deleteAttachment(@PathVariable("id") Integer attachmentId){
		attachmentService.deleteAttachment(attachmentId);
	}

}
