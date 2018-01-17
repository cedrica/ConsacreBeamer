package com.consacresdeleternel.consacrebeamer.manager;

import java.util.Base64;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.controlsfx.glyphfont.FontAwesome;

import com.consacresdeleternel.consacrebeamer.common.Dialogs;
import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;
import com.consacresdeleternel.consacrebeamer.events.ListItemViewEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.CreateOrEditNewSongView;
import com.consacresdeleternel.consacrebeamer.maincontainer.listitem.ListItemView;
import com.consacresdeleternel.consacrebeamer.maincontainer.songpart.SongPartView;
import com.consacresdeleternel.consacrebeamer.repository.SongRepository;

import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

@Singleton
public class MainContainerManger {
	private static final Logger LOG = Logger.getLogger(MainContainerManger.class);
	@Inject
private SongRepository songRepository;
	public void init(MainContainerView mainContainerView) {
		mainContainerView.addEventHandler(FileMenuEvent.NEW_SONG, evt -> {
			LOG.info("New song was added");
			CreateOrEditNewSongView createOrEditNewSongView = new CreateOrEditNewSongView();
			Dialog<ButtonType> dialogStage = Dialogs.customDialog(createOrEditNewSongView, Modality.APPLICATION_MODAL,
					Localization.asKey("csb.createNewSongView.title"), mainContainerView.getScene().getWindow());
			dialogStage.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
			Button lookupButton = (Button) dialogStage.getDialogPane().lookupButton(ButtonType.APPLY);
			lookupButton.disableProperty().bind(createOrEditNewSongView.getTextView().invalidProperty()
					.or(createOrEditNewSongView.getCopyRightsView().invalidProperty()));
			Optional<ButtonType> showAndWait = dialogStage.showAndWait();
			if (showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY) {
				Song song = new Song();
				song.setSongTitle(createOrEditNewSongView.getTextView().getTitle());
				song.setSongBody(createOrEditNewSongView.getTextView().getSonghtmlBase64());
				song.setCopyRightTitle(createOrEditNewSongView.getCopyRightsView().getTitle());
				song.setOriginalTitle(createOrEditNewSongView.getCopyRightsView().getOriginalTitle());
				song.setAutor(createOrEditNewSongView.getCopyRightsView().getTextAutor());
				song.setMusik(createOrEditNewSongView.getCopyRightsView().getMusik());
				song.setTraduction(createOrEditNewSongView.getCopyRightsView().getTranslation());
				song.setNationalCopy(createOrEditNewSongView.getCopyRightsView().getNationalCopyr());
				song.setRights(createOrEditNewSongView.getCopyRightsView().getRights());
				song.setCcliNumber(createOrEditNewSongView.getCopyRightsView().getCCLiNr());
				song.setBibleVerse(createOrEditNewSongView.getCopyRightsView().getBibleReferenz());
				song.setSongBook(createOrEditNewSongView.getCopyRightsView().getSongBuchNr());
				song.setAdditionalInfo(createOrEditNewSongView.getCopyRightsView().getAdditionalInfo());
				song.setSongKey(createOrEditNewSongView.getCopyRightsView().getKey());
				song.setTempo(createOrEditNewSongView.getCopyRightsView().getTempo());
				
				songRepository = new SongRepository();
				song = songRepository.save(song);
				if(song != null){
					Dialogs.success(Localization.asKey("csb.alert.songSavingSuccessfulled"),mainContainerView.getScene().getWindow());
					ListItemView listItemView = new ListItemView();
					listItemView.setItemObject(song);
					listItemView.addEventHandler(ListItemViewEvent.SHOW_LIST_ITEM_CONTEXT_MENU,
							e -> handleShowListItemViewContextMenu(mainContainerView.getListViewContainer(), listItemView,
									e));
					listItemView.setItemName(createOrEditNewSongView.getCopyRightsView().getTitle());
					listItemView.setPosition(mainContainerView.getListViewContainer().getChildren().size());
					mainContainerView.getListViewContainer().getChildren().add(listItemView);
					SongPartView songPartView = new SongPartView();
					System.out.println("html: "+Base64.getDecoder().decode(song.getSongBody()).toString());
					songPartView.setHtml(Base64.getDecoder().decode(song.getSongBody()).toString());
					mainContainerView.getFlowPane().getChildren().add(songPartView);
				}else{
					Dialogs.error(Localization.asKey("csb.alert.songSavingMissed"),mainContainerView.getScene().getWindow());
					return;
				}
			}
		});

	}

	private void handleShowListItemViewContextMenu(VBox listViewContainer, ListItemView listItemView,
			ListItemViewEvent e) {
		e.consume();
		ContextMenu contextMenu = new ContextMenu();

		MenuItem delete = new MenuItem();
		delete.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.TRASH));
		delete.setText(Localization.asKey("csb.listItemViewContextMenu.delete"));
		delete.setOnAction(evt -> removeFromList(listViewContainer, listItemView));

		MenuItem pushUp = new MenuItem();
		pushUp.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.ARROW_UP));
		pushUp.setText(Localization.asKey("csb.listItemViewContextMenu.arrowUp"));
		pushUp.setOnAction(evt -> pushUp(listViewContainer, listItemView,pushUp));

		MenuItem pushDown = new MenuItem();
		pushDown.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.ARROW_DOWN));
		pushDown.setText(Localization.asKey("csb.listItemViewContextMenu.arrowDown"));
		pushDown.setOnAction(evt -> pushDown(listViewContainer, listItemView,pushDown));

		contextMenu.getItems().addAll(delete, pushUp, pushDown);

		if (listItemView.getPosition() == 0) {
			pushUp.setDisable(true);
		}
		if (listItemView.getPosition() == listViewContainer.getChildren().size()-1) {
			pushDown.setDisable(true);
		}

		contextMenu.show(listItemView, Side.BOTTOM, 0.0, 0.0);
	}

	private void pushDown(VBox listViewContainer, ListItemView listItemView,MenuItem pushDown) {
		ListItemView next = (ListItemView) listViewContainer.getChildren().get(listItemView.getPosition() + 1);
		listItemView.setPosition(next.getPosition());
		listViewContainer.getChildren().remove(listItemView);
		listViewContainer.getChildren().add(next.getPosition(), listItemView);
		next.setPosition(next.getPosition() - 1);
		if (listItemView.getPosition() == listViewContainer.getChildren().size()-1) {
			pushDown.setDisable(true);
		}
	}

	private void pushUp(VBox listViewContainer, ListItemView listItemView,MenuItem pushUp) {
		ListItemView previous = (ListItemView) listViewContainer.getChildren().get(listItemView.getPosition() - 1);
		listItemView.setPosition(previous.getPosition());
		listViewContainer.getChildren().remove(listItemView);
		listViewContainer.getChildren().add(previous.getPosition(), listItemView);
		previous.setPosition(previous.getPosition() + 1);
		if (listItemView.getPosition() == 0) {
			pushUp.setDisable(true);
		}
	}

	private void removeFromList(VBox listViewContainer, ListItemView listItemView) {
		listViewContainer.getChildren().remove(listItemView);
	}

}
