package io.github.oscarmaestre;

import javafx.scene.control.Tab;

public class PestaniaTabla extends Tab {
	protected DatosCruce cruce;
	protected ListViewFixed<String> lvDisparador, lvDisparado;
	protected boolean sePuedenSeleccionarMuchos=false;
	public PestaniaTabla(DatosCruce data, ListViewFixed<String> lvDisparador, boolean sePuedenSeleccionarMuchos) {
		super();
		this.lvDisparador = lvDisparador;
		this.sePuedenSeleccionarMuchos = sePuedenSeleccionarMuchos;
	}
	public PestaniaTabla(String text, DatosCruce data, ListViewFixed<String> lvDisparador,
			boolean sePuedenSeleccionarMuchos) {
		super(text);
		this.lvDisparador = lvDisparador;
		this.sePuedenSeleccionarMuchos = sePuedenSeleccionarMuchos;
	}
	
	protected void crearControles(){
		
	}
	
	
	

}
