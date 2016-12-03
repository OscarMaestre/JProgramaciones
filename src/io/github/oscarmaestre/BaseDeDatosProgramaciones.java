package io.github.oscarmaestre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.JDBC;
import org.sqlite.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BaseDeDatosProgramaciones {
	Connection conexion;
	public static final String NOMBRE_TABLA_CICLOS="ciclos";
	public static final String CICLOS_NOMBRE_CICLO="nombre";
	public static final String CICLOS_NOMBRE_ID="id";

	
	public static final String NOMBRE_TABLA_CURSOS="cursos";
	public static final String CURSOS_NOMBRE_CURSO="nombre_curso";
	public static final String CURSOS_NOMBRE_ID="id";
	public static final String CURSOS_NOMBRE_CICLO_ID="ciclo_id";
	
	public static final String NOMBRE_TABLA_MODULOS="modulos";
	public static final String MODULOS_NOMBRE_MODULO="nombre";
	public static final String MODULOS_NOMBRE_ID="id";
	public static final String MODULOS_NOMBRE_CURSO_ID="curso_id";
	
	
	
	public BaseDeDatosProgramaciones(String archivoSqlite) throws SQLException{
		JDBC driver=new JDBC();
		DriverManager.registerDriver(driver);
		String cadConexion="jdbc:sqlite:"+archivoSqlite;
		//System.out.println(cadConexion);
		conexion=(SQLiteConnection) DriverManager.getConnection(cadConexion);
	}
	public ResultSet getCiclos() throws SQLException{
		String sql=String.format("select %s, %s from ciclos order by %s", Ciclo.NOMBRE_ID, 
				Ciclo.NOMBRE_CICLO, Ciclo.NOMBRE_CICLO);
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
	public long getIdCiclo(String nombreCiclo) throws SQLException{
		long id=-1;
		id=this.getID(NOMBRE_TABLA_CICLOS, CICLOS_NOMBRE_ID, CICLOS_NOMBRE_CICLO, nombreCiclo);
		return id;
	}
	/* Dado un módulo nos devuelve el id del modulo dado
	 * el nombre y el codigo de ciclo*/
	public long getIdModulo(String nombreModulo, long idCiclo) throws SQLException{
		 
		String sql="select id from modulos as m1 where nombre='Bases de datos' and curso_id in";
		sql+="(select id from cursos where ciclo_id=1)";
		String sql2="select %s from %s as m1 where %s='%s' and %s in";
		sql2+=" (select %s from %s where %s=%d)";
		String consulta=String.format(sql2,
				BaseDeDatosProgramaciones.MODULOS_NOMBRE_ID,
				BaseDeDatosProgramaciones.NOMBRE_TABLA_MODULOS,
				BaseDeDatosProgramaciones.MODULOS_NOMBRE_MODULO,
				nombreModulo,
				BaseDeDatosProgramaciones.MODULOS_NOMBRE_CURSO_ID,
				BaseDeDatosProgramaciones.CURSOS_NOMBRE_ID,
				BaseDeDatosProgramaciones.NOMBRE_TABLA_CURSOS,
				BaseDeDatosProgramaciones.CURSOS_NOMBRE_CICLO_ID,
				idCiclo
				);
		//System.out.println(sql);
		//System.out.println(consulta);
		ResultSet rs=this.getSQL(consulta);
		rs.next();
		long idCurso=rs.getLong(1);
		return idCurso;
		
	}
	
	public ObservableList<String> getTextos(String nombreTabla, String nombreCampo) throws SQLException{
		ObservableList<String> datos;
		datos=FXCollections.observableArrayList();
		
		String sql="select %s from %s order by %s";
		String consulta=String.format(sql, nombreCampo, nombreTabla, nombreCampo);
		ResultSet rs=this.getSQL(consulta);
		while (rs.next()){
			datos.add(rs.getString(1));
		}
		return datos;
	}
	public ObservableList<String> getNombresCiclos() throws SQLException{
		ObservableList<String> datos;
		datos=getTextos(NOMBRE_TABLA_CICLOS, CICLOS_NOMBRE_CICLO);
		return datos;
	}
	public ObservableList<String> getNombresModulos(long idCiclo) throws SQLException{
		ObservableList<String> datos;
		datos=FXCollections.observableArrayList();
		
		String sql2="select %s from %s where %s in";
		sql2+=" (select %s from %s where %s=%d) order by %s";
		String consulta=String.format(sql2,
				BaseDeDatosProgramaciones.MODULOS_NOMBRE_MODULO,
				BaseDeDatosProgramaciones.NOMBRE_TABLA_MODULOS,
				BaseDeDatosProgramaciones.MODULOS_NOMBRE_CURSO_ID,
				BaseDeDatosProgramaciones.CURSOS_NOMBRE_ID,
				BaseDeDatosProgramaciones.NOMBRE_TABLA_CURSOS,
				BaseDeDatosProgramaciones.CURSOS_NOMBRE_CICLO_ID,
				idCiclo,
				BaseDeDatosProgramaciones.MODULOS_NOMBRE_MODULO
				);
		System.out.println("Textos:"+consulta);
		ResultSet rs=this.getSQL(consulta);
		while (rs.next()){
			datos.add(rs.getString(1));
		}
		return datos;
	}
}

