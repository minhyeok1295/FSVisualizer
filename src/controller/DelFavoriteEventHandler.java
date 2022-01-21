package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.*;
import view.*;

public class DelFavoriteEventHandler implements EventHandler<ActionEvent> {
	private FileSystem fileSystem;
	private FavoriteIcon fi;
	
	public DelFavoriteEventHandler(FileSystem fs, FavoriteIcon fi) {
		this.fileSystem = fs;
		this.fi = fi;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (this.fileSystem.favorites.contains(this.fi.node)) {
			this.fileSystem.removeFavorite(this.fi.node);
		}
		
		
	}
}