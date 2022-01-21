package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.*;

public class GoBackButtonEventHandler implements EventHandler<ActionEvent>  {
	
	private FileSystem fs;
	
	public GoBackButtonEventHandler(FileSystem fs) {
		this.fs = fs;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (this.fs.curr != null) {
			if (this.fs.curr.parent != null) {
				this.fs.setCurr(this.fs.curr.parent);
			}
			else {
				this.fs.setCurr(this.fs.curr);
			}
		}
	}
}   
