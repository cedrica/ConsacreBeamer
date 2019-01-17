package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.copyrights;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.data.SongCategory;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class CopyRightsView extends BorderPane {
	private StringProperty tempo = new SimpleStringProperty();
	private StringProperty key = new SimpleStringProperty();
	private StringProperty additionalInfo = new SimpleStringProperty();
	private ObjectProperty<Book> songBuch = new SimpleObjectProperty<>();
	private ObjectProperty<SongCategory> songCategory = new SimpleObjectProperty<>();
	private ListProperty<Book> bookItems = new SimpleListProperty<>();
	private ListProperty<SongCategory> songCategoryItems = new SimpleListProperty<>();
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
	private BooleanProperty invalid = new SimpleBooleanProperty();

	public CopyRightsView() {
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

	public final ObjectProperty<Book> songBuchProperty() {
		return this.songBuch;
	}

	public final Book getSongBuch() {
		return this.songBuchProperty().get();
	}

	public final void setSongBuch(final Book songBuch) {
		this.songBuchProperty().set(songBuch);
	}

	public final ListProperty<Book> bookItemsProperty() {
		return this.bookItems;
	}

	public final ObservableList<Book> getBookItems() {
		return this.bookItemsProperty().get();
	}

	public final void setBookItems(final ObservableList<Book> bookItems) {
		this.bookItemsProperty().set(bookItems);
	}

	public final ListProperty<SongCategory> songCategoryItemsProperty() {
		return this.songCategoryItems;
	}
	

	public final ObservableList<SongCategory> getSongCategoryItems() {
		return this.songCategoryItemsProperty().get();
	}
	

	public final void setSongCategoryItems(final ObservableList<SongCategory> songCategoryItems) {
		this.songCategoryItemsProperty().set(songCategoryItems);
	}

	public final ObjectProperty<SongCategory> songCategoryProperty() {
		return this.songCategory;
	}
	

	public final SongCategory getSongCategory() {
		return this.songCategoryProperty().get();
	}
	

	public final void setSongCategory(final SongCategory songCategory) {
		this.songCategoryProperty().set(songCategory);
	}
	
	

}
