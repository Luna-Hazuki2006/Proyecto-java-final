package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import clases.Cliente;
import clases.Reserva;
import clases.Vehiculo;
import modelo.Modelo_cliente;
import modelo.Modelo_reservas;
import vista.Modulos;

public class Controlador_reservas implements DocumentListener, ActionListener {

	private Reserva r;
	private Modelo_reservas mr;
	private Modulos m;
	
	public Controlador_reservas(Modulos mo) {
		// TODO Auto-generated constructor stub
		m = mo;
		r = new Reserva(null, null, null, null, null, 0);
		mr = new Modelo_reservas();
	}
	
	public void limpiar() {
		if (!m.getUsuario().getTipo().equals("0001")) m.setTcla(null);
		m.setTfeci("####-##-##");
		m.setTfecf("####-##-##");
	}
	
	public Reserva dar_reserva() {
		return r;
	}
	
	public void guardar() {
		r.setCodigo(m.getTcla());
		try {
			r.setCedula_cliente(m.getCusuarios().getSelectedItem().toString());
		} catch (Exception e) {
			// TODO: handle exception
			r.setCedula_cliente(m.getUsuario().getCedula());
		}
		r.setPlaca_vehiculo(m.getCtipos().getSelectedItem().toString());
		r.setFecha_alquila(Date.valueOf(m.getTfeci()));
		r.setFecha_devolucion(Date.valueOf(m.getTfecf()));
		double precio_dia = mr.dar_precio_vehiculo(r.getPlaca_vehiculo());
		System.out.println(precio_dia);
		LocalDate p = LocalDate.parse(r.getFecha_alquila().toString());
		LocalDate s = LocalDate.parse(r.getFecha_devolucion().toString());
		int dias = (int) ChronoUnit.DAYS.between(p, s);
		r.setPrecio_total(precio_dia * dias);
	}
	
	public void cargar() {
		m.setTcla(r.getCodigo());
		try {
			m.getCusuarios().setSelectedItem(r.getCedula_cliente());
			m.getCtipos().setSelectedItem(r.getPlaca_vehiculo());
		} catch (Exception e) {
			// TODO: handle exception
			m.setTpla(r.getPlaca_vehiculo());
		}
		m.setTfeci(r.getFecha_alquila().toString());
		m.setTfecf(r.getFecha_devolucion().toString());
	}
	
	public void dar_codigo() {
		m.setTcla(mr.dar_codigo());
	}
	
	public boolean preguntar(String usuario) {
		int r = JOptionPane.showConfirmDialog(
				new JFrame(), 
				"¿Desea " + usuario + " esta reserva?", 
				"Pregunta de acceso", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.INFORMATION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) return true;
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		// TODO Auto-generated method stub
		if (eve.getActionCommand().equalsIgnoreCase("limpiar")) {
			limpiar();
		} else if (eve.getActionCommand().equalsIgnoreCase("buscar")) {
			r = mr.buscar(m.getTcla());
			if (r == null) 
				cargar();
			else JOptionPane.showConfirmDialog(
					new JFrame(), 
					"La reserva que busca no existe", 
					"Reseva no encontrada", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.INFORMATION_MESSAGE);
		} else if (eve.getActionCommand().equalsIgnoreCase("buscar todo")) {
			r = mr.buscar(m.getCdisponible().getSelectedItem().toString());
			cargar();
		} else if (eve.getActionCommand().equalsIgnoreCase("guardar")) {
			if (preguntar("guardar información de")) {
				guardar();
				String mensaje = "Felicidades, el vehículo ha sido ";
				if (mr.buscar(r.getCodigo()) == null) {
					mr.crear(r);
					mensaje = "agregado";
				} else {
					mr.modificar(r);
					mensaje = "actualizado";
				}
				JOptionPane.showConfirmDialog(
						new JFrame(), 
						mensaje, 
						"Acción exitosa", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.INFORMATION_MESSAGE);
				limpiar();
				cargar_datos();
			}
		} else if (eve.getActionCommand().equalsIgnoreCase("eliminar")) {
			String n = m.getTcla(), mensaje;
			if (mr.buscar(n) != null) {
				mr.eliminar(n);
				mensaje = "La reserva ha sido eliminada con éxito";
			} else mensaje = "El código de la reserva no existe";
			JOptionPane.showConfirmDialog(
					new JFrame(), 
					mensaje, 
					"Mensaje informativo", 
					JOptionPane.PLAIN_MESSAGE, 
					JOptionPane.INFORMATION_MESSAGE);
		} else if (eve.getActionCommand().equalsIgnoreCase("crear")) {
			guardar();
			String mensaje;
			if (preguntar("crear")) {
				mr.crear(r);
				dar_codigo();
				limpiar();
				m.getCtipos().removeAllItems();
				cargar_datos();
				mensaje = "La reserva ha sido creada con éxito";
			} else mensaje = "La reserva no se ha podido crear";
			JOptionPane.showConfirmDialog(
					new JFrame(), 
					mensaje, 
					"Mensaje informativo", 
					JOptionPane.PLAIN_MESSAGE, 
					JOptionPane.INFORMATION_MESSAGE);
		} else if (eve.getActionCommand().equalsIgnoreCase("consultar")) {
			r = mr.buscar(m.getCdisponible().getSelectedItem().toString());
			System.out.println(r.getCodigo());
			System.out.println(r.getCedula_cliente());
			System.out.println(r.getPlaca_vehiculo());
			System.out.println(r.getFecha_alquila());
			System.out.println(r.getFecha_devolucion());
			m.consultar_reservas();
			cargar();
		} else if (eve.getActionCommand().equalsIgnoreCase("volver")) {
			m.crear_reservas();
		}
	}
	
