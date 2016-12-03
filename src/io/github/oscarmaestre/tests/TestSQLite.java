package io.github.oscarmaestre.tests;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.github.oscarmaestre.BaseDeDatosProgramaciones;
import javafx.collections.ObservableList;


public class TestSQLite {
	BaseDeDatosProgramaciones bd;
	@Before
	public void setUp() throws Exception {
		bd=new BaseDeDatosProgramaciones(":resource:resources/ciclos.db");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHayCiclos() throws SQLException {
		ResultSet rs=bd.getCiclos();
		int cantidad=0;
		while (rs.next()){
			cantidad++;
		}
		Assert.assertNotEquals(0, cantidad);
	}
	@Test
	public void testGetID() throws SQLException{
		long id=bd.getID("ciclos", "id", 
				"nombre", "Informática y comunicaciones");
		System.out.println("Se encontró un id válido:"+id);
	}
	@Test
	public void testGetNombresCiclos() throws SQLException{
		ObservableList<String> datos;
		datos=bd.getTextos("ciclos", "nombre");
		Assert.assertFalse(datos.size()==0);
		System.out.println("Se encontraron ciclos, cantidad:"+datos.size());
	}
	@Test
	public void testGetIdCiclos() throws SQLException{
		long id=bd.getIdModulo("Bases de datos.", 1);
		System.out.println("Id curso:"+id);
		Assert.assertNotEquals(0, id);
	}
	@Test
	public void testGetIdUnSoloCiclos() throws SQLException{
		long id=bd.getIdCiclo("Desarrollo de aplicaciones web");
		Assert.assertNotEquals(0, id);
		
	}
	@Test
	public void testGetTextosModulos() throws SQLException{
		ObservableList<String> datos = bd.getNombresModulos(1);
		System.out.println(datos.toString());
	}
	@Test
	public void testIdModuloAislado() throws SQLException{
		long idCiclo;
		idCiclo=this.bd.getIdModulo("Bases de datos.");
		
	}
	

}
