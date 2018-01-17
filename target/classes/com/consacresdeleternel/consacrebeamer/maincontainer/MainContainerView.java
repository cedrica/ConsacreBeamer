package com.consacresdeleternel.consacrebeamer.maincontainer;

import javax.inject.Singleton;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

@Singleton
public class MainContainerView extends BorderPane {
	private ObjectProperty<VBox> listViewContainer = new SimpleObjectProperty<>();
	private ObjectProperty<FlowPane> flowPane = new SimpleObjectProperty<>();

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
}
