package io.github.oscarmaestre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.JDBC;
import org.sqlite.SQLiteConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BaseDeDatosProgramaciones {
	Connection conexion;
	public BaseDeDatosProgramaciones(String archivoSqlite) throws SQLException{
		JDBC driver=new JDBC();
		DriverManager.registerDriver(driver);
		String cadConexion="jdbc:sqlite:"+archivoSqlite;
		System.out.println(cadConexion);
		conexion=(SQLiteConnection) DriverManager.getConnection(cadConexion);
	}
	public ResultSet getCiclos() throws SQLException{
		String sql=String.format("select %s, %s from ciclos", Ciclo.NOMBRE_ID, Ciclo.NOMBRE_CICLO);
		return this.getSQL(sql);
	}
	private ResultSet getSQL(String sql) throws SQLException{
		Statement sentencia=conexion.createStatement();
		ResultSet resultados=sentencia.executeQuery(sql);
		return resultados;
	}
	
	public long getID(String nombreTabla, String nombreCampoID, 
			String nombreCampo, String valorCampo) throws SQLException{
		String sql="Select %s from %s where %s='%s'";
		String consulta=String.format(sql, nombreCampoID, 
				nombreTabla,nombreCampo,valorCampo);
		ResultSet rs=this.getSQL(consulta);
		rs.next();
		return rs.getLong(1);
	}
	
	public ObservableList<String> getTextos(String nombreTabla, String nombreCampo) throws SQLException{
		ObservableList<String> datos;
		datos=FXCollections.observableArrayList();
		
		String sql="select %s from %s";
		String consulta=String.format(sql, nombreCampo, nombreTabla);
		ResultSet rs=this.getSQL(consulta);
		while (rs.next()){
			datos.add(rs.getString(1));
		}
		return datos;
	}
}
