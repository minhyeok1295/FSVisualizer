package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.*;
import view.FSIcon;

public class AddFavoriteEventHandler implements EventHandler<ActionEvent> {
	private FileSystem fileSystem;
	private FSIcon fsi;
	
	public AddFavoriteEventHandler(FileSystem fs, FSIcon fsi) {
		this.fileSystem = fs;
		this.fsi = fsi;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (!this.fileSystem.favorites.contains(fsi.node)) {
			this.fileSystem.addFavorite(fsi.node);
		}
		
		
	}
}
