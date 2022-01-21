package controller;


import model.*;
import view.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class FSIconButtonEventHandler implements EventHandler<ActionEvent> {
	
	private FileSystem fs;
	
	public FSIconButtonEventHandler(FileSystem fs) {
		this.fs = fs;																								
	}

	@Override
	public void handle(ActionEvent event) {
		FSIcon source = (FSIcon) event.getSource();
		this.fs.setCurr(source.node);
	}

}
