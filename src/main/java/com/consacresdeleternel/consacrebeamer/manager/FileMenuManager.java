package com.consacresdeleternel.consacrebeamer.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.consacresdeleternel.consacrebeamer.utils.FileUtil;

import javafx.geometry.Side;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
@Singleton
public class FileMenuManager {
	private static final Logger LOG = Logger.getLogger(FileMenuManager.class);
	private ToggleGroup toggleGroup = new ToggleGroup();
	private File actualPresentation;
	@Inject
	private SongRepository songRepository;
	@Inject
	private ValueObjectManager valueObjectManager;
	@Inject
	private DialogManager dialogManager;
	public void init(MainContainerView mainContainerView) {
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
		Dialog<ButtonType> dialogStage = dialogManager.showCreateOrEditNewSong(createOrEditNewSongView, mainContainerView.getScene().getWindow());
		Optional<ButtonType> showAndWait = dialogStage.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY) {
			song = songRepository.findById(song.getId());
			song = createSongFromCreateOrEditNewSongView(createOrEditNewSongView, song);
			song = songRepository.save(song);
			if (song != null) {
				FileUtil.saveSongAsTxtFileToSongsFolder(createOrEditNewSongView.getTextView().getSongHtml(),
						song.getTextFileReference());
				Dialogs.success(Localization.asKey("csb.alert.songSavingSuccessfulled"),
						mainContainerView.getScene().getWindow());
			} else {
				Dialogs.error(Localization.asKey("csb.alert.songSavingMissed"),
						mainContainerView.getScene().getWindow());
				return;
			}
			mainContainerView.fireEvent(
					new CreateOrEditNewSongEvent(CreateOrEditNewSongEvent.UPDATE_LIST_ITEM_AND_SONG_PART, song));
		}
	}
	private void createSongPartFromSong(MainContainerView mainContainerView, Song song) {
		List<SongPartView> songPartViews = new ArrayList<>();
		List<String> foramtedStrings = Arrays.asList(song.getSongBody().split("---")).stream()
				.filter(s -> !s.trim().isEmpty()).collect(Collectors.toList());// text
																				// without
																				// formating
		XMLSlideShow ppt = PPTService.generatePPT("", foramtedStrings);
		String[] parts = song.getSongHtml().split("---");// text with formating
		int index = 0;
		if (parts != null && parts.length > 0) {
			for (String part : parts) {
				SongPartView songPartView = new SongPartView();
				songPartView.setIndex(index++);
				songPartView.addEventHandler(SongPartEvent.SHOW_SONG_PART, evt -> {
					actualPresentation = PPTService.showPPT(ppt, actualPresentation, songPartView.getIndex());
					reformIndexes(songPartViews, songPartView);
				});
				songPartView.setText(part);
				songPartViews.add(songPartView);
			}
		} else {
			SongPartView songPartView = new SongPartView();
			songPartView.setIndex(index++);
			songPartView.addEventHandler(SongPartEvent.SHOW_SONG_PART, evt -> {
				actualPresentation = PPTService.showPPT(ppt, actualPresentation, songPartView.getIndex());
			});
			songPartView.setText(song.getSongBody());
			songPartViews.add(songPartView);
		}
		mainContainerView.getFlowPane().getChildren().clear();
		mainContainerView.getFlowPane().getChildren().addAll(songPartViews);
	}
	private CreateOrEditNewSongView createCreateOrEditNewSongViewFromSong(Song song) {
		CreateOrEditNewSongView createOrEditNewSongView = new CreateOrEditNewSongView();
		createOrEditNewSongView.getTextView().setTitle(song.getSongTitle());
		createOrEditNewSongView.getTextView().setSongText(FileUtil.readTxtFileToString(song.getTextFileReference()));
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
		createOrEditNewSongView.getCopyRightsView().setSongBuch(song.getBook());
		createOrEditNewSongView.getCopyRightsView().setAdditionalInfo(song.getAdditionalInfo());
		createOrEditNewSongView.getCopyRightsView().setKey(song.getSongKey());
		createOrEditNewSongView.getCopyRightsView().setTempo(song.getTempo());
		return createOrEditNewSongView;
	}

	private void handleSelectSong(MainContainerView mainContainerView, CreateOrEditNewSongEvent evt) {
		evt.consume();
		Song song = (Song) evt.getItemObject();
		createSongPartFromSong(mainContainerView, song);
	}



	private void handleCreateNewSong(MainContainerView mainContainerView, FileMenuEvent evt) {
		evt.consume();
		CreateOrEditNewSongView createOrEditNewSongView = new CreateOrEditNewSongView();
		Dialog<ButtonType> dialogStage = dialogManager.showCreateOrEditNewSong(createOrEditNewSongView,mainContainerView.getScene().getWindow());
		createOrEditNewSongView.getCopyRightsView().setBookItems(valueObjectManager.getBookItems());
		Optional<ButtonType> showAndWait = dialogStage.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY) {
			Song song = createSongFromCreateOrEditNewSongView(createOrEditNewSongView, new Song());
			FileUtil.saveSongAsTxtFileToSongsFolder(createOrEditNewSongView.getTextView().getSongHtml(),
					song.getTextFileReference());
			Song findByTitle = songRepository.findByTitle(song.getSongTitle());
			if (findByTitle != null) {
				Dialogs.error(Localization.asKey("csb.alert.songAlreadyExist"),
						mainContainerView.getScene().getWindow());
				return;
			}
			song = songRepository.save(song);
			if (song != null) {
				Dialogs.success(Localization.asKey("csb.alert.songSavingSuccessfulled"),
						mainContainerView.getScene().getWindow());
				ListItemView listItemView = createListItemView(mainContainerView, createOrEditNewSongView, song);
				toggleGroup.getToggles().add(listItemView.getToggle());
				valueObjectManager.getSaveAsBinder().put(listItemView, listItemView.getToggle().selectedProperty());
				mainContainerView.saveAsProperty().unbind();
				mainContainerView.saveAsProperty().bind(Helper.concatProperties(valueObjectManager.getSaveAsBinder()));
				mainContainerView.getListViewContainer().getChildren().add(listItemView);
				createSongPartFromSong(mainContainerView, song);
				mainContainerView.addEventHandler(CreateOrEditNewSongEvent.UPDATE_LIST_ITEM_AND_SONG_PART,
						e -> handleUpdateListItemAndSongPart(mainContainerView, listItemView, e));
			} else {
				Dialogs.error(Localization.asKey("csb.alert.songSavingMissed"),
						mainContainerView.getScene().getWindow());
				return;
			}
		}
	}

	
	private Song createSongFromCreateOrEditNewSongView(CreateOrEditNewSongView createOrEditNewSongView, Song song) {
		song.setSongTitle(createOrEditNewSongView.getTextView().getTitle());
		song.setSongBody(createOrEditNewSongView.getTextView().getSongText());
		//COPYRIGHT
		song.setSongHtml(createOrEditNewSongView.getTextView().getSongHtml());
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
		song.setBook(createOrEditNewSongView.getCopyRightsView().getSongBuch());
		song.setAdditionalInfo(createOrEditNewSongView.getCopyRightsView().getAdditionalInfo());
		song.setSongKey(createOrEditNewSongView.getCopyRightsView().getKey());
		song.setTempo(createOrEditNewSongView.getCopyRightsView().getTempo());
		song.setTextFileReference(createOrEditNewSongView.getTextView().getTitle().replaceAll(" ", "").concat(".txt"));
		//EXTRAS
		song.setAttachements(createOrEditNewSongView.getExtrasView().getAttachments());
		return song;
	}

	
	
	private ListItemView createListItemView(MainContainerView mainContainerView,
			CreateOrEditNewSongView createOrEditNewSongView, Song song) {
		ListItemView listItemView = new ListItemView();
		listItemView.setItemObject(song);
		listItemView.addEventHandler(ListItemViewEvent.SHOW_LIST_ITEM_CONTEXT_MENU,
				e -> handleShowListItemViewContextMenu(mainContainerView, listItemView, e));
		listItemView.setItemName(createOrEditNewSongView.getTextView().getTitle());
		listItemView.setPosition(mainContainerView.getListViewContainer().getChildren().size());
		return listItemView;
	}
	private void handleShowListItemViewContextMenu(MainContainerView mainContainerView, ListItemView listItemView,
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
		delete.setOnAction(
				evt -> removeFromList(mainContainerView, mainContainerView.getListViewContainer(), listItemView));

		MenuItem pushUp = new MenuItem();
		pushUp.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.ARROW_UP));
		pushUp.setText(Localization.asKey("csb.listItemViewContextMenu.arrowUp"));
		pushUp.setOnAction(evt -> pushUp(mainContainerView.getListViewContainer(), listItemView, pushUp));

		MenuItem pushDown = new MenuItem();
		pushDown.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.ARROW_DOWN));
		pushDown.setText(Localization.asKey("csb.listItemViewContextMenu.arrowDown"));
		pushDown.setOnAction(evt -> pushDown(mainContainerView.getListViewContainer(), listItemView, pushDown));

		contextMenu.getItems().addAll(edit, delete, pushUp, pushDown);

		if (listItemView.getPosition() == 0) {
			pushUp.setDisable(true);
		}
		if (listItemView.getPosition() == mainContainerView.getListViewContainer().getChildren().size() - 1) {
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

	private void removeFromList(MainContainerView mainContainerView, VBox listViewContainer,
			ListItemView listItemView) {
		listViewContainer.getChildren().remove(listItemView);
		if (listItemView.getToggle().isSelected()) {
			mainContainerView.getFlowPane().getChildren().clear();
		}
	}
	private void reformIndexes(List<SongPartView> songPartViews, SongPartView songPartView) {
		for (SongPartView sp : songPartViews) {
			if (sp.getIndex() < songPartView.getIndex()) {
				sp.setIndex(sp.getIndex() + 1);
			}
		}
		songPartView.setIndex(0);
	}
	private void handleUpdateListItemAndSongPart(MainContainerView mainContainerView, ListItemView listItemView,
			CreateOrEditNewSongEvent e) {
		e.consume();
		Song itemObject = (Song) e.getItemObject();
		createSongPartFromSong(mainContainerView, itemObject);
		listItemView.setItemName(itemObject.getSongTitle());
		listItemView.setItemObject(itemObject);
	}
}
