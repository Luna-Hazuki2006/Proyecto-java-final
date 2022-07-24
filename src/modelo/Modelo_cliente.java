package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import clases.Cliente;

public class Modelo_cliente extends Modelo {
	
	public Modelo_cliente() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public ArrayList<String> desc() {
		ArrayList<String> listas = new ArrayList<>();
		try {
			sentencia = "desc reservas";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next()) {
				listas.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listas;
	}
	
	
	public String dar_nombre_tipo(String codigo) {
		String nombre = null;
		try {
			sentencia = "select * from tipo_usuario where codigo = '" 
					+ codigo + "' and estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				nombre = rs.getString(2);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
	}
	
	public String dar_codigo_tipo(String nombre) {
		String codigo = null;
		try {
			sentencia = "select * from tipo_usuario where nombre = '" 
					+ nombre + "' and estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				codigo = rs.getString(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigo;
	}
	
	public ArrayList<String> dar_nombres_tipos() {
		ArrayList<String> codigos = new ArrayList<String>();
		try {
			sentencia = "select * from tipo_usuario where estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next()) {
				codigos.add(rs.getString(2));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigos;
	}
	
	public ArrayList<Cliente> listar_usuarios() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		try {
			sentencia = "select * from clientes where estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next()) {
				lista.add(new Cliente(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6), 
						rs.getString(7)));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<Cliente> listar_clientes() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		try {
			sentencia = "select * from clientes where tipo = '0001' and estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next()) {
				lista.add(new Cliente(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6), 
						rs.getString(7)));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<Cliente> listar_empleados() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		try {
			sentencia = "select * from clientes where tipo = '0002' and estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next()) {
				lista.add(new Cliente(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6), 
						rs.getString(7)));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<Cliente> listar_administradores() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		try {
			sentencia = "select * from clientes where tipo = '0003' and estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next()) {
				lista.add(new Cliente(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6), 
						rs.getString(7)));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public Cliente buscar_perdido(String cedula) {
		Cliente cliente = null;
		sentencia = "select * from clientes where cedula = '" 
				+ cedula + "' and estatus = 'a'";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				cliente = new Cliente(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6), 
						rs.getString(7));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
	}
	
	public Cliente buscar(String cedula) {
		Cliente cliente = null;
		try {
			sentencia = "select * from clientes where cedula = '" 
					+ cedula + "' and estatus = 'a'";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			if (rs.next()) {
				cliente = new Cliente(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6), 
						rs.getString(7));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
	}
	
	public boolean crear(Cliente c) {
		boolean verdad = false;
		System.out.println(c.getCedula());
		System.out.println(c.getNombres());
		System.out.println(c.getApellidos());
		System.out.println(c.getDireccion());
		System.out.println(c.getTelefono());
		System.out.println(c.getTipo());
		System.out.println(c.getClave());
		if (buscar_perdido(c.getCedula()) != null) {
			try {
				sentencia = "update clientes set nombres = '" 
						+ c.getNombres() + "', apellidos = '" 
						+ c.getApellidos() + "', direccion = '" 
						+ c.getDireccion() + "', telefono = '" 
						+ c.getTelefono() + "', tipo = '" 
						+ c.getTipo() + "', estatus = 'a' where cedula = '" 
						+ c.getCedula() + "'";
				st = con.createStatement();
				st.executeUpdate(sentencia);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			verdad = true;
		} else if (buscar(c.getCedula()) == null) {
			try {
				sentencia = "insert into clientes values ('" 
						+ c.getCedula() + "', '" 
						+ c.getNombres() + "', '" 
						+ c.getApellidos() + "', '" 
						+ c.getDireccion() + "', '" 
						+ c.getTelefono() + "', '" 
						+ c.getTipo() + "', '" 
						+ c.getClave() + "', 'a')";
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
	
	public void actualizar(Cliente c) {
		try {
			sentencia = "update clientes set nombres = '" 
					+ c.getNombres() + "', apellidos = '" 
					+ c.getApellidos() + "', direccion = '" 
					+ c.getDireccion() + "', telefono = '" 
					+ c.getTelefono() + "', tipo = '" 
					+ c.getTipo() + "', clave = '" 
					+ c.getClave() + "' where cedula = '" 
					+ c.getCedula() + "' and estatus = 'a'";
			st = con.createStatement();
			st.executeUpdate(sentencia);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminar(String cedula) {
		try {
			sentencia = "update clientes set estatus = 'i' where cedula = '" 
					+ cedula + "'";
			st = con.createStatement();
			st.executeUpdate(sentencia);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
