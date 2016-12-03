package io.github.oscarmaestre;

import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public abstract class Pestania extends Tab implements ChangeListener<String> {
	
	private HBox contenedor=new HBox();
	protected ListViewFixed<String> textos=new ListViewFixed<String>();
	protected TextArea textosCopiados=new TextArea();
	private Button btnCopiar=new Button("Copiar al portapapeles");
	protected BaseDeDatosProgramaciones bd;
	private String nombreTabla;
	private String nombreCampoMostrar;
	protected String campoIDBusqueda=null;
	
	private ObservableList<String> itemsVacios=FXCollections.observableArrayList();
	
	public Pestania(
			BaseDeDatosProgramaciones bd, String nombreTabla, 
			String nombreCampoMostrar) {
		super();
		this.bd = bd;
		this.nombreTabla = nombreTabla;
		this.nombreCampoMostrar = nombreCampoMostrar;
		this.crearControles();
	}
	
	public Pestania(String text, BaseDeDatosProgramaciones bd, 
			String nombreTabla, String nombreCampoMostrar) 
	{
		super(text);
		this.bd = bd;
		this.nombreTabla = nombreTabla;
		this.nombreCampoMostrar = nombreCampoMostrar;
		this.crearControles();
	}
	
	private void crearControles(){
		this.setCampoIdBusqueda();
		itemsVacios.add("Elija un módulo");
		contenedor.getChildren().addAll(textos,textosCopiados,btnCopiar);
		this.setContent(contenedor);
	}
	protected void limpiarTextos(){
		this.textos.setItems(itemsVacios);
	}
	public static Pestania fabricarPestania(String tablaAsociada,
			BaseDeDatosProgramaciones bd,String nombreTabla, 
			String nombreCampoMostrar)
	{
		
		if (tablaAsociada==BaseDeDatosProgramaciones.NOMBRE_TABLA_CICLOS){
			PestaniaAsociadaCiclo p;
			p=new PestaniaAsociadaCiclo(bd, nombreTabla, nombreCampoMostrar);
			return p;
		}
		if (tablaAsociada==BaseDeDatosProgramaciones.NOMBRE_TABLA_MODULOS){
			PestaniaAsociadaModulo p;
			p=new PestaniaAsociadaModulo(bd, nombreTabla, nombreCampoMostrar);
			return p;
		}
		System.out.println("NULL va!");
		return null;
	}
	
	public abstract long getIdParaBusqueda(String text);
	protected abstract void setCampoIdBusqueda();
	
	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue==null){
			this.limpiarTextos();
			return ;
		}
		try {
			long id=this.getIdParaBusqueda(newValue);
			System.out.println("Encontrado id:"+id);
			ObservableList<String> datos;
			datos = bd.getTextosCondicion(this.nombreTabla, nombreCampoMostrar, 
					campoIDBusqueda, id);
			System.out.println(datos.toString());
			this.textos.setItems(datos);
		} catch (SQLException e) {

		}
	}
}
