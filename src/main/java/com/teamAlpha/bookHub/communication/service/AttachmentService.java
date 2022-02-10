package com.teamAlpha.bookHub.communication.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.controller.AttachmentController;
import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.exception.AttachmentDetailNotFoundException;
import com.teamAlpha.bookHub.communication.exception.InvalidAttachmentTypeException;
import com.teamAlpha.bookHub.communication.model.AttachmentDto;
import com.teamAlpha.bookHub.communication.repository.AttachmentRepository;
import com.teamAlpha.bookHub.communication.utils.FileUtils;

@Service
public class AttachmentService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AttachmentService.class);

	private Path path = null;

	@Autowired
	AttachmentRepository attachmentRepository;

	public AttachmentDto saveAttachment(MultipartFile file, Attachment attachment) throws InvalidAttachmentTypeException {
		try {
			LOGGER.info("Create new attachment with details");
			path = FileUtils.pathFinders(file, attachment.getAttachmentTypeId());
			Files.createDirectories(path);
			attachment.setFileHash(FileUtils.fileHash(file).toString());
			Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));

			AttachmentDto attachmentDto = new AttachmentDto();
			Attachment savedAttachment = attachmentRepository.save(attachment);
			LOGGER.info("Attachment with details saved success");
			BeanUtils.copyProperties(savedAttachment, attachmentDto);

			attachmentDto.add(linkTo(methodOn(AttachmentController.class).getAllAttachmentDetails()).withRel("list"));
			attachmentDto.add(linkTo(
					methodOn(AttachmentController.class).singleAttchmentDetail(savedAttachment.getAttachmentId()))
							.withSelfRel());

			return attachmentDto;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.info("Invalid attachment type uploaded.");
			throw new InvalidAttachmentTypeException("Select proper image file type. Try again!");
		}
	}

	public List<AttachmentDto> getAllAttachmentDetails() throws AttachmentDetailNotFoundException {
		List<AttachmentDto> attachmentDtoList = new ArrayList<>();
		List<Attachment> attachments = attachmentRepository.findAll();
		BeanUtils.copyProperties(attachments, attachmentDtoList);

		return attachmentDtoList;
	}

	public AttachmentDto singleAttachmentDetail(Integer attachmentId) throws AttachmentDetailNotFoundException {
		try {

			AttachmentDto attachmentDto = new AttachmentDto();
			Attachment attachment = attachmentRepository.findById(attachmentId).get();
			BeanUtils.copyProperties(attachmentDto, attachment);
			attachmentDto.add(linkTo(methodOn(AttachmentController.class).getAllAttachmentDetails()).withRel("list"));

			return attachmentDto;

		} catch (Exception e) {
			throw new AttachmentDetailNotFoundException(attachmentId);
		}
	}

}
