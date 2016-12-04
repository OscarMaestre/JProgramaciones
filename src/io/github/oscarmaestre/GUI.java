package io.github.oscarmaestre;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application implements ChangeListener<String>{
	private static final int ESPACIO=5;
	Scene escena;
	VBox layout=new VBox();
	ListViewFixed<String> lvCiclos;
	ListViewFixed<String> lvModulos;
	TabPane panelDatos;
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
		primaryStage.setTitle("Programaciones");
		primaryStage.show();
		
	}
	private void anadirControles() throws SQLException{
		HBox cajaSelecciones=new HBox();
		cajaSelecciones.setSpacing(ESPACIO);
		cajaSelecciones.setPrefWidth(Double.MAX_VALUE);
		
		this.lvCiclos = this.getLVCiclos();
		
		
		
		this.lvModulos=new ListViewFixed<String>();
		this.lvModulos.setMinWidth(120);
		
		layout.setSpacing(ESPACIO);
		panelDatos=new TabPane();
		panelDatos.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		crearTabs();
		cajaSelecciones.getChildren().add(this.lvCiclos);
		cajaSelecciones.getChildren().add(this.lvModulos);
		layout.getChildren().add(cajaSelecciones);
		layout.getChildren().add(panelDatos);
		
	}
	private void crearTabs(){
		ArrayList<DatosTabla> tablas=DatosTabla.getTablasInteres();
		for (DatosTabla tabla: tablas){
			Tab t=crearTab(tabla);
			panelDatos.getTabs().add(t);
		}
	}
	private Tab crearTab(DatosTabla tabla){
		String tablaAsociada=tabla.getNombreTablaAsociada();
		String nombreTabla=tabla.getNombreTabla();
		String nombreCampoMostrar=tabla.getNombreCampo();
		Pestania t=Pestania.fabricarPestania(
				tablaAsociada, bd, nombreTabla, nombreCampoMostrar);
		
		t.setText(tabla.getTextoLabel());
		if (tablaAsociada==BaseDeDatosProgramaciones.NOMBRE_TABLA_CICLOS){
			this.lvCiclos.getSelectionModel().selectedItemProperty().addListener(t);
		}
		if (tablaAsociada==BaseDeDatosProgramaciones.NOMBRE_TABLA_MODULOS){
			this.lvModulos.getSelectionModel().selectedItemProperty().addListener(t);
		}
		return t;
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
				this.lvModulos.setItems(bd.getNombresModulos(idCicloElegido));
				this.lvModulos.getSelectionModel().clearSelection();
			} catch (SQLException e) {
				
			}
		}
		
		
	}
}
