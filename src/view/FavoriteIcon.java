package view;

import controller.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;


public class FavoriteIcon extends Button{
		public FSNode node;
		private FileSystem fs;
		Image directory = new Image(getClass().getResourceAsStream("../images/folder-144.png"), 20, 20, false, false);
		
		public FavoriteIcon(FileSystem fs, FSNode node) {
			this.fs = fs;
			this.node = node;
			this.setGraphic(new ImageView(directory));
			this.setText(node.name);
			this.setAlignment(Pos.CENTER_LEFT); 
			
			OpenFavoriteEventHandler of = new OpenFavoriteEventHandler(fs, node);
			this.setOnAction(of);
			
			/*drop down menu for right click to delete from the list*/
			ContextMenu ctxMenu = new ContextMenu();
			MenuItem delFavorite = new MenuItem("Delete favorite");
			DelFavoriteEventHandler df = new DelFavoriteEventHandler(fs, this);
			delFavorite.setOnAction(df);
			ctxMenu.getItems().add(delFavorite);
			this.setContextMenu(ctxMenu);
			
		}
}
