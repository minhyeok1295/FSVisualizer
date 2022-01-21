package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BrowseButton extends Button {
	
	Image browse = new Image(getClass().getResourceAsStream("../images/browse-96.png"), 20, 20, false, false);
	
	public BrowseButton() {
		this.setGraphic(new ImageView(browse));
		this.setStyle("-fx-background-color:  transparent;" +
				"-fx-opacity: 1;\r\n"
				+ "-fx-text-fill:#000000;");
	}
}
