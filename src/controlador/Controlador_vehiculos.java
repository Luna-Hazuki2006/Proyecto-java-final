package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import clases.Vehiculo;
import modelo.Modelo_vehiculo;
import vista.Modulos;

public class Controlador_vehiculos implements ActionListener, DocumentListener, KeyListener {
	
	private Vehiculo v;
	private Modelo_vehiculo mv;
	private Modulos m;

	public Controlador_vehiculos(Modulos mo) {
		// TODO Auto-generated constructor stub
		m = mo;
		mv = new Modelo_vehiculo();
		v = new Vehiculo(null, null, null, null, 0, 0, false);
	}
	
	public void limpiar() {
		m.setTpla(null);
		m.setTnom(null);
		m.setTape(null);
		m.setTcol(null);
		m.setTpre(null);
		m.setTkil(null);
	}
	
	public boolean pregunta(String accion) {
		int r = JOptionPane.showConfirmDialog(
				new JFrame(), 
				"¿Desea " + accion + " este vehículo?", 
				"Pregunta de acceso", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.INFORMATION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) return true;
		return false;
	}
	
	public void cargar() {
		m.setTpla(v.getPlaca());
		m.setTnom(v.getMarca());
		m.setTape(v.getModelo());
		m.setTcol(v.getColor());
		m.setTpre(String.valueOf(v.getPrecio_dia()));
		m.setTkil(String.valueOf(v.getKilometraje()));
		if (v.isDisponible()) {
			m.getCdisponible().setSelectedIndex(1);
		} else {
			m.getCdisponible().setSelectedIndex(0);
		}
	}
	
	public void guardar() {
		v.setPlaca(m.getTpla());
		v.setMarca(m.getTnom());
		v.setModelo(m.getTape());
		v.setColor(m.getTcol());
		v.setPrecio_dia(Double.valueOf(m.getTpre()));
		v.setKilometraje(Double.valueOf(m.getTkil()));
		if (m.getCdisponible().getSelectedIndex() == 1) {
			v.setDisponible(true);
		} else if (m.getCdisponible().getSelectedIndex() == 0) {
			v.setDisponible(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent eve) {
		// TODO Auto-generated method stub
		if (eve.getActionCommand().equalsIgnoreCase("limpiar")) {
			limpiar();
		} else if (eve.getActionCommand().equalsIgnoreCase("eliminar")) {
			if (pregunta("eliminar")) {
				if (mv.buscar(m.getTpla()) != null) {
					mv.eliminar(m.getTpla());
					limpiar();
					cargar_datos();
				}
			}
		} else if (eve.getActionCommand().equalsIgnoreCase("guardar")) {
			if (pregunta("guardar información de")) {
				guardar();
				String mensaje = "Felicidades, el vehículo ha sido ";
				if (mv.buscar(v.getPlaca()) == null) {
					mv.crear(v);
					mensaje = "agregado";
				} else {
					mv.actualizar(v);
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
		} else if (eve.getActionCommand().equalsIgnoreCase("buscar")) {
			v = mv.buscar(m.getTpla());
			if (v != null) {
				cargar();
			} else JOptionPane.showConfirmDialog(
						new JFrame(), 
						"El vehículo que busca no existe", 
						"Vehículo no encontrado", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.INFORMATION_MESSAGE);
		} else if (eve.getActionCommand().equalsIgnoreCase("buscar todo")) {
			System.out.println(m.getCtipos().getSelectedItem().toString());
			v = mv.buscar(m.getCtipos().getSelectedItem().toString());
			System.out.println(v.getPlaca());
			cargar();
		}
	}
	
	public void cargar_datos() {
		m.getCtipos().removeAllItems();
		ArrayList<Vehiculo> lista = mv.listar();
		for (Vehiculo vehiculo : lista) {
			m.getCtipos().addItem(vehiculo.getPlaca());
		}
	}
	
	public void validar() {
		if (!m.getTpla().isBlank()) {
			m.aparecer_buscar();
			m.aparecer_eliminar();
		} else {
			m.desaparecer_buscar();
			m.desaparecer_eliminar();
		}
		if (m.getCtipos().getItemCount() > 0) {
			m.aparecer_consultar();
		} else m.desaparecer_consultar();
		if (!m.getTpla().isBlank() && 
				!m.getTnom().isBlank() && 
				!m.getTape().isBlank() && 
				!m.getTcol().isBlank() && 
				!m.getTpre().isBlank() && 
				!m.getTkil().isBlank()) {
			m.aparecer_guardar();
		} else m.desaparecer_guardar();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		validar();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		validar();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		validar();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!Character.isDigit(e.getKeyChar())) {
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
