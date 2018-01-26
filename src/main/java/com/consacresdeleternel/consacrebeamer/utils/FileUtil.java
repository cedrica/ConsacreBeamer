package com.consacresdeleternel.consacrebeamer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class FileUtil {

	private static final Logger LOG = Logger.getLogger(FileUtil.class);

	public static String readTxtFileToString(String fileName) {
		try {
			return new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir").replace("\\", "/") + "/songs/" +fileName)));
		} catch (IOException e) {
			LOG.error("Datei " + fileName + " konnte nicht gelesen werden");
			e.printStackTrace();
		}
		return null;
	}

	public static boolean saveSongAsTxtFileToSongsFolder(String songText, String fileName) {
		if (isFileNameCorrect(fileName)) {
			writeStringToFile(songText, System.getProperty("user.dir").replace("\\", "/") + "/songs/" + fileName);
			return true;
		}
		return false;
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
