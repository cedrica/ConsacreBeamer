package com.consacresdeleternel.consacrebeamer.maincontainer.book;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.glyphfont.FontAwesome;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.utils.SVGUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BookController implements Initializable{

	@FXML BookView rootNode;
	@FXML Button btnBook;
	@FXML Text txtBookTitle;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtBookTitle.textProperty().bind(rootNode.bookNameProperty());
		SVGUtil.setSVGGraphicInJFXButton(btnBook, "/icons/icons8-read-filled-50.svg");
	}

	@FXML public void onOpenBook(MouseEvent evt) {
		if(evt.getButton() == MouseButton.SECONDARY && evt.getClickCount() == 1){
			ContextMenu contextMenu = new ContextMenu();
			MenuItem delete = new MenuItem(Localization.asKey("csb.listItemViewContextMenu.delete"));
			delete.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.TRASH, 20));
			delete.setOnAction(e -> rootNode.fireEvent(new BookEvent(BookEvent.REMOVE_BOOK, rootNode.getBook())));
			
			MenuItem edit = new MenuItem(Localization.asKey("csb.listItemViewContextMenu.edit"));
			edit.setGraphic(Helper.setIcon(Color.LIGHTBLUE, FontAwesome.Glyph.EDIT, 20));
			edit.setOnAction(e -> rootNode.fireEvent(new BookEvent(BookEvent.EDIT_BOOK, rootNode.getBook())));
			
			
			contextMenu.getItems().addAll(edit,delete);
			contextMenu.show(btnBook, Side.BOTTOM, 0, 0);
		}else if(evt.getButton() == MouseButton.PRIMARY && evt.getClickCount() == 2){
			rootNode.fireEvent(new BookEvent(BookEvent.SHOW_SONG_LIST, rootNode.getBook()));
		}
	}

}
