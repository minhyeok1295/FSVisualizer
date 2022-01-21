package view;

import controller.AddFavoriteEventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;

public class FSIcon extends Button{
	public FSNode node;
	private FileSystem fs;
	Image file = new Image(getClass().getResourceAsStream("../images/file-144.png"), 100, 100, false, false);
	Image directory = new Image(getClass().getResourceAsStream("../images/folder-144.png"), 100, 100, false, false);
	
	public FSIcon(FileSystem fs, FSNode node) {
		this.fs = fs;
		this.node = node;
		this.setPrefSize(150, 110);
		if (node.type == 'f') {
			this.setGraphic(new ImageView(file));
			this.setDisable(true);
			this.setStyle("-fx-opacity: 1.0;\r\n" + 
			"-fx-text-fill: black;");
		}
		else if (node.type == 'd') {
			this.setGraphic(new ImageView(directory));
		}
		this.setText(node.name);
		this.setContentDisplay(ContentDisplay.TOP);
		this.setBackground(null);
		this.setStyle("    -fx-border-color: transparent;\r\n"
				+ "    -fx-background-color: transparent;");
		
		//Right click menu to add to Favorite
		ContextMenu ctxMenu = new ContextMenu();
		MenuItem addFavorite = new MenuItem("add to favorite");
		AddFavoriteEventHandler af = new AddFavoriteEventHandler(fs, this);
		addFavorite.setOnAction(af);
		ctxMenu.getItems().add(addFavorite);
		this.setContextMenu(ctxMenu);
	}

}
