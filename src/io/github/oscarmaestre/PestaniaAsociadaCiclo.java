package io.github.oscarmaestre;

import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class PestaniaAsociadaCiclo extends Pestania {

	public PestaniaAsociadaCiclo(BaseDeDatosProgramaciones bd, String nombreTabla, String nombreCampoMostrar) {
		super(bd, nombreTabla, nombreCampoMostrar);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setCampoIdBusqueda() {
		this.campoIDBusqueda="ciclo_id";
	}

	@Override
	public long getIdParaBusqueda(String nombreCiclo) {
		try {
			
			long id=this.bd.getIdCiclo(nombreCiclo);
			System.out.println("Encontrado nombre de ciclo:"+nombreCiclo +":"+id);
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	

}
