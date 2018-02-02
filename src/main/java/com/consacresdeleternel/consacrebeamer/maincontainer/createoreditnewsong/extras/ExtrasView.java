package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.extras;

import java.io.File;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Attachment;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class ExtrasView extends BorderPane {
	private ListProperty<Attachment> attachments = new SimpleListProperty<>();

	public ExtrasView() {
		Helper.load(this, Localization.getDefault());
	}

	public final ListProperty<Attachment> attachmentsProperty() {
		return this.attachments;
	}

	public final ObservableList<Attachment> getAttachments() {
		return this.attachmentsProperty().get();
	}

	public final void setAttachments(final ObservableList<Attachment> attachments) {
		this.attachmentsProperty().set(attachments);
	}

}
