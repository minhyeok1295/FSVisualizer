package controller;


import model.*;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;
 
public class BrowseButtonEventHandler implements EventHandler<ActionEvent> {
	
	public DirectoryChooser dirChooser;
	public Stage stage;
	private FileSystem fileSystem;
	
	public BrowseButtonEventHandler(FileSystem fs, DirectoryChooser dirChooser, Stage stage) {
		this.dirChooser = dirChooser;
		this.stage = stage;
		this.fileSystem = fs;
	}
	
	public String[] splitPath(String path) {
		Path p = Paths.get(path);
		return StreamSupport.stream(p.spliterator(), false).map(Path::toString)
                .toArray(String[]::new);
	}
	

	@Override
	public void handle(ActionEvent event) {
		File selectedDirectory = this.dirChooser.showDialog(stage);
		if (selectedDirectory != null) {
			this.fileSystem.path = selectedDirectory.getAbsolutePath();
		    //create Tree
		    this.fileSystem.createTree(this.fileSystem.path);
		}
	    
	    
	}

}
