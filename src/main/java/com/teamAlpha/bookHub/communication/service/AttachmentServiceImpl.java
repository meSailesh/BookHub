package com.teamAlpha.bookHub.communication.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.controller.AttachmentController;
import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.exception.AttachmentDetailNotFoundException;
import com.teamAlpha.bookHub.communication.model.AttachmentDto;
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
	public AttachmentDto saveAttachment(MultipartFile file, Attachment attachment) {
		try {
			path = FileUtils.pathFinders(file, attachment.getAttachmentTypeId());
			Files.createDirectories(path);
			attachment.setFileHash(FileUtils.fileHash(file).toString());
			Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));

			AttachmentDto attachmentDto = new AttachmentDto();
			Attachment savedAttachment = attachmentRepository.save(attachment);

			BeanUtils.copyProperties(savedAttachment, attachmentDto);

			attachmentDto
					.add(linkTo(methodOn(AttachmentController.class).getAllAttachmentDetails()).withRel("viewAll"));
			attachmentDto.add(
					linkTo(methodOn(AttachmentController.class).singleAttchmentDetail(savedAttachment.getAttachmentId()))
							.withSelfRel());

			return attachmentDto;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<AttachmentDto> getAllAttachmentDetails() {
		List<AttachmentDto> attachmentDtoList = new ArrayList<>();
		List<Attachment> attachments = attachmentRepository.findAll();
		BeanUtils.copyProperties(attachments, attachmentDtoList);

		return attachmentDtoList;
	}

	@Override
	public AttachmentDto singleAttachmentDetail(Integer attachmentId) throws AttachmentDetailNotFoundException {
		try {

			AttachmentDto attachmentDto = new AttachmentDto();
			Attachment attachment = attachmentRepository.findById(attachmentId).get();
			BeanUtils.copyProperties(attachmentDto, attachment);
			attachmentDto
					.add(linkTo(methodOn(AttachmentController.class).getAllAttachmentDetails()).withRel("viewAll"));

			return attachmentDto;

		} catch (Exception e) {
			throw new AttachmentDetailNotFoundException(attachmentId);
		}
	}

}
