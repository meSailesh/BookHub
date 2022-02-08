package com.teamAlpha.bookHub.communication.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.teamAlpha.bookHub.communication.model.AttachmentStorageProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.teamAlpha.bookHub.communication.controller.AttachmentController;
import com.teamAlpha.bookHub.communication.entity.Attachment;
import com.teamAlpha.bookHub.communication.exception.AttachmentDetailNotFoundException;
import com.teamAlpha.bookHub.communication.model.AttachmentDto;
import com.teamAlpha.bookHub.communication.repository.AttachmentRepository;
import com.teamAlpha.bookHub.communication.utils.FileUtils;

import javax.servlet.http.HttpServletResponse;

@Service
public class AttachmentService {
	private final static String rootPath = new AttachmentStorageProperties().getPATH();

	private Path path = null;

	@Autowired
	AttachmentRepository attachmentRepository;

	
	public void init() {
		// TODO Auto-generated method stub

		try {
			Files.createDirectories(path);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
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
					.add(linkTo(methodOn(AttachmentController.class).getAllAttachmentDetails()).withRel("list"));
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

	
	public List<AttachmentDto> getAllAttachmentDetails() {
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
			attachmentDto
					.add(linkTo(methodOn(AttachmentController.class).getAllAttachmentDetails()).withRel("list"));

			return attachmentDto;

		} catch (Exception e) {
			throw new AttachmentDetailNotFoundException(attachmentId);
		}
	}

	public void downloadAttachment (HttpServletResponse response , Integer attachmentId)throws AttachmentDetailNotFoundException{

		try{
			AttachmentDto attachmentDto = new AttachmentDto();
			Attachment attachment = attachmentRepository.findById(attachmentId).get();
			BeanUtils.copyProperties( attachment,attachmentDto);

			String path = rootPath.concat("/" + attachment.getAttachmentTypeId() + "/" + attachment.getFileHash());

			File file = new File(path);
			String[] fileList = file.list();
			File imagePath = new File(path.concat("/"+fileList[0]));


			String mimeType = URLConnection.guessContentTypeFromName(file.getName());

			response.setContentType(mimeType);

			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + imagePath.getName() + "\""));
			response.setContentLength((int) imagePath.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(imagePath));
			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}catch (Exception e){
			throw new AttachmentDetailNotFoundException(attachmentId);
		}


	}



}
