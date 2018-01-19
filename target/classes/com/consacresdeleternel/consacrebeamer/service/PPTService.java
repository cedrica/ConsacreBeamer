package com.consacresdeleternel.consacrebeamer.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class PPTService {
	private static final Logger LOG = Logger.getLogger(PPTService.class);

	public static XMLSlideShow generatePPT(String titleText, String songText) {
		try {
			XMLSlideShow ppt = new XMLSlideShow();
			// getting the slide master object
			XSLFSlideMaster slideMaster = ppt.getSlideMasters()[0];

			// select a layout from specified list
			XSLFSlideLayout slidelayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

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
			body.addNewTextParagraph().addNewTextRun().setText(songText);

			
			XSLFSlide slide2 = ppt.createSlide(slidelayout);
			// selection of body placeholder
			XSLFTextShape body2 = slide2.getPlaceholder(1);

			// clear the existing text in the slide
			body2.clearText();

			// adding new paragraph
			body2.addNewTextParagraph().addNewTextRun().setText(songText);

			return ppt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void showPPT(XMLSlideShow slideShow) {

		File createTempFile = null;
		try {
			createTempFile = File.createTempFile("temp", ".pptx");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try (FileOutputStream out = new FileOutputStream(createTempFile.getAbsolutePath())) {
			slideShow.write(out);
			String cmd = "C:\\Program Files (x86)\\Microsoft Office\\Office14\\PPTVIEW.EXE "
					+ createTempFile.getAbsolutePath();
			Runtime.getRuntime().exec(cmd);
			LOG.info("Presentation wird gestartet");
		} catch (IOException e) {
			LOG.error("Folgender Fehler ist aufgetretten während der Anzeige");
		}
	}
}
