package view;
import model.*;
import util.*;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class SideBar extends ScrollPane implements Observer {
	private FileSystem fs;

	public SideBar(FileSystem fs, BorderPane p) {
		this.fs = fs;
		this.setPrefWidth(140);
		this.prefHeightProperty().bind(p.heightProperty());
		this.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		this.setStyle("-fx-background: #ffebcd;" +
				"-fx-border-width: 1 1 0 0;\n" + 
				"-fx-border-style: solid;\n" + 
				"-fx-border-color: #6a6a6a;\n" + 
				"-fx-background-color: transparent;" );
		
	}
	
	public void displayFavorite(FileSystem fs) {
		List<FSNode> favs = fs.favorites;
		FlowPane fp = new FlowPane();
		fp.setAlignment(Pos.CENTER);
		this.setContent(null);
		for (int i = 0; i < favs.size(); i++) {
			FSNode fav = favs.get(i);
			FavoriteIcon fsi = new FavoriteIcon(fs, fav);
			fsi.setStyle("-fx-background-color:  #ffebcd;;");
			fsi.setPrefWidth(130);
			fp.getChildren().add(fsi);
		}
		fp.prefWidthProperty().bind(this.widthProperty());
		this.setContent(fp);
		
	}
	
	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		FileSystem fileSystem = (FileSystem) o;
		displayFavorite(fileSystem);
		
	}
}
