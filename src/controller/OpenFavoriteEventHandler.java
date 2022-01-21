package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.*;

public class OpenFavoriteEventHandler implements EventHandler<ActionEvent> {
	private FileSystem fileSystem;
	private FSNode fn;
	
	public OpenFavoriteEventHandler(FileSystem fs, FSNode fn) {
		this.fileSystem = fs;
		this.fn = fn;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		this.fileSystem.setCurr(this.fn);
		
	}
}