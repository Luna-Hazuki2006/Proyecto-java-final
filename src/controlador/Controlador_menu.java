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
import vista.Principal;

public class Controlador_menu implements ActionListener, DocumentListener, KeyListener {
	
	private Modelo_cliente mc;
	private Principal p;
	private Cliente c;

	public Controlador_menu(Principal pr) {
		// TODO Auto-generated constructor stub
		mc = new Modelo_cliente();
		c = new Cliente(null, null, null, null, null, null, null);
		p = pr;
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		// TODO Auto-generated method stub
		if (eve.getActionCommand().equalsIgnoreCase("salir")) {
			System.exit(0);
		} else if (eve.getActionCommand().equalsIgnoreCase("como cliente")) {
			c = mc.buscar(p.getCclientes().getSelectedItem().toString());
			procedimiento("cliente");
		} else if (eve.getActionCommand().equalsIgnoreCase("como empleado")) {
			c = mc.buscar(p.getCempleados().getSelectedItem().toString());
			procedimiento("empleado");
		} else if (eve.getActionCommand().equalsIgnoreCase("como administrador")) {
			c = mc.buscar(p.getCadministradores().getSelectedItem().toString());
			procedimiento("administrador");
		} else if (eve.getActionCommand().equalsIgnoreCase("como nuevo usuario")) {
			p.registro();
			p.setTitulo("Registro de usuario");
		}
		/*
			 * else if (eve.getActionCommand().equalsIgnoreCase("como nuevo cliente")) {
			 * p.registro(); p.setTitulo("Registro de cliente"); } else if
			 * (eve.getActionCommand().equalsIgnoreCase("como nuevo empleado")) {
			 * p.registro(); p.setTitulo("Registro de empleado"); } else if
			 * (eve.getActionCommand().equalsIgnoreCase("como nuevo administrador")) {
			 * p.registro(); p.setTitulo("Registro de administrador"); }
			 */ else if (eve.getActionCommand().equalsIgnoreCase("volver")) {
			p.inicio();
		} else if (eve.getActionCommand().equalsIgnoreCase("registrar")) {
			c.setCedula(p.getTced());
			c.setNombres(p.getTnom());
			c.setApellidos(p.getTape());
			c.setDireccion(p.getTdir());
			c.setTelefono(p.getTtel());
			c.setClave(p.getTcla());
			/*
			 * if (p.getTitulo().contains("clientes")) c.setTipo("0001"); else if
			 * (p.getTitulo().contains("empleado")) c.setTipo("0002"); else if
			 * (p.getTitulo().contains("administrador")) c.setTipo("0003");
			 */
			c.setTipo(mc.dar_codigo_tipo(p.getCtipos().getSelectedItem().toString()));
			boolean verdad = mc.crear(c);
			if (verdad) {
				JOptionPane.showConfirmDialog(
						new JFrame(), 
						"Registro completado", 
						"El usuario ha sido registrado", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.INFORMATION_MESSAGE);
				Modulos m = new Modulos("Beta", c);
				m.setVisible(true);
				p.dispose();
			}
			
		}
	}
	
	public void procedimiento(String tipo) {
		String clave = preguntar_clave(tipo + " " + c.getNombres() + " " + c.getApellidos());
		if (clave == null) {
			System.out.println("Better luck next time");
		} else if (clave.equals(c.getClave())) {
			Modulos m = new Modulos("Beta", c);
			m.setVisible(true);
			p.dispose();
		} else mostrar();
	}
	
	public String preguntar_clave(String usuario) {
		String banana = JOptionPane.showInputDialog(
				new JFrame(), 
				"Ingrese la clave del " + usuario, 
				"Inicio de sesión", 
				JOptionPane.QUESTION_MESSAGE);
		System.out.println(banana);
		return banana;
	}
	
	public void mostrar() {
		JOptionPane.showConfirmDialog(
				new JFrame(), 
				"La clave no es correcta", 
				"Error de la clave", 
				JOptionPane.PLAIN_MESSAGE, 
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void llenar_usuarios() {
		ArrayList<Cliente> usuarios = mc.listar_clientes();
		for (Cliente c : usuarios) 
			p.getCclientes().addItem(c.getCedula());
		if (usuarios.isEmpty()) p.desaparecer_cliente();
		else p.aparecer_cliente();
		usuarios = mc.listar_empleados();
		for (Cliente c : usuarios) 
			p.getCempleados().addItem(c.getCedula());
		if (usuarios.isEmpty()) p.desaparecer_empleado();
		else p.aparecer_empleado();
		usuarios = mc.listar_administradores();
		for (Cliente c : usuarios) 
			p.getCadministradores().addItem(c.getCedula());
		if (usuarios.isEmpty()) p.desaparecer_administrador();
		else p.aparecer_administrador();
	}
	
	public void llenar_tipos() {
		ArrayList<String> tipos = mc.dar_nombres_tipos();
		for (String string : tipos) {
			p.getCtipos().addItem(string);
		}
	}
	
	public void validar() {
		if (!p.getTced().isBlank() && 
				!p.getTnom().isBlank() && 
				!p.getTape().isBlank() && 
				!p.getTdir().isBlank() && 
				!p.getTtel().isBlank() && 
				!p.getTcla().isBlank()) {
			if (mc.buscar(p.getTced()) == null) {
				p.aparecer_registrar();
			} else p.desaparecer_registrar();
		} else p.desaparecer_registrar();
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
		if (e.getSource().equals(p.revisar_cedula()) && 
				!Character.isDigit(e.getKeyChar())) {
			e.consume();
		} else if ((e.getSource().equals(p.revisar_nombres()) || 
				e.getSource().equals(p.revisar_apellidos())) && 
				!Character.isAlphabetic(e.getKeyChar()) && 
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