	public void cargar_datos() {
		m.getCdisponible().removeAllItems();
		ArrayList<Reserva> lista = mr.listar();
		if (m.getUsuario().getTipo().equals("0001")) {
			for (Reserva reserva : lista) {
				if (reserva.getCedula_cliente().equals(m.getUsuario().getCedula()))
					m.getCdisponible().addItem(reserva.getCodigo());
			}
		} else {
			for (Reserva reserva : lista) {
				m.getCdisponible().addItem(reserva.getCodigo());
			}
		}
		try {
			Modelo_cliente clientes = new Modelo_cliente();
			m.getCusuarios().removeAllItems();
			ArrayList<Cliente> lista_clientes = clientes.listar_clientes();
			for (Cliente cliente : lista_clientes) {
				m.getCusuarios().addItem(cliente.getCedula());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/*
		 * Modelo_vehiculo vehiculos = new Modelo_vehiculo(); ArrayList<Vehiculo>
		 * lista_vehiculos = vehiculos.listar(); for (Vehiculo vehiculo :
		 * lista_vehiculos) { m.getCtipos().addItem(vehiculo.getPlaca()); }
		 */
	}
	
	public void cargar_vehiculos() {
		boolean verdad = true;
		Date p = Date.valueOf(m.getTfeci());
		Date s = Date.valueOf(m.getTfecf());
		verdad = (p.before(s)) ? true : false;
		//System.out.println(verdad);
		if (verdad) {
			ArrayList<Vehiculo> lista = mr.listar_autos_disponibles(p, s);
			if (verdad) {
				if (!lista.isEmpty()) {
					m.getCtipos().removeAllItems();
					for (Vehiculo vehiculo : lista) {
						//System.out.println("Placa: " + vehiculo.getPlaca());
						if (vehiculo.isDisponible())
							m.getCtipos().addItem(vehiculo.getPlaca());
					}
				} else System.out.println("No hay nadie hoy");
			}
		}
	}
	
	public boolean validar_fechas(String fecha) {
		boolean verdad = true;
		if (fecha.length() == 10) 
			for (int i = 0; i < fecha.length(); i++) 
				if (verdad) 
					if (i == 4 || i == 7) 
						verdad = (fecha.charAt(i) == '-') ? true : false;
					else 
						verdad = Character.isDigit(fecha.charAt(i)) ? true : false;
				else break;
		if (verdad) {
			try {
				Date.valueOf(fecha);
			} catch (Exception e) {
				// TODO: handle exception
				verdad = false;
			}
		}
		return verdad;
	}
	
	public void validar_para_clientes() {
		if (!m.getTcla().isBlank() && 
				m.getTfeci().length() == 10 && 
				m.getTfecf().length() == 10 && 
				m.getCtipos().getItemCount() > 0) {
			m.aparecer_guardar();
		} else m.desaparecer_guardar();
		if (validar_fechas(m.getTfeci()) && 
				validar_fechas(m.getTfecf())) {
			cargar_vehiculos();
		}
	}
	
	public void validar() {
		if (m.getCdisponible().getItemCount() > 0) {
			m.aparecer_consultar();
		} else m.desaparecer_consultar();
		if (!m.getTcla().isBlank()) {
			m.aparecer_eliminar();
		} else m.desaparecer_eliminar();
		if (!m.getTcla().isBlank() && 
				m.getTfeci().length() == 10 && 
				m.getTfecf().length() == 10 && 
				m.getCusuarios().getItemCount() > 0 && 
				m.getCtipos().getItemCount() > 0) {
			m.aparecer_guardar();
		} else m.desaparecer_guardar();
		if (validar_fechas(m.getTfeci()) && 
				validar_fechas(m.getTfecf())) {
			cargar_vehiculos();
		} 
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (m.getUsuario().getTipo().equals("0001")) validar_para_clientes();
		else validar();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (m.getUsuario().getTipo().equals("0001")) validar_para_clientes();
		else validar();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (m.getUsuario().getTipo().equals("0001")) validar_para_clientes();
		else validar();
	}
}
