package io.github.oscarmaestre;

public class Ciclo {
	private int 		idCiclo;
	private String 		nombreCiclo;
	
	public static final String NOMBRE_ID 		= "id";
	public static final String NOMBRE_CICLO 	= "nombre";
	
	
	public Ciclo(int id_ciclo, String nombreCiclo) {
		super();
		this.idCiclo = id_ciclo;
		this.nombreCiclo = nombreCiclo;
	}
	public int getIdCiclo() {
		return idCiclo;
	}
	public String getNombreCiclo() {
		return nombreCiclo;
	}
	
}
