package io.github.oscarmaestre;

import java.util.ArrayList;

public class DatosTabla {
	private String nombreTabla;
	private String nombreCampo;
	private String textoLabel;
	private String nombreTablaAsociada;
	
	
	public DatosTabla(String nombreTabla, String nombreCampo, String textoLabel, String nombreTablaAsociada) {
		super();
		this.nombreTabla = nombreTabla;
		this.nombreCampo = nombreCampo;
		this.textoLabel = textoLabel;
		this.nombreTablaAsociada = nombreTablaAsociada;
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
	public String getNombreTablaAsociada() {
		return nombreTablaAsociada;
	}
	public static ArrayList<DatosTabla> getTablasInteres(){
		ArrayList<DatosTabla> datos=new ArrayList<DatosTabla>();
		datos.add(new DatosTabla(
				"cualificaciones_profesionales","texto",
				"Cualificaciones profesionales del ciclo", 
				BaseDeDatosProgramaciones.NOMBRE_TABLA_CICLOS));
		datos.add(new DatosTabla(
				"competenciasgenerales","texto",
				"Competencias generales del ciclo", 
				BaseDeDatosProgramaciones.NOMBRE_TABLA_CICLOS));
		datos.add(new DatosTabla(
				"objetivosgenerales","texto",
				"Objetivos generales del ciclo", 
				BaseDeDatosProgramaciones.NOMBRE_TABLA_CICLOS));
		datos.add(new DatosTabla(
				"resultados_de_aprendizaje","texto",
				"Resultados de aprendizaje",
				BaseDeDatosProgramaciones.NOMBRE_TABLA_MODULOS
				));
		return datos;
	}
	
}
