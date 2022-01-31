package com.teamAlpha.bookHub.communication.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import com.teamAlpha.bookHub.communication.model.AttachmentStorageProperties;

public class FileUtils {

	private final static String rootPath = new AttachmentStorageProperties().getPATH();

	public static Path pathFinders(MultipartFile file, Integer key) throws Exception {

		if (fileTypeValidation(file)) {

			String path = rootPath.concat("/" + key + "/" + fileHash(file));

			Path getPath = Paths.get(path);

			return getPath;
		}
		return null;

	}

	public static UUID fileHash(MultipartFile file) {
		return UUID.nameUUIDFromBytes(file.getOriginalFilename().getBytes());
	}

	private static Boolean fileTypeValidation(MultipartFile file) throws Exception {

		String[] type = file.getContentType().split("/");

		if (type[1].equalsIgnoreCase("jpg") || type[1].equalsIgnoreCase("png") || type[1].equalsIgnoreCase("jpeg")) {
			return true;
		}
		return false;
	}

}
