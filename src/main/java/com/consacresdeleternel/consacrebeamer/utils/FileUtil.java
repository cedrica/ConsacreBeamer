package com.consacresdeleternel.consacrebeamer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class FileUtil {

	private static final Logger LOG = Logger.getLogger(FileUtil.class);
	
	public static void createDirectory(String location) {
		Path path = Paths.get(location);
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			LOG.error("Directory konnte nicht angelegt werden");
		}
		LOG.info("Directory wurde erfolgreich angelegt");
	}

	public static String readTxtFileToString(String fileName) {
		try {
			String properties = System.getProperty("user.home").replace("\\", "/")+"/Documents/songs/" + fileName;
			Path path = Paths.get(properties);
			return new String(Files
					.readAllBytes(path));
		} catch (IOException e) {
			LOG.error("Datei " + fileName + " konnte nicht gelesen werden");
			e.printStackTrace();
		}
		return null;
	}

	public static boolean saveSongAsTxtFileToSongsFolder(String songText, String fileName) {
		createDirectory( System.getProperty("user.home")+"/Documents/songs");
		if (isFileNameCorrect(fileName)) {
			writeStringToFile(songText, System.getProperty("user.home").replace("\\", "/") + "/Documents/songs/" + fileName);
			return true;
		}
		return false;
	}

	public static void removeFile(String fileName) {
		try {
			File file = new File(System.getProperty("C:\\Users\\HP\\Documents").replace("\\", "/") + "/songs/" + fileName);
			if (file.delete()) {
				LOG.info(file.getName() + " is deleted!");
			} else {
				LOG.error("Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private static boolean isFileNameCorrect(String fileName) {
		return fileName.trim().endsWith(".txt");
	}

	public static void writeStringToFile(String content, String filename) {
		File file = new File(filename);
		FileOutputStream faos = null;
		try {
			faos = new FileOutputStream(file);
			faos.write(content.getBytes());

		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} finally {
			if (faos != null) {
				try {
					faos.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
