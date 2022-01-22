package view;

import model.*;
import util.*;
import javafx.scene.control.TextField;

import java.io.File;
import javafx.scene.input.KeyCode;


public class PathBar extends TextField implements Observer{

	public String path;
	private FileSystem fs;
	
	
	public PathBar(FileSystem fs) {
		this.fs = fs;
		this.setText("Search or Copy path to folder");
		this.setOnKeyPressed( event -> {
			if( event.getCode() == KeyCode.ENTER ) {
				String item = this.getText();
				if (isPath(item)) {
					this.fs.createTree(item);
				}
				else {
					searchItem(item);
				}
			}
		});

	}

	//check if given string is a valid path.
	public boolean isPath(String path) {
		if (new File(path).exists()) {
			return true;
		}
		return false;
		
	}
	//tree search method to recursively search item from the tree
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
	public void update(Observable o) {
		// TODO Auto-generated method stub
		FileSystem fileSystem = (FileSystem) o;
		if (fileSystem.curr != null) {
			this.setText(fileSystem.curr.path);
		}
		
	}
}
