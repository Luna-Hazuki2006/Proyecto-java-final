package modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Vehiculo;

public class Modelo_reportes extends Modelo {

	public Modelo_reportes() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public ArrayList<Vehiculo> listar_vehiculos_fecha(Date d) {
		ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>();
		try {
			sentencia = "select * "
					+ "from vehiculos "
					+ "where placa not in (select distinct placa_vehiculo "
					+ "from reservas "
					+ "where '" 
					+ d + "' between fecha_alquila and fecha_devolucion and estatus = 'a')";
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
	
	public ArrayList<Vehiculo> listar_vehiculos_no_retornados() {
		ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>();
		try {
			sentencia = "select * "
					+ "from vehiculos "
					+ "where placa in (select distinct placa_vehiculo "
					+ "from reservas "
					+ "where disponible = 0 and estatus = 'a')";
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

}
