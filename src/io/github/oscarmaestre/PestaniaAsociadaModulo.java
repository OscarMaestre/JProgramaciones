package io.github.oscarmaestre;

import java.sql.SQLException;

import javafx.scene.control.ListView;

public class PestaniaAsociadaModulo extends Pestania {

		public PestaniaAsociadaModulo(BaseDeDatosProgramaciones bd, String nombreTabla, String nombreCampoMostrar) {
		super(bd, nombreTabla, nombreCampoMostrar);
		// TODO Auto-generated constructor stub
	}

		@Override
	protected void setCampoIdBusqueda() {
		this.campoIDBusqueda="modulo_id";
	}

	@Override
	public long getIdParaBusqueda(String nombreModulo) {
		
		try {
			long id=this.bd.getIdModulo(nombreModulo);
			//System.out.println("Encontrado nombre de modulo:"+nombreModulo+":"+id);
			return id;
		} catch (SQLException e) {
			//System.out.println("No encontre el modulo:"+nombreModulo);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
