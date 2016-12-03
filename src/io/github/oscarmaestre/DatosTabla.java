package io.github.oscarmaestre;

public class DatosTabla {
	String nombreTabla;
	String nombreCampo;
	String textoLabel;
	public DatosTabla(String nombreTabla, String nombreCampo, String textoLabel) {
		super();
		this.nombreTabla = nombreTabla;
		this.nombreCampo = nombreCampo;
		this.textoLabel = textoLabel;
	}
	public String getNombreTabla() {
		return nombreTabla;
	}
	public String getNombreCampo() {
		return nombreCampo;
	}
	public String getTextoLabel() {
		return textoLabel;
	}
	
}
