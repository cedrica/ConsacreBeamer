package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.copyrights.CopyRightsView;
import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.extras.ExtrasView;
import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.text.TextView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TabPane;

public class CreateOrEditNewSongView extends TabPane {
	private ObjectProperty<TextView>  textView = new SimpleObjectProperty<>();
	private ObjectProperty<CopyRightsView>  copyRightsView = new SimpleObjectProperty<>();
	private ObjectProperty<ExtrasView>  extrasView = new SimpleObjectProperty<>();
	public  CreateOrEditNewSongView () {
		Helper.load(this, Localization.getDefault());
	}

	public final ObjectProperty<TextView> textViewProperty() {
		return this.textView;
	}
	

	public final TextView getTextView() {
		return this.textViewProperty().get();
	}
	

	public final void setTextView(final TextView textView) {
		this.textViewProperty().set(textView);
	}
	

	public final ObjectProperty<CopyRightsView> copyRightsViewProperty() {
		return this.copyRightsView;
	}
	

	public final CopyRightsView getCopyRightsView() {
		return this.copyRightsViewProperty().get();
	}
	

	public final void setCopyRightsView(final CopyRightsView copyRightsView) {
		this.copyRightsViewProperty().set(copyRightsView);
	}

	public final ObjectProperty<ExtrasView> extrasViewProperty() {
		return this.extrasView;
	}
	

	public final ExtrasView getExtrasView() {
		return this.extrasViewProperty().get();
	}
	

	public final void setExtrasView(final ExtrasView extrasView) {
		this.extrasViewProperty().set(extrasView);
	}
	
	

}
