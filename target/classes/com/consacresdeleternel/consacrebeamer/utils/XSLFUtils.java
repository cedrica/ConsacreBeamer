package com.consacresdeleternel.consacrebeamer.utils;

import org.apache.poi.xslf.usermodel.TextAlign;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;

public class XSLFUtils {
	public static XSLFTextParagraph createParagraph(XSLFTextBox shape, String text, CustomFont font) {
		XSLFTextParagraph paragraph = shape.addNewTextParagraph();
		paragraph.setTextAlign(TextAlign.CENTER);
		XSLFTextRun textRun = paragraph.addNewTextRun();
		textRun.setText(text);
		textRun.setFontSize(font.getFontSize());
		textRun.setFontColor(font.getColor());
		textRun.setFontFamily(font.getFontFamily());
		return paragraph;
	}

}
