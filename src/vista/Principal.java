package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.Controlador_menu;

public class Principal extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel norte, centro, sur, x;
	private JComboBox<String> cclientes, cempleados, cadministradores, ctipos;
	private JButton bclientess, bempleadoss, badministradoress,
			/* bclientesr, bempleadosr, badministradoresr, */ iregistrar, salir, registrar;
	private JLabel sesion, registro, titulo, lced, lnom, lape, ldir, ltel, lcla;
	private JTextField tced, tnom, tape, tdir, ttel, tcla;
	private Controlador_menu cm;
	
	public JComboBox<String> getCclientes() {
		return cclientes;
	}

	public void setCclientes(JComboBox<String> cclientes) {
		this.cclientes = cclientes;
	}

	public JComboBox<String> getCempleados() {
		return cempleados;
	}

	public void setCempleados(JComboBox<String> cempleados) {
		this.cempleados = cempleados;
	}

	public JComboBox<String> getCadministradores() {
		return cadministradores;
	}

	public void setCadministradores(JComboBox<String> cadministradores) {
		this.cadministradores = cadministradores;
	}
	
	public JTextField revisar_cedula() {return tced;}
	
	public JTextField revisar_nombres() {return tnom;}
	
	public JTextField revisar_apellidos() {return tape;}
	
	public String getTced() {return tced.getText();}

	public void setTced(String tced) {this.tced.setText(tced);}

	public String getTnom() {return tnom.getText();}

	public void setTnom(String tnom) {this.tnom.setText(tnom);}

	public String getTape() {return tape.getText();}

	public void setTape(String tape) {this.tape.setText(tape);}

	public String getTdir() {return tdir.getText();}

	public void setTdir(String tdir) {this.tdir.setText(tdir);}

	public String getTtel() {return ttel.getText();}

	public void setTtel(String ttel) {this.ttel.setText(ttel);}

	public String getTcla() {return tcla.getText();}

	public void setTcla(String tcla) {this.tcla.setText(tcla);}

	public void aparecer_registrar() {
		registrar.setEnabled(true);
	}
	
	public void desaparecer_registrar() {
		registrar.setEnabled(false);
	}
	
	public void aparecer_cliente() {
		bclientess.setEnabled(true);
	}
	
	public void desaparecer_cliente() {
		bclientess.setEnabled(false);
	}
	
	public void aparecer_empleado() {
		bempleadoss.setEnabled(true);
	}
	
	public void desaparecer_empleado() {
		bempleadoss.setEnabled(false);
	}
	
	public void aparecer_administrador() {
		badministradoress.setEnabled(true);
	}
	
	public void desaparecer_administrador() {
		badministradoress.setEnabled(false);
	}

	public String getTitulo() {
		return titulo.getText();}

	public void setTitulo(String titulo) {
		this.titulo.setText(titulo);
	}

	public void preparar() {
		remove(norte);
		remove(centro);
		remove(sur);
	}
	
	public void registro() {
		preparar();
		norte = new JPanel();
		Cuztom.Panel(norte);
		centro = new JPanel(new GridLayout(6, 1));
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		titulo = new JLabel("Registro de usuario");
		Cuztom.Label(titulo);
		lced = new JLabel("Cédula:");
		Cuztom.Label(lced);
		lnom = new JLabel("Nombres:");
		Cuztom.Label(lnom);
		lape = new JLabel("Apellidos:");
		Cuztom.Label(lape);
		ldir = new JLabel("Dirección:");
		Cuztom.Label(ldir);
		ltel = new JLabel("Teléfono:");
		Cuztom.Label(ltel);
		lcla = new JLabel("Contraseña:");
		ctipos = new JComboBox<String>();
		Cuztom.Label(lcla);
		
		tced = new JTextField(10);
		Cuztom.Texto(tced);
		tnom = new JTextField(10);
		Cuztom.Texto(tnom);
		tape = new JTextField(10);
		Cuztom.Texto(tape);
		tdir = new JTextField(10);
		Cuztom.Texto(tdir);
		ttel = new JTextField(10);
		Cuztom.Texto(ttel);
		tcla = new JTextField(10);
		Cuztom.Texto(tcla);
		
		registrar = new JButton("Registrar");
		Cuztom.Boton(registrar);
		iregistrar = new JButton("Registrar sesión");
		Cuztom.Boton(iregistrar);
		salir = new JButton("Volver");
		Cuztom.Boton(salir);
		
		norte.add(titulo);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lced);
		x.add(tced);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lnom);
		x.add(tnom);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lape);
		x.add(tape);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(ldir);
		x.add(tdir);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(ltel);
		x.add(ttel);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lcla);
		x.add(tcla);
		centro.add(x);
		
		sur.add(registrar);
		sur.add(salir);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		cm = new Controlador_menu(this);
		salir.addActionListener(cm);
		registrar.addActionListener(cm);
		tced.getDocument().addDocumentListener(cm);
		tnom.getDocument().addDocumentListener(cm);
		tape.getDocument().addDocumentListener(cm);
		tdir.getDocument().addDocumentListener(cm);
		ttel.getDocument().addDocumentListener(cm);
		tcla.getDocument().addDocumentListener(cm);
		tced.addKeyListener(cm);
		tnom.addKeyListener(cm);
		tape.addKeyListener(cm);
		cm.validar();
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void inicio() {
		preparar();
		norte = new JPanel();
		Cuztom.Panel(norte);
		centro = new JPanel(new GridLayout(4, 2));
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		cclientes = new JComboBox<String>();
		Cuztom.Combo(cclientes);
		cempleados = new JComboBox<String>();
		Cuztom.Combo(cempleados);
		cadministradores = new JComboBox<String>();
		Cuztom.Combo(cadministradores);
		
		bclientess = new JButton("Como cliente");
		Cuztom.Boton(bclientess);
		bempleadoss = new JButton("Como empleado");
		Cuztom.Boton(bempleadoss);
		badministradoress = new JButton("Como administrador");
		Cuztom.Boton(badministradoress);
		/*
		 * bclientesr = new JButton("Como nuevo cliente"); Cuztom.Boton(bclientesr);
		 * bempleadosr = new JButton("Como nuevo empleado"); Cuztom.Boton(bempleadosr);
		 * badministradoresr = new JButton("Como nuevo administrador");
		 * Cuztom.Boton(badministradoresr);
		 */
		salir = new JButton("Salir");
		Cuztom.Boton(salir);
		
		sesion = new JLabel("Iniciar Sesión:");
		Cuztom.Label(sesion);
		registro = new JLabel("Registrar Usuario:");
		Cuztom.Label(registro);
		titulo = new JLabel("Página de inicio");
		Cuztom.Label(titulo);
		
		norte.add(titulo);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(sesion);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(registro);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(cclientes);
		x.add(bclientess);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		/* x.add(bclientesr); */
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(cempleados);
		x.add(bempleadoss);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		/* x.add(bempleadosr); */
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(cadministradores);
		x.add(badministradoress);
		centro.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		/* x.add(badministradoresr); */
		centro.add(x);
		
		sur.add(salir);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		cm = new Controlador_menu(this);
		cm.llenar();
		salir.addActionListener(cm);
		bclientess.addActionListener(cm);
		/* bclientesr.addActionListener(cm); */
		bempleadoss.addActionListener(cm);
		/* bempleadosr.addActionListener(cm); */
		badministradoress.addActionListener(cm);
		/* badministradoresr.addActionListener(cm); */
		
		pack();
		setLocationRelativeTo(null);
	}

	public Principal(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		norte = new JPanel();
		Cuztom.Panel(norte);
		centro = new JPanel();
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		inicio();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
