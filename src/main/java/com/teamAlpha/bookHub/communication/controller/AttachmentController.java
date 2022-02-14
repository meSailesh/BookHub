package com.teamAlpha.bookHub.communication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.exception.AttachmentDetailNotFoundException;
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
