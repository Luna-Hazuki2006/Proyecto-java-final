package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Modelo {
	
	protected Connection con;
	protected Statement st;
	protected ResultSet rs;
	protected String sentencia;
	
	public Modelo() {
		// TODO Auto-generated constructor stub
		String url, user, password;
		url = "jdbc:mysql://localhost:3306/proyecto";
		user = "root";
		password = "AnaPaulaMendozaDiaz2006!";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
