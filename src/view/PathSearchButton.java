package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.*;

public class PathSearchButton extends Button implements Observer {
	Image search = new Image(getClass().getResourceAsStream("../images/search-500.png"), 20, 20, false, false);
	
	public PathSearchButton() {
		this.setGraphic(new ImageView(search));
		this.setStyle("-fx-background-color:  transparent;" +
				"-fx-opacity: 1;\r\n"
				+ "-fx-text-fill:#000000;");
		
	}

	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		
	}

}
