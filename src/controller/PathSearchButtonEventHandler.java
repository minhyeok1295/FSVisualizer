package controller;


import model.*;
import view.*;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class PathSearchButtonEventHandler implements EventHandler<ActionEvent>  {
	
	private TextField path;
	private FileSystem fs;
	
	public PathSearchButtonEventHandler(FileSystem fs, PathBar path) {
		this.path = path;
		this.fs = fs;
	}
	
	public boolean isPath(String path) {
		if (new File(path).exists()) {
			return true;
		}
		return false;
		
	}
	
	public void searchItem(String item) {
		FSNode res = null;
		if (this.fs.curr != null) {
			res = this.fs.searchTree(this.fs.curr, item);
		}
		if (res != null) {
			if (res.type == 'd') {
				this.fs.setCurr(res);
			}
			else {
				if (res.parent != null) {
					this.fs.setCurr(res.parent);
				}
			}
		}
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		String item = path.getText();
		if (isPath(item)) {
			this.fs.createTree(item);
		}
		else {
			searchItem(item);
		}
		
	}
	
}
