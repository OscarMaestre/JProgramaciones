package io.github.oscarmaestre;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application implements ChangeListener<String>{
	Scene escena;
	VBox layout=new VBox();
	ListView<String> lvCiclos;
	ListView<String> lvModulos;
	long idCicloElegido=-1;
	long idModuloElegido=-1;
	long idCursoElegido=-1;
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
		ListViewFixed<String> lv = this.getLVCiclos();
		this.lvCiclos=lv;
		layout.getChildren().add(lv);
		
		TabPane panelDatos=new TabPane();
		
		
		
	}
	
	private ArrayList<DatosTabla> getDatosTablas(){
		ArrayList<DatosTabla> lista;
		lista=new ArrayList<DatosTabla>();
		
		return lista;
	}
	
	private ListViewFixed<String> crearListView(ObservableList<String> datos){
		ListViewFixed<String> lv=new ListViewFixed<String>();
		lv.setItems(datos);
		lv.getSelectionModel().selectedItemProperty().addListener(this);
		return lv;
	}
	public ListViewFixed<String> getLVCiclos() throws SQLException{
		ObservableList<String> listaCiclos;
		listaCiclos=bd.getNombresCiclos();
		ListViewFixed<String> lvf=this.crearListView(listaCiclos);
		return lvf;
	}

	public static void main(String[] args) {
		launch(args);
	}
	public boolean seSeleccionoUnCiclo(ObservableValue<? extends String> observable){
		if (observable==this.lvCiclos.getSelectionModel().selectedItemProperty()){
			return true;
		}
		return false;
	}
	@Override
	public void changed
		(ObservableValue<? extends String> observable, String antiguoValor, String nuevoValor) {
		if (seSeleccionoUnCiclo(observable)){
			try {
				this.idCicloElegido=bd.getIdCiclo(nuevoValor);
			} catch (SQLException e) {
				
			}
		}
		
		
	}
}
