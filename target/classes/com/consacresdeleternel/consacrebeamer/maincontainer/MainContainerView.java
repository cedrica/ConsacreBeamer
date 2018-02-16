package com.consacresdeleternel.consacrebeamer.maincontainer;

import javax.inject.Singleton;

import org.controlsfx.control.HiddenSidesPane;
import org.controlsfx.control.MaskerPane;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

@Singleton
public class MainContainerView extends StackPane {
	private ObjectProperty<VBox> listViewContainer = new SimpleObjectProperty<>();
	private ObjectProperty<Boolean> saveAs = new SimpleObjectProperty<>(true);
	private ObjectProperty<FlowPane> flowPane = new SimpleObjectProperty<>();
	private ObjectProperty<MaskerPane> maskerPane = new SimpleObjectProperty<>();
	private ObjectProperty<HiddenSidesPane> hiddenSidesPane = new SimpleObjectProperty<>();

	public MainContainerView() {
		Helper.load(this, Localization.getDefault());
	}

	public final ObjectProperty<VBox> listViewContainerProperty() {
		return this.listViewContainer;
	}

	public final VBox getListViewContainer() {
		return this.listViewContainerProperty().get();
	}

	public final void setListViewContainer(final VBox listViewContainer) {
		this.listViewContainerProperty().set(listViewContainer);
	}

	public final ObjectProperty<FlowPane> flowPaneProperty() {
		return this.flowPane;
	}

	public final FlowPane getFlowPane() {
		return this.flowPaneProperty().get();
	}

	public final void setFlowPane(final FlowPane flowPane) {
		this.flowPaneProperty().set(flowPane);
	}

	public final ObjectProperty<MaskerPane> maskerPaneProperty() {
		return this.maskerPane;
	}

	public final MaskerPane getMaskerPane() {
		return this.maskerPaneProperty().get();
	}

	public final void setMaskerPane(final MaskerPane maskerPane) {
		this.maskerPaneProperty().set(maskerPane);
	}

	public final ObjectProperty<HiddenSidesPane> hiddenSidesPaneProperty() {
		return this.hiddenSidesPane;
	}

	public final HiddenSidesPane getHiddenSidesPane() {
		return this.hiddenSidesPaneProperty().get();
	}

	public final void setHiddenSidesPane(final HiddenSidesPane hiddenSidesPane) {
		this.hiddenSidesPaneProperty().set(hiddenSidesPane);
	}

	public final ObjectProperty<Boolean> saveAsProperty() {
		return this.saveAs;
	}

	public final Boolean getSaveAs() {
		return this.saveAsProperty().get();
	}

	public final void setSaveAs(final Boolean saveAs) {
		this.saveAsProperty().set(saveAs);
	}

}
