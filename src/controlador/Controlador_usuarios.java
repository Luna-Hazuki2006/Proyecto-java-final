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

import clases.Cliente;
import modelo.Modelo_cliente;
import vista.Modulos;

public class Controlador_usuarios implements ActionListener, DocumentListener, KeyListener {
	
	private Modulos m;
	private Cliente c;
	private Modelo_cliente mc;

	public Controlador_usuarios(Modulos mo) {
		// TODO Auto-generated constructor stub
		m = mo;
		c = new Cliente(null, null, null, null, null, null, null);
		mc = new Modelo_cliente();
	}
	
	public void limpiar() {
		m.setTced(null);
		m.setTnom(null);
		m.setTape(null);
		m.setTdir(null);
		m.setTtel(null);
		m.getCtipos().setSelectedIndex(0);
		m.setTcla(null);
	}
	
	public boolean pregunta(String accion) {
		int r = JOptionPane.showConfirmDialog(
				new JFrame(), 
				"¿Desea " + accion + " este usuario?", 
				"Pregunta de acceso", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.INFORMATION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) return true;
		return false;
	}
	
	public void guardar() {
		c.setCedula(m.getTced());
		c.setNombres(m.getTnom());
		c.setApellidos(m.getTape());
		c.setDireccion(m.getTdir());
		c.setTelefono(m.getTtel());
		c.setTipo(mc.dar_codigo_tipo(m.getCtipos().getSelectedItem().toString()));
		c.setClave(m.getTcla());
	}
	
	public void cargar() {
		m.setTced(c.getCedula());
		m.setTnom(c.getNombres());
		m.setTape(c.getApellidos());
		m.setTdir(c.getDireccion());
		m.setTtel(c.getTelefono());
		m.getCtipos().setSelectedItem(mc.dar_nombre_tipo(c.getTipo()));
		m.setTcla(c.getClave());
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		// TODO Auto-generated method stub
		if (eve.getActionCommand().equalsIgnoreCase("limpiar")) {
			limpiar();
		} else if (eve.getActionCommand().equalsIgnoreCase("guardar")) {
			if (pregunta("guardar información de")) {
				guardar();
				String mensaje = "Felicidades, el usuario ha sido ";
				if (c.getCedula() == null) {
					mc.crear(c);
					mensaje = "agregado";
				} else {
					mc.actualizar(c);
					mensaje = "actualizado";
				}
				JOptionPane.showConfirmDialog(
						new JFrame(), 
						mensaje, 
						"Acción exitosa", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.INFORMATION_MESSAGE);
				limpiar();
				revision_para_cargar();
			}
		} else if (eve.getActionCommand().equalsIgnoreCase("buscar")) {
			c = mc.buscar(m.getTced());
			if (c != null) {
				cargar();
			} else JOptionPane.showConfirmDialog(
						new JFrame(), 
						"El usuario que busca no existe", 
						"Usuario no encontrado", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.INFORMATION_MESSAGE);
		} else if (eve.getActionCommand().equalsIgnoreCase("buscar todo")) {
			c = mc.buscar(m.getCusuarios().getSelectedItem().toString());
			cargar();
		} else if (eve.getActionCommand().equalsIgnoreCase("eliminar")) {
			if (pregunta("eliminar")) {
				if (mc.buscar(m.getTced()) != null) {
					mc.eliminar(m.getTced());
					JOptionPane.showConfirmDialog(
							new JFrame(), 
							"Felicidades, el usuario ha sido eliminado", 
							"Acción exitosa", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.INFORMATION_MESSAGE);
					limpiar();
					revision_para_cargar();
				}
			}
		}
	}
	
	public void revision_para_cargar() {
		if (m.getUsuario().getTipo().equals(mc.dar_codigo_tipo("empleado"))) {
			cargar_datos_menores();
		} else if (m.getUsuario().getTipo().equals(mc.dar_codigo_tipo("administrador"))) {
			cargar_datos();
		}
	}
	
	public void cargar_datos_menores() {
		m.getCtipos().removeAllItems();
		ArrayList<String> lista = mc.dar_nombres_tipos();
		for (String string : lista) {
			if (string.equals("cliente")) {
				m.getCtipos().addItem(string);
			}
		}
		m.getCusuarios().removeAllItems();
		ArrayList<Cliente> usuarios = mc.listar_usuarios();
		for (Cliente cliente : usuarios) {
			if (cliente.getTipo().equals(mc.dar_codigo_tipo("cliente"))) {
				m.getCusuarios().addItem(cliente.getCedula());
			}
		}
		m.inexiste_eliminar();
	}
	
	public void cargar_datos() {
		m.getCtipos().removeAllItems();
		ArrayList<String> lista = mc.dar_nombres_tipos();
		for (String string : lista) {
			m.getCtipos().addItem(string);
		}
		m.getCusuarios().removeAllItems();
		ArrayList<Cliente> usuarios = mc.listar_usuarios();
		for (Cliente cliente : usuarios) {
			m.getCusuarios().addItem(cliente.getCedula());
		}
		m.existe_eliminar();
	}
	
	public void validar() {
		if (!m.getTced().isBlank()) {
			m.aparecer_buscar();
			m.aparecer_eliminar();
		} else m.desaparecer_buscar();
		if (m.getCusuarios().getItemCount() > 0) {
			m.aparecer_consultar();
		} else m.desaparecer_consultar();
		if (!m.getTced().isBlank() && 
				!m.getTnom().isBlank() && 
				!m.getTape().isBlank() && 
				!m.getTdir().isBlank() && 
				!m.getTtel().isBlank() && 
				!m.getTcla().isBlank()) {
			m.aparecer_guardar();
		} else m.desaparecer_guardar();
	}

	@Override
	public void changedUpdate(DocumentEvent eve) {
		// TODO Auto-generated method stub
		validar();
	}

	@Override
	public void insertUpdate(DocumentEvent eve) {
		// TODO Auto-generated method stub
		validar();
	}

	@Override
	public void removeUpdate(DocumentEvent eve) {
		// TODO Auto-generated method stub
		validar();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(m.revisar_cedula()) && 
				!Character.isDigit(e.getKeyChar())) {
			e.consume();
		} else if (e.getSource().equals(m.revisar_nombres()) && 
				!Character.isLetter(e.getKeyChar()) && 
				e.getKeyChar() != ' ') {
			e.consume();
		} else if (e.getSource().equals(m.revisar_apellidos()) && 
				!Character.isLetter(e.getKeyChar()) && 
				e.getKeyChar() != ' ') {
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
