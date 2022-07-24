package modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Reserva;
import clases.Vehiculo;

public class Modelo_reservas extends Modelo {

	public Modelo_reservas() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public ArrayList<Reserva> listar() {
		ArrayList<Reserva> lista = new ArrayList<Reserva>();
		try {
			sentencia = "select * from reservas where estatus = 'a'";
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
	
	public ArrayList<Vehiculo> listar_autos_disponibles(Date p, Date s) {
		ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>();
		Modelo_vehiculo mv = new Modelo_vehiculo();
		try {
			sentencia = "select distinct placa "
					+ "from vehiculos, reservas where placa not in "
					+ "(select distinct placa_vehiculo "
					+ "from reservas "
					+ "where fecha_alquila <= '"
					+ s + "' and fecha_devolucion >= '"
					+ p + "' and estatus = 'a')";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next()) {
				Vehiculo v = mv.buscar(rs.getString(1));
				lista.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public double dar_precio_vehiculo(String placa) {
		double precio = 0;
		try {
			sentencia = "select precio_dia from vehiculos where placa = '" 
					+ placa + "' and estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				precio = rs.getDouble(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return precio;
	}
	
	public Reserva buscar_perdido(String codigo) {
		Reserva r = null;
		try {
			sentencia = "select * from reservas where codigo = '" 
					+ codigo + "' and estatus = 'i'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				r = new Reserva(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getDate(3), 
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
	
	public String dar_codigo() {
		String c = "";
		try {
			sentencia = "select count(*) from reservas";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				int esto = rs.getInt(1) + 1;
				int todo = 8 - String.valueOf(esto).length();
				for (int i = 0; i < todo; i++) c += "0";
				c += esto;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public Reserva buscar(String codigo) {
		Reserva r = null;
		try {
			sentencia = "select * from reservas where codigo = '" 
					+ codigo + "' and estatus = 'a'";
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
	
	public boolean crear(Reserva r) {
		boolean verdad = false;
		if (buscar_perdido(r.getCodigo()) != null) {
			try {
				System.out.println("Se está modificando");
				sentencia = "update reservas set cedula_cliente = '" 
						+ r.getCedula_cliente() + "', placa_vehiculo = '" 
						+ r.getPlaca_vehiculo() + "', fecha_alquila = '" 
						+ r.getFecha_alquila() + "', fecha_devolucion = '" 
						+ r.getPrecio_total() + "', estatus = 'a' where codigo = '" 
						+ r.getCodigo() + "'";
				st = con.createStatement();
				st.executeUpdate(sentencia);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			verdad = true;
		} else if (buscar(r.getCodigo()) == null) {
			try {
				sentencia = "insert into reservas values ('" 
						+ r.getCodigo() + "', '" 
						+ r.getCedula_cliente() + "', '" 
						+ r.getPlaca_vehiculo() + "', '" 
						+ r.getFecha_alquila() + "', '" 
						+ r.getFecha_devolucion() + "', '" 
						+ r.getPrecio_total() + "', 'a')";
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
	
	public void modificar(Reserva r) {
		try {
			sentencia = "update reservas set cedula_cliente = '" 
					+ r.getCedula_cliente() + "', placa_vehiculo = '" 
					+ r.getPlaca_vehiculo() + "', fecha_alquila = '" 
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
	
	public void eliminar(String codigo) {
		try {
			sentencia = "update reservas set estatus = 'i' where codigo = '" 
					+ codigo + "'";
			st = con.createStatement();
			st.executeUpdate(sentencia);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
