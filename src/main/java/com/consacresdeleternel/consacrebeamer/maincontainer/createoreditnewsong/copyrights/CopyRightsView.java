package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.copyrights;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.BorderPane;

public class CopyRightsView extends BorderPane {
	private StringProperty tempo = new SimpleStringProperty();
	private StringProperty key = new SimpleStringProperty();
	private StringProperty additionalInfo = new SimpleStringProperty();
	private StringProperty songBuchNr = new SimpleStringProperty();
	private StringProperty bibleReferenz = new SimpleStringProperty();
	private StringProperty cCLiNr = new SimpleStringProperty();
	private StringProperty rights = new SimpleStringProperty();
	private StringProperty nationalCopyr = new SimpleStringProperty();
	private StringProperty copyright = new SimpleStringProperty();
	private StringProperty translation = new SimpleStringProperty();
	private StringProperty musik = new SimpleStringProperty();
	private StringProperty textAutor = new SimpleStringProperty();
	private StringProperty originalTitle = new SimpleStringProperty();
	private StringProperty title = new SimpleStringProperty();
	private BooleanProperty  invalid = new SimpleBooleanProperty();
	public  CopyRightsView(){
		Helper.load(this, Localization.getDefault());
	}

	public final StringProperty tempoProperty() {
		return this.tempo;
	}
	

	public final String getTempo() {
		return this.tempoProperty().get();
	}
	

	public final void setTempo(final String tempo) {
		this.tempoProperty().set(tempo);
	}
	

	public final StringProperty keyProperty() {
		return this.key;
	}
	

	public final String getKey() {
		return this.keyProperty().get();
	}
	

	public final void setKey(final String key) {
		this.keyProperty().set(key);
	}
	

	public final StringProperty additionalInfoProperty() {
		return this.additionalInfo;
	}
	

	public final String getAdditionalInfo() {
		return this.additionalInfoProperty().get();
	}
	

	public final void setAdditionalInfo(final String additionalInfo) {
		this.additionalInfoProperty().set(additionalInfo);
	}
	

	public final StringProperty songBuchNrProperty() {
		return this.songBuchNr;
	}
	

	public final String getSongBuchNr() {
		return this.songBuchNrProperty().get();
	}
	

	public final void setSongBuchNr(final String songBuchNr) {
		this.songBuchNrProperty().set(songBuchNr);
	}
	

	public final StringProperty bibleReferenzProperty() {
		return this.bibleReferenz;
	}
	

	public final String getBibleReferenz() {
		return this.bibleReferenzProperty().get();
	}
	

	public final void setBibleReferenz(final String bibleReferenz) {
		this.bibleReferenzProperty().set(bibleReferenz);
	}
	

	public final StringProperty cCLiNrProperty() {
		return this.cCLiNr;
	}
	

	public final String getCCLiNr() {
		return this.cCLiNrProperty().get();
	}
	

	public final void setCCLiNr(final String cCLiNr) {
		this.cCLiNrProperty().set(cCLiNr);
	}
	

	public final StringProperty rightsProperty() {
		return this.rights;
	}
	

	public final String getRights() {
		return this.rightsProperty().get();
	}
	

	public final void setRight(final String rights) {
		this.rightsProperty().set(rights);
	}
	

	public final StringProperty nationalCopyrProperty() {
		return this.nationalCopyr;
	}
	

	public final String getNationalCopyr() {
		return this.nationalCopyrProperty().get();
	}
	

	public final void setNationalCopyr(final String nationalCopyr) {
		this.nationalCopyrProperty().set(nationalCopyr);
	}
	

	public final StringProperty copyrightProperty() {
		return this.copyright;
	}
	

	public final String getCopyright() {
		return this.copyrightProperty().get();
	}
	

	public final void setCopyright(final String copyright) {
		this.copyrightProperty().set(copyright);
	}
	

	public final StringProperty translationProperty() {
		return this.translation;
	}
	

	public final String getTranslation() {
		return this.translationProperty().get();
	}
	

	public final void setTranslation(final String translation) {
		this.translationProperty().set(translation);
	}
	

	public final StringProperty musikProperty() {
		return this.musik;
	}
	

	public final String getMusik() {
		return this.musikProperty().get();
	}
	

	public final void setMusik(final String musik) {
		this.musikProperty().set(musik);
	}
	

	public final StringProperty textAutorProperty() {
		return this.textAutor;
	}
	

	public final String getTextAutor() {
		return this.textAutorProperty().get();
	}
	

	public final void setTextAutor(final String textAutor) {
		this.textAutorProperty().set(textAutor);
	}
	

	public final StringProperty originalTitleProperty() {
		return this.originalTitle;
	}
	

	public final String getOriginalTitle() {
		return this.originalTitleProperty().get();
	}
	

	public final void setOriginalTitle(final String originalTitle) {
		this.originalTitleProperty().set(originalTitle);
	}
	

	public final StringProperty titleProperty() {
		return this.title;
	}
	

	public final String getTitle() {
		return this.titleProperty().get();
	}
	

	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}

	public final BooleanProperty invalidProperty() {
		return this.invalid;
	}
	

	public final boolean isInvalid() {
		return this.invalidProperty().get();
	}
	

	public final void setInvalid(final boolean invalid) {
		this.invalidProperty().set(invalid);
	}
}
