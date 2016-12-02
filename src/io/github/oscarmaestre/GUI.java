package io.github.oscarmaestre;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
	Scene escena;
	VBox layout=new VBox();
	
	BaseDeDatosProgramaciones bd;
	@Override
	public void start(Stage primaryStage) throws SQLException {
		bd=new BaseDeDatosProgramaciones(":resource:resources/ciclos.db");
		escena=new Scene(layout, 800,600);
		this.anadirControles();
		
		primaryStage.setScene(escena);
		primaryStage.show();
		
	}
	private void anadirControles() throws SQLException{
		ListView<String> lv = this.getLVCiclos();
		layout.getChildren().add(lv);
		
		
	}
	
	private ListView<String> crearListView(ObservableList<String> datos){
		ListView<String> lv=new ListView<String>();
		lv.setItems(datos);
		return lv;
	}
	public ListViewFixed<String> getLVCiclos() throws SQLException{
		ListViewFixed<String> lvCiclos;
		lvCiclos=new ListViewFixed<String>();
		lvCiclos.setItems( bd.getNombresCiclos() );
//		lvCiclos.setMaxWidth(Double.MAX_VALUE);
//		lvCiclos.setPrefWidth(Double.MAX_VALUE);
//		lvCiclos.setPrefHeight(Control.USE_COMPUTED_SIZE);
		return lvCiclos;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
