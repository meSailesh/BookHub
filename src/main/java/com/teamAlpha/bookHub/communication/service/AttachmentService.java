package com.teamAlpha.bookHub.communication.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.exception.AttachmentDetailNotFoundException;
import com.teamAlpha.bookHub.communication.model.AttachmentDto;

public interface AttachmentService {
	
	public void init();
	
	public AttachmentDto saveAttachment(MultipartFile file, Attachment attachment);
	
	
	public List<AttachmentDto> getAllAttachmentDetails();
	
	public AttachmentDto singleAttachmentDetail(Integer attachmentId) throws AttachmentDetailNotFoundException;

	
}
