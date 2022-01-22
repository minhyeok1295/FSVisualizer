package view;
import model.*;
import util.*;
import controller.*;

import java.util.List;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class DisplayScreen extends ScrollPane implements Observer {
	public FileSystem fs;
	
	public DisplayScreen(FileSystem fs) {
		this.fs = fs;
		this.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		this.setStyle("-fx-background: #ffebcd;;" +
				"-fx-background-color: transparent;" );
		
	}
	
	//Display sub-folders or files under current node(folder);
	public void displayChildren(FileSystem fs) {
		if (fs.curr != null) {
			FSIconButtonEventHandler fsIcon = new FSIconButtonEventHandler(fs);
			List<FSNode> children = fs.curr.children;
			FlowPane fp = new FlowPane(Orientation.HORIZONTAL);
			fp.setHgap(3);
			fp.setVgap(5);
			this.setContent(null);
			for(int i = 0; i < children.size(); i++) {
				FSNode child = children.get(i);
				VBox v = new VBox();
				v.setAlignment(Pos.CENTER);
				FSIcon icon = new FSIcon(fs, child);
				icon.setOnAction(fsIcon);
				v.setPrefSize(160, 140); 
				v.getChildren().add(icon);
				fp.getChildren().add(v);
			}
			this.setContent(fp);
			fp.prefWidthProperty().bind(this.widthProperty());
		}
	}
	
	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		FileSystem fileSystem = (FileSystem) o;
		displayChildren(fileSystem);
	}
}
