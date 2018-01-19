package com.consacresdeleternel.consacrebeamer.manager;

import java.io.File;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.controlsfx.glyphfont.FontAwesome;

import com.consacresdeleternel.consacrebeamer.common.Dialogs;
import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.events.CreateOrEditNewSongEvent;
import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;
import com.consacresdeleternel.consacrebeamer.events.ListItemViewEvent;
import com.consacresdeleternel.consacrebeamer.events.SongPartEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.CreateOrEditNewSongView;
import com.consacresdeleternel.consacrebeamer.maincontainer.listitem.ListItemView;
import com.consacresdeleternel.consacrebeamer.maincontainer.songpart.SongPartView;
import com.consacresdeleternel.consacrebeamer.repository.SongRepository;
import com.consacresdeleternel.consacrebeamer.service.PPTService;

import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

@Singleton
public class MainContainerManger {
	private static final Logger LOG = Logger.getLogger(MainContainerManger.class);
	@Inject
	private SongRepository songRepository;
	private ToggleGroup toggleGroup = new ToggleGroup();

	public void init(MainContainerView mainContainerView) {
		songRepository = new SongRepository();
		mainContainerView.addEventHandler(FileMenuEvent.NEW_SONG, evt -> handleCreateNewSong(mainContainerView, evt));
		mainContainerView.addEventHandler(FileMenuEvent.EDIT_SONG, evt -> handleEditSong(mainContainerView, evt));
		mainContainerView.addEventHandler(CreateOrEditNewSongEvent.SELECT_SONG,
				evt -> handleSelectSong(mainContainerView, evt));
	}

