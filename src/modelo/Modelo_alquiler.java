package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import clases.Factura;
import clases.Reserva;

public class Modelo_alquiler extends Modelo {

	public Modelo_alquiler() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public ArrayList<Reserva> listar() {
		ArrayList<Reserva> lista = new ArrayList<Reserva>();
		try {
			sentencia = "select * "
					+ "from reservas "
					+ "where placa_vehiculo in "
					+ "(select * "
					+ "from vehiculos "
					+ "where disponible = 0 "
					+ "and estatus = 'a') "
					+ "and estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next()) {
				lista.add(new Reserva(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getDate(4), 
						rs.getDate(5), 
						rs.getDouble(6)));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public Reserva buscar(String codigo) {
		Reserva r = null;
		try {
			sentencia = "select * "
					+ "from reservas "
					+ "where codigo = '"
					+ codigo + "' and placa_vehiculo in "
					+ "(select placa "
					+ "from vehiculos "
					+ "where disponible = 0 "
					+ "and estatus = 'a') "
					+ "and estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				r = new Reserva(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getDate(4), 
						rs.getDate(5), 
						rs.getDouble(6));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public boolean crear(Reserva r, Factura f) {
		boolean verdad = false;
		if (buscar(r.getCodigo()) == null) {
			try {
				sentencia = "update vehiculos "
						+ "set disponible = 0 "
						+ "where placa = '" 
						+ r.getPlaca_vehiculo() + "'";
				st = con.createStatement();
				st.executeUpdate(sentencia);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				sentencia = "insert into facturas values ('" 
						+ f.getCodigo() + "', '" 
						+ f.getCodigo_reserva() + "', '" 
						+ f.getFecha() + "', 'a')";
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
	
	public void actualizar(Reserva r) {
		try {
			sentencia = "update reservas set fecha_alquila = '" 
					+ r.getFecha_alquila() + "', fecha_devolucion = '" 
					+ r.getFecha_devolucion() + "', precio_total = " 
					+ r.getPrecio_total() + " where codigo = '" 
					+ r.getCodigo() + "' and estatus = 'a'";
			st = con.createStatement();
			st.executeUpdate(sentencia);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
