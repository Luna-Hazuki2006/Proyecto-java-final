package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import clases.Vehiculo;

public class Modelo_vehiculo extends Modelo {
	
	public Modelo_vehiculo() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public ArrayList<Vehiculo> listar() {
		ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>();
		try {
			sentencia = "select * from vehiculos where estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next()) {
				lista.add(new Vehiculo(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getDouble(5), 
						rs.getDouble(6), 
						rs.getBoolean(7)));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public Vehiculo buscar_perdido(String placa) {
		Vehiculo v = null;
		try {
			sentencia = "select * from vehiculos where placa = '" 
					+ placa + "' and estatus = 'i'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				v = new Vehiculo(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getDouble(5), 
						rs.getDouble(6), 
						rs.getBoolean(7));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	
	public Vehiculo buscar(String placa) {
		Vehiculo v = null;
		sentencia = "select * from vehiculos where placa = '" 
				+ placa + "' and estatus = 'a'";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				v = new Vehiculo(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getDouble(5), 
						rs.getDouble(6), 
						rs.getBoolean(7));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	
	public boolean crear(Vehiculo v) {
		boolean verdad = false;
		System.out.println(v.getPlaca());
		System.out.println(v.getMarca());
		System.out.println(v.getModelo());
		System.out.println(v.getColor());
		System.out.println(v.getKilometraje());
		System.out.println(v.getPrecio_dia());
		System.out.println(v.isDisponible());
		int disponible = v.isDisponible() ? 1 : 0;
		if (buscar_perdido(v.getPlaca()) != null) {
			try {
				System.out.println("se está actualizando");
				sentencia = "update vehiculos set marca = '" 
						+ v.getMarca() + ", modelo = '" 
						+ v.getModelo() + "', color = '" 
						+ v.getColor() + "', precio_dia = " 
						+ v.getPrecio_dia() + ", kilometraje = " 
						+ v.getKilometraje() + ", disponible = " 
						+ disponible + ", estatus = 'a' where placa = '" 
						+ v.getPlaca() + "'";
				st = con.createStatement();
				st.executeUpdate(sentencia);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			verdad = true;
		}
		else if (buscar(v.getPlaca()) == null) {
			try {
				System.out.println("Se está creando");
				sentencia = "insert into vehiculos values ('" 
						+ v.getPlaca() + "', '" 
						+ v.getMarca() + "', '"
						+ v.getModelo() + "', '"
						+ v.getColor() + "', " 
						+ v.getPrecio_dia() + ", " 
						+ v.getKilometraje() + ", " 
						+ disponible + ", 'a')";
				st = con.createStatement();
				st.executeUpdate(sentencia);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			verdad = true;
		}
		return verdad;
	}
	
	public boolean actualizar(Vehiculo v) {
		boolean verdad = false;
		int disponible = v.isDisponible() ? 1 : 0;
		if (buscar(v.getPlaca()) != null) {
			try {
				sentencia = "update vehiculos set marca = '" 
						+ v.getMarca() + "', modelo = '" 
						+ v.getModelo() + "', color = '" 
						+ v.getColor() + "', precio_dia = " 
						+ v.getPrecio_dia() + ", kilometraje = " 
						+ v.getKilometraje() + ", disponible = " 
						+ disponible + " where placa = '" 
						+ v.getPlaca() + "' and estatus = 'a'";
				st = con.createStatement();
				st.executeUpdate(sentencia);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			verdad = true;
		}
		return verdad;
	}
	
	public boolean eliminar(String placa) {
		boolean verdad = false;
		if (buscar(placa) != null) {
			try {
				sentencia = "update vehiculos set estatus = 'i' where placa = '" 
						+ placa + "'";
				st = con.createStatement();
				st.executeUpdate(sentencia);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			verdad = true;
		}
		return verdad;
	}

}