	private void handleEditSong(MainContainerView mainContainerView, FileMenuEvent evt) {
		evt.consume();
		Song song = evt.getSong();
		CreateOrEditNewSongView createOrEditNewSongView = createCreateOrEditNewSongViewFromSong(song);
		createOrEditNewSongView.getTextView().setEditMode(true);
		Dialog<ButtonType> dialogStage = Dialogs.customDialog(createOrEditNewSongView, Modality.APPLICATION_MODAL,
				Localization.asKey("csb.createNewSongView.title"), mainContainerView.getScene().getWindow());
		dialogStage.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		Button lookupButton = (Button) dialogStage.getDialogPane().lookupButton(ButtonType.APPLY);
		lookupButton.disableProperty().bind(createOrEditNewSongView.getTextView().invalidProperty()
				.or(createOrEditNewSongView.getCopyRightsView().invalidProperty()));
		Optional<ButtonType> showAndWait = dialogStage.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY) {
			song = songRepository.findById(song.getId());
			song = createSongFromCreateOrEditNewSongView(createOrEditNewSongView, song);
			song = songRepository.save(song);
			if (song != null) {
				Dialog<ButtonType> success = Dialogs.success(Localization.asKey("csb.alert.songSavingSuccessfulled"),
						mainContainerView.getScene().getWindow());
				success.getDialogPane().getButtonTypes().add(ButtonType.OK);
				success.showAndWait();
			} else {
				Dialogs.error(Localization.asKey("csb.alert.songSavingMissed"),
						mainContainerView.getScene().getWindow());
				return;
			}
			mainContainerView.fireEvent(new CreateOrEditNewSongEvent(CreateOrEditNewSongEvent.UPDATE_SONG_PART, song));
		}
	}

	private CreateOrEditNewSongView createCreateOrEditNewSongViewFromSong(Song song) {
		CreateOrEditNewSongView createOrEditNewSongView = new CreateOrEditNewSongView();
		createOrEditNewSongView.getTextView().setTitle(song.getSongTitle());
		createOrEditNewSongView.getTextView().setSongText(song.getSongBody());
		createOrEditNewSongView.getCopyRightsView().setTitle(song.getCopyRightTitle());
		createOrEditNewSongView.getCopyRightsView().setOriginalTitle(song.getOriginalTitle());
		createOrEditNewSongView.getCopyRightsView().setTextAutor(song.getAutor());
		createOrEditNewSongView.getCopyRightsView().setMusik(song.getMusik());
		createOrEditNewSongView.getCopyRightsView().setTranslation(song.getTraduction());
		createOrEditNewSongView.getCopyRightsView().setCopyright(song.getCopyRight());
		createOrEditNewSongView.getCopyRightsView().setNationalCopyr(song.getNationalCopy());
		createOrEditNewSongView.getCopyRightsView().setRight(song.getRights());
		createOrEditNewSongView.getCopyRightsView().setCCLiNr(song.getCcliNumber());
		createOrEditNewSongView.getCopyRightsView().setBibleReferenz(song.getBibleVerse());
		createOrEditNewSongView.getCopyRightsView().setSongBuchNr(song.getSongBook());
		createOrEditNewSongView.getCopyRightsView().setAdditionalInfo(song.getAdditionalInfo());
		createOrEditNewSongView.getCopyRightsView().setKey(song.getSongKey());
		createOrEditNewSongView.getCopyRightsView().setTempo(song.getTempo());
		return createOrEditNewSongView;
	}

	private void handleSelectSong(MainContainerView mainContainerView, CreateOrEditNewSongEvent evt) {
		evt.consume();
		Song song = (Song) evt.getItemObject();
		SongPartView songPartView = new SongPartView();
		songPartView.setText(song.getSongBody());
		mainContainerView.getFlowPane().getChildren().clear();
		mainContainerView.getFlowPane().getChildren().add(songPartView);
	}

	private void handleCreateNewSong(MainContainerView mainContainerView, FileMenuEvent evt) {
		evt.consume();
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
			Song song = createSongFromCreateOrEditNewSongView(createOrEditNewSongView, new Song());
			song = songRepository.save(song);
			if (song != null) {
				Dialog<ButtonType> success = Dialogs.success(Localization.asKey("csb.alert.songSavingSuccessfulled"),
						mainContainerView.getScene().getWindow());
				success.getDialogPane().getButtonTypes().add(ButtonType.OK);
				success.showAndWait();
				ListItemView listItemView = createListItemView(mainContainerView, createOrEditNewSongView, song);
				toggleGroup.getToggles().add(listItemView.getToggle());
				mainContainerView.getListViewContainer().getChildren().add(listItemView);
				SongPartView songPartView = createSongPartFromSong(mainContainerView, song);
				mainContainerView.addEventHandler(CreateOrEditNewSongEvent.UPDATE_SONG_PART,
						e -> handleUpdateListItemAndSongPart(listItemView, songPartView, e));
				mainContainerView.getFlowPane().getChildren().clear();
				mainContainerView.getFlowPane().getChildren().add(songPartView);
			} else {
				Dialogs.error(Localization.asKey("csb.alert.songSavingMissed"),
						mainContainerView.getScene().getWindow());
				return;
			}
		}
	}

	private void handleUpdateListItemAndSongPart(ListItemView listItemView, SongPartView songPartView,
			CreateOrEditNewSongEvent e) {
		e.consume();
		Song itemObject = (Song) e.getItemObject();
		songPartView.setText(itemObject.getSongBody());
		listItemView.setItemName(itemObject.getSongTitle());
		listItemView.setItemObject(itemObject);
	}

	private SongPartView createSongPartFromSong(MainContainerView mainContainerView, Song song) {
		SongPartView songPartView = new SongPartView();
		songPartView.addEventHandler(SongPartEvent.SHOW_SONG_PART, evt ->{
			LOG.info("Song part anzeigen");
			XMLSlideShow ppt = PPTService.generatePPT("",songPartView.getText());
			PPTService.showPPT(ppt);
		});
		songPartView.setText(song.getSongBody());
		return songPartView;
	}

	private ListItemView createListItemView(MainContainerView mainContainerView,
			CreateOrEditNewSongView createOrEditNewSongView, Song song) {
		ListItemView listItemView = new ListItemView();
		listItemView.setItemObject(song);
		listItemView.addEventHandler(ListItemViewEvent.SHOW_LIST_ITEM_CONTEXT_MENU,
				e -> handleShowListItemViewContextMenu(mainContainerView.getListViewContainer(), listItemView, e));
		listItemView.setItemName(createOrEditNewSongView.getTextView().getTitle());
		listItemView.setPosition(mainContainerView.getListViewContainer().getChildren().size());
		return listItemView;
	}

	private Song createSongFromCreateOrEditNewSongView(CreateOrEditNewSongView createOrEditNewSongView, Song song) {
		song.setSongTitle(createOrEditNewSongView.getTextView().getTitle());
		song.setSongBody(createOrEditNewSongView.getTextView().getSongText());
		song.setCopyRightTitle(createOrEditNewSongView.getCopyRightsView().getTitle());
		song.setCopyRight(createOrEditNewSongView.getCopyRightsView().getCopyright());
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
		return song;
	}

	private void handleShowListItemViewContextMenu(VBox listViewContainer, ListItemView listItemView,
			ListItemViewEvent e) {
		e.consume();
		ContextMenu contextMenu = new ContextMenu();

		MenuItem edit = new MenuItem();
		edit.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.EDIT));
		edit.setText(Localization.asKey("csb.listItemViewContextMenu.edit"));
		edit.setOnAction(evt -> editSong(listItemView));

		MenuItem delete = new MenuItem();
		delete.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.TRASH));
		delete.setText(Localization.asKey("csb.listItemViewContextMenu.delete"));
		delete.setOnAction(evt -> removeFromList(listViewContainer, listItemView));

		MenuItem pushUp = new MenuItem();
		pushUp.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.ARROW_UP));
		pushUp.setText(Localization.asKey("csb.listItemViewContextMenu.arrowUp"));
		pushUp.setOnAction(evt -> pushUp(listViewContainer, listItemView, pushUp));

		MenuItem pushDown = new MenuItem();
		pushDown.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.ARROW_DOWN));
		pushDown.setText(Localization.asKey("csb.listItemViewContextMenu.arrowDown"));
		pushDown.setOnAction(evt -> pushDown(listViewContainer, listItemView, pushDown));

		contextMenu.getItems().addAll(edit, delete, pushUp, pushDown);

		if (listItemView.getPosition() == 0) {
			pushUp.setDisable(true);
		}
		if (listItemView.getPosition() == listViewContainer.getChildren().size() - 1) {
			pushDown.setDisable(true);
		}

		contextMenu.show(listItemView, Side.BOTTOM, 0.0, 0.0);
	}

	private void editSong(ListItemView listItemView) {
		listItemView.fireEvent(new FileMenuEvent(FileMenuEvent.EDIT_SONG, (Song) listItemView.getItemObject()));
	}

	private void pushDown(VBox listViewContainer, ListItemView listItemView, MenuItem pushDown) {
		ListItemView next = (ListItemView) listViewContainer.getChildren().get(listItemView.getPosition() + 1);
		listItemView.setPosition(next.getPosition());
		listViewContainer.getChildren().remove(listItemView);
		listViewContainer.getChildren().add(next.getPosition(), listItemView);
		next.setPosition(next.getPosition() - 1);
		if (listItemView.getPosition() == listViewContainer.getChildren().size() - 1) {
			pushDown.setDisable(true);
		}
	}

	private void pushUp(VBox listViewContainer, ListItemView listItemView, MenuItem pushUp) {
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
