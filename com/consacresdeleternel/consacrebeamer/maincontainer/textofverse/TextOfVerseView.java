package com.consacresdeleternel.consacrebeamer.maincontainer.textofverse;

import java.io.StringWriter;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.google.gson.Gson;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class TextOfVerseView implements Initializable{

	@FXML WebView webViewChapterText;
	@FXML TextOfVerseViewModel rootNode;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.selectedVerseProperty().addListener((obs, oldVal, selectedVerse) -> {
			String template = createTemplate(rootNode.getSelectedChapter(),selectedVerse);
	        webViewChapterText.getEngine().loadContent(template);
		});
	}

	private String createTemplate(Chapter selectedChapter, Verse selectedVerse) {
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        String path = getClass().getResource("").getPath();
        props.put("file.resource.loader.path", path);
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.setProperty(RuntimeConstants.VM_PERM_ALLOW_INLINE_REPLACE_GLOBAL, true); 
        Template t = velocityEngine.getTemplate("selected-vesre.vm");

        VelocityContext context = new VelocityContext();
        context.put("selectedChapter", selectedChapter);

        Gson gson = new Gson();
        String selectedVerseJson = gson.toJson(selectedVerse);
        String selectedChapterJson = gson.toJson(selectedChapter);
        
        context.put("selectedChapterJson", selectedChapterJson);
        context.put("selectedVerseJson", selectedVerseJson);

        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        return writer.toString();
    }

}
