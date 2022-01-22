package view;

import model.*;
import controller.*;
import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;





public class FSApplication extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		//Model
		FileSystem fileSystem = new FileSystem();
		
		
		//DirectoryChooser
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setInitialDirectory(new File("C:\\"));

		
		// VIEW LAYOUT
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: #6a6a6a");
		
		HBox topBox = new HBox();
		topBox.setPadding(new Insets(5, 2, 3, 2));
		topBox.setPrefHeight(30);
		topBox.setStyle("-fx-background-color: #ffebcd;");
		
		PathBar pBar = new PathBar(fileSystem);

		DisplayScreen screen = new DisplayScreen(fileSystem);
		
		//Browse, Search Button
		BrowseButton bButton = new BrowseButton();
		PathSearchButton pButton = new PathSearchButton();
		Button gButton = new Button();
		Image backArrow = new Image(getClass().getResourceAsStream("../images/left_arrow.png"), 17, 17, false, false);
		gButton.setGraphic(new ImageView(backArrow));
		gButton.setStyle("-fx-background-color:  transparent;" +
				"-fx-opacity: 1;\r\n"
				+ "-fx-text-fill:#000000;");
		
		
		//EventHandler
		BrowseButtonEventHandler browse = new BrowseButtonEventHandler(fileSystem, dirChooser, stage);
		bButton.setOnAction(browse);
		PathSearchButtonEventHandler search = new PathSearchButtonEventHandler(fileSystem, pBar);
		pButton.setOnAction(search);
		GoBackButtonEventHandler goBack = new GoBackButtonEventHandler(fileSystem);
		gButton.setOnAction(goBack);
		
		
		//PathBar to include search, browse and goback button.
		HBox pathBarBox = new HBox();
		pathBarBox.getChildren().addAll(pBar, pButton);
		pathBarBox.setStyle("-fx-background-color: #ffebcd;");
		pathBarBox.prefWidthProperty().bind(topBox.widthProperty());
		pBar.prefWidthProperty().bind(pathBarBox.widthProperty());
		
		topBox.getChildren().addAll(gButton, pathBarBox, bButton);
		topBox.setAlignment(Pos.CENTER);
		
		//SideBar for favorite section
		SideBar sideBar = new SideBar(fileSystem, pane);	
		VBox sideBox = new VBox();
		sideBox.setStyle("-fx-background-color: #ffebcd;");
		sideBox.setPrefWidth(150);
		Image favoriteStar = new Image(getClass().getResourceAsStream("../images/star-96.png"), 17, 17, false, false);
		
		//Favorite button
		Button favText = new Button();
		favText.prefWidthProperty().bind(sideBox.widthProperty());
		favText.setGraphic(new ImageView(favoriteStar));
		favText.setDisable(true);
		favText.setBackground(null);
		favText.setStyle("-fx-border-width: 1 1 0 0;\n" + 
				"-fx-border-color: #6a6a6a;\n" + 
				"-fx-border-style: solid;\n" +"-fx-background-color:  transparent;" +
				"-fx-opacity: 1;\r\n"
				+ "-fx-text-fill:#000000;");
		favText.setText("Favorites");
		favText.setFont(new Font(15));
		
		
		
		sideBox.getChildren().addAll(favText, sideBar);
		
		
		//connect to Observable
		fileSystem.attach(pButton);
		fileSystem.attach(screen);
		fileSystem.attach(sideBar);
		fileSystem.attach(pBar);
		
		
		
		pane.setLeft(sideBox);
		pane.setTop(topBox);
		pane.setCenter(screen);
		
		// SCENE
		Scene scene = new Scene(pane, 900, 600); 
		stage.setTitle("FS visualizer");
		stage.setScene(scene);
		//stage.setResizable(false);
						
		// LAUNCH THE GUI
		stage.show();
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
