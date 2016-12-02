package io.github.oscarmaestre;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {
	Scene escena;
	GridPane rejilla=new GridPane();
	@Override
	public void start(Stage primaryStage) {
		escena=new Scene(rejilla, 800,600);
		primaryStage.setScene(escena);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
