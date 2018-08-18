package com.consacresdeleternel.consacrebeamer.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PPTService {
	private static final Logger LOG = LoggerFactory.getLogger(PPTService.class);
	private static Process process;

	public static XMLSlideShow generatePPT(String titleText, List<String> songTexts) {
		try {
			XMLSlideShow ppt = new XMLSlideShow();
			// getting the slide master object
			XSLFSlideMaster slideMaster = ppt.getSlideMasters().get(0);

			// select a layout from specified list
			XSLFSlideLayout slidelayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

			// ###########################################
			for (String text : songTexts) {
				// creating a slide with title and content layout
				XSLFSlide slide = ppt.createSlide(slidelayout);
				// selection of title place holder
				XSLFTextShape title = slide.getPlaceholder(0);
				// setting the title in it
				title.setText(titleText);

				// selection of body placeholder
				XSLFTextShape body = slide.getPlaceholder(1);

				// clear the existing text in the slide
				body.clearText();

				// adding new paragraph
				body.addNewTextParagraph().addNewTextRun().setText(text);
			}
			return ppt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File showPPT(XMLSlideShow slideShow, File createTempFile, int index) {
		if (createTempFile != null && slideShow.getSlides().size() > 0) {
			try {
				process.destroy();
				XSLFSlide slide = slideShow.getSlides().get(index);
				slideShow.setSlideOrder(slide, 0);
				FileOutputStream out = new FileOutputStream(createTempFile);
				slideShow.write(out);
				String cmd = "C:\\Program Files (x86)\\Microsoft Office\\Office14\\PPTVIEW.EXE /f "
						+ createTempFile.getAbsolutePath();
				process = Runtime.getRuntime().exec(cmd);
				out.close();
				return createTempFile;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			createTempFile = File.createTempFile("temp", ".pptx");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (FileOutputStream out = new FileOutputStream(createTempFile.getAbsolutePath())) {
			slideShow.write(out);
			String cmd = "C:\\Program Files (x86)\\Microsoft Office\\Office14\\PPTVIEW.EXE /f "
					+ createTempFile.getAbsolutePath();
			process = Runtime.getRuntime().exec(cmd);
			// LOG.info("Presentation wird gestartet. Process =
			// "+process.exitValue());
			return createTempFile;
		} catch (IOException e) {
			LOG.error("Folgender Fehler ist aufgetretten w�hrend der Anzeige");
			return null;
		}
	}
}
