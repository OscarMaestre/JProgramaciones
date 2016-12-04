package io.github.oscarmaestre;

public class DatosCruce {
	private final BaseDeDatosProgramaciones bd;
	private final String nombreTablaDisparadora;
	private final String campoTablaDisparadora;
	private final String nombreTablaMostrar;
	private final String nombreCampoMostrar;
	private final String nombreCampoClaveAjena;
	public DatosCruce(BaseDeDatosProgramaciones bd, String nombreTablaDisparadora, String campoTablaDisparadora,
			String nombreTablaMostrar, String nombreCampoMostrar, String nombreCampoClaveAjena) {
		super();
		this.bd = bd;
		this.nombreTablaDisparadora = nombreTablaDisparadora;
		this.campoTablaDisparadora = campoTablaDisparadora;
		this.nombreTablaMostrar = nombreTablaMostrar;
		this.nombreCampoMostrar = nombreCampoMostrar;
		this.nombreCampoClaveAjena = nombreCampoClaveAjena;
	}
	public BaseDeDatosProgramaciones getBd() {
		return bd;
	}
	public String getNombreTablaDisparadora() {
		return nombreTablaDisparadora;
	}
	public String getCampoTablaDisparadora() {
		return campoTablaDisparadora;
	}
	public String getNombreTablaMostrar() {
		return nombreTablaMostrar;
	}
	public String getNombreCampoMostrar() {
		return nombreCampoMostrar;
	}
	public String getNombreCampoClaveAjena() {
		return nombreCampoClaveAjena;
	}
	

	
}