package com.consacresdeleternel.consacrebeamer.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consacresdeleternel.consacrebeamer.consts.BibelFilesConst;
import com.consacresdeleternel.consacrebeamer.enums.Language;

public class FileUtil {

	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
	
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
			File file = new File(System.getProperty("user.home").replace("\\", "/") + "/Documents/songs/" + fileName);
			if (file.exists() && file.delete()) {
				LOG.info(file.getName() + " is deleted!");
			} else {
				LOG.error("Delete operation is failed. File does not exist");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private static boolean isFileNameCorrect(String fileName) {
		return fileName.trim().endsWith(".txt");
	}
	
	public static List<String> readLines(String pathToFile) throws IOException{
		List<String> lines = new ArrayList();
		InputStream is = FileUtil.class.getResourceAsStream(pathToFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		while((line = br.readLine()) != null) {
			lines.add(line);
		}
		return lines;
	}

	public static void writeStringToFile(String content, String filename) {
		File file = new File(filename);
		FileOutputStream faos = null;
		try {
			faos = new FileOutputStream(file);
			byte[] byteContent = (content != null)? content.getBytes(): "".getBytes();
			faos.write(byteContent);

		} catch (Exception e) {
			LOG.error(e.getMessage());
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
	
	
	public static File[] loadXmlBibelFiles(Language language) {
	    File file = new File(System.getProperty("user.dir").replace("\\", "/")  + BibelFilesConst.XML_BIBLE_LOCATION + language.name().toLowerCase());
	    System.out.println(file);
    	File[] files = file.listFiles();
	    return files;
	}
	
	
	
}
