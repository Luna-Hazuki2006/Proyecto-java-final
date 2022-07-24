package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import clases.Cliente;
import controlador.Controlador_modulos;
import controlador.Controlador_reportes;
import controlador.Controlador_reservas;
import controlador.Controlador_usuarios;
import controlador.Controlador_vehiculos;

public class Modulos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cliente usuario = null;
	private JPanel norte, centro, sur, x;
	private JMenuBar menu;
	private JMenu mclientes, mvehiculos, mreservas, malquileres, mreportes, mvolver/*, merrores*/;
	private JMenuItem iclientes, ireservas, ivehiculos, ialquileres, irepa, irepb, ivolver/*, imultas, iincidencias*/;
	private JLabel lced, lnom, lape, ldir, ltel, ltip, lcla, ltodos, lpla, lcol, lpre, lkil, ldis, lfeci, lfecf;
	private JTextField tced, tnom, tape, tdir, ttel, tcla, tpla, tcol, tpre, tkil;
	private JFormattedTextField tfeci, tfecf;
	private JComboBox<String> ctipos, cusuarios, cdisponible;
	private JButton volver, eliminar, guardar, limpiar, buscar, consultar, filtrar;
	private JTable reportes;
	private JScrollPane dimension;
	private Controlador_modulos cm;
	private Controlador_usuarios cu;
	private Controlador_vehiculos cv;
	private Controlador_reservas cres;
	private Controlador_reportes crep;
	
	public String getTfeci() {return tfeci.getText();}
	public void setTfeci(String tfeci) {this.tfeci.setText(tfeci);}
	
	public String getTfecf() {return tfecf.getText();}
	public void setTfecf(String tfecf) {this.tfecf.setText(tfecf);}
	
	public Cliente getUsuario() {return usuario;}
	public void setUsuario(Cliente u) {usuario = u;}
	
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
	
	public String getTpla() {return tpla.getText();}
	public void setTpla(String tpla) {this.tpla.setText(tpla);}
	
	public String getTcol() {return tcol.getText();}
	public void setTcol(String tcol) {this.tcol.setText(tcol);}
	
	public String getTpre() {return tpre.getText();}
	public void setTpre(String tpre) {this.tpre.setText(tpre);}
	
	public String getTkil() {return tkil.getText();}
	public void setTkil(String tkil) {this.tkil.setText(tkil);}
	
	public JTextField revisar_cedula() {
		return tced;
	}
	
	public JTextField revisar_nombres() {
		return tnom;
	}
	
	public JTextField revisar_apellidos() {
		return tape;
	}
	
	public JFormattedTextField revisar_fecha_inicio() {
		return tfeci;
	}
	
	public JFormattedTextField revisar_fecha_fin() {
		return tfecf;
	}
	
	public JComboBox<String> getCtipos() {
		return ctipos;
	}
	public void setCtipos(JComboBox<String> ctipos) {
		this.ctipos = ctipos;
	}
	public JComboBox<String> getCusuarios() {
		return cusuarios;
	}
	public void setCusuarios(JComboBox<String> cusuarios) {
		this.cusuarios = cusuarios;
	}
	public JComboBox<String> getCdisponible() {
		return cdisponible;
	}
	public void setCdisponible(JComboBox<String> cdisponible) {
		this.cdisponible = cdisponible;
	}
	public void aparecer_buscar() {
		buscar.setEnabled(true);
	}
	
	public void desaparecer_buscar() {
		buscar.setEnabled(false);
	}
	
	public void aparecer_consultar() {
		consultar.setEnabled(true);
	}
	
	public void desaparecer_consultar() {
		consultar.setEnabled(false);
	}
	
	public void aparecer_guardar() {
		guardar.setEnabled(true);
	}
	
	public void desaparecer_guardar() {
		guardar.setEnabled(false);
	}
	
	public void aparecer_eliminar() {
		eliminar.setEnabled(true);
	}
	
	public void desaparecer_eliminar() {
		eliminar.setEnabled(false);
	}
	
	public void aparecer_filtrar() {
		filtrar.setEnabled(true);
	}
	
	public void desaparecer_filtrar() {
		filtrar.setEnabled(false);
	}
	
	public void existe_eliminar() {
		eliminar.setVisible(true);
	}
	
	public void inexiste_eliminar() {
		eliminar.setVisible(false);
	}
	
	public void existe_filtrar() {
		filtrar.setVisible(true);
	}
	
	public void inexiste_filtrar() {
		filtrar.setVisible(false);
	}
	
	public JTable getReportes() {
		return reportes;
	}
	
	public void setReportes(JTable reportes) {
		this.reportes = reportes;
	}
	
	public void preparar() {
		remove(norte);
		remove(centro);
		remove(sur);
	}
	
	public void preparo_previo() {
		menu = new JMenuBar();
		Cuztom.Barra(menu);
		setJMenuBar(menu);
	}
	
	public void preparar_cliente() {
		preparo_previo();
		
		mreservas = new JMenu("Reservas");
		Cuztom.Menu(mreservas);
		mvolver = new JMenu("Volver");
		Cuztom.Menu(mvolver);
		
		ireservas = new JMenuItem("Reservas");
		Cuztom.Opcion(ireservas);
		ivolver = new JMenuItem("Volver");
		Cuztom.Opcion(ivolver);
		
		menu.add(mreservas);
		menu.add(mvolver);
		
		mreservas.add(ireservas);
		mvolver.add(ivolver);
		
		ireservas.addActionListener(cm);
		ivolver.addActionListener(cm);
	}
	
	public void preparar_empleado() {
		preparo_previo();
		
		mclientes = new JMenu("Clientes");
		Cuztom.Menu(mclientes);
		mreservas = new JMenu("Reservas");
		Cuztom.Menu(mreservas);
		malquileres = new JMenu("Alquileres");
		Cuztom.Menu(malquileres);
		mreportes = new JMenu("Reportes");
		Cuztom.Menu(mreportes);
		mvolver = new JMenu("Volver");
		Cuztom.Menu(mvolver);
		
		iclientes = new JMenuItem("Clientes");
		Cuztom.Opcion(iclientes);
		ireservas = new JMenuItem("Reservas");
		Cuztom.Opcion(ireservas);
		ialquileres = new JMenuItem("Alquileres");
		Cuztom.Opcion(ialquileres);
		irepa = new JMenuItem("Vehículos disponibles");
		Cuztom.Opcion(irepa);
		irepb = new JMenuItem("Vehículos no retornados");
		Cuztom.Opcion(irepb);
		ivolver = new JMenuItem("Volver");
		Cuztom.Opcion(ivolver);
		
		menu.add(mclientes);
		menu.add(mreservas);
		menu.add(malquileres);
		menu.add(mreportes);
		menu.add(mvolver);
		
		mclientes.add(iclientes);
		mreservas.add(ireservas);
		malquileres.add(ialquileres);
		mreportes.add(irepa);
		mreportes.add(irepb);
		mvolver.add(ivolver);
		
		iclientes.addActionListener(cm);
		ireservas.addActionListener(cm);
		ialquileres.addActionListener(cm);
		irepa.addActionListener(cm);
		irepb.addActionListener(cm);
		ivolver.addActionListener(cm);
	}
	
	public void preparar_administrador() {
		preparo_previo();
		
		mclientes = new JMenu("Usuarios");
		Cuztom.Menu(mclientes);
		mvehiculos = new JMenu("Vehículos");
		Cuztom.Menu(mvehiculos);
		mreservas = new JMenu("Reservas");
		Cuztom.Menu(mreservas);
		malquileres = new JMenu("Alquileres");
		Cuztom.Menu(malquileres);
		mreportes = new JMenu("Reportes");
		Cuztom.Menu(mreportes);
		mvolver = new JMenu("Volver");
		Cuztom.Menu(mvolver);
		
		iclientes = new JMenuItem("Usuarios");
		Cuztom.Opcion(iclientes);
		ivehiculos = new JMenuItem("Vehículos");
		Cuztom.Opcion(ivehiculos);
		ireservas = new JMenuItem("Reservas");
		Cuztom.Opcion(ireservas);
		ialquileres = new JMenuItem("Alquileres");
		Cuztom.Opcion(ialquileres);
		irepa = new JMenuItem("Vehículos disponibles");
		Cuztom.Opcion(irepa);
		irepb = new JMenuItem("Vehículos no retornados");
		Cuztom.Opcion(irepb);
		ivolver = new JMenuItem("Volver");
		Cuztom.Opcion(ivolver);
		
		menu.add(mclientes);
		menu.add(mvehiculos);
		menu.add(mreservas);
		menu.add(malquileres);
		menu.add(mreportes);
		menu.add(mvolver);
		
		mclientes.add(iclientes);
		mvehiculos.add(ivehiculos);
		mreservas.add(ireservas);
		malquileres.add(ialquileres);
		mreportes.add(irepa);
		mreportes.add(irepb);
		mvolver.add(ivolver);
		
		iclientes.addActionListener(cm);
		ivehiculos.addActionListener(cm);
		ireservas.addActionListener(cm);
		ialquileres.addActionListener(cm);
		irepa.addActionListener(cm);
		irepb.addActionListener(cm);
		ivolver.addActionListener(cm);
	}
	
	public void reportes_a() {
		preparar();
		norte = new JPanel(new GridLayout(1, 2));
		Cuztom.Panel(norte);
		centro = new JPanel();
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		lfeci = new JLabel("Filtrar por fecha:");
		Cuztom.Label(lfeci);
		ltodos = new JLabel("Cantidad:");
		Cuztom.Label(ltodos);
		
		tfeci = new JFormattedTextField("####-##-##");
		Cuztom.Texto(tfeci);
		tpre = new JTextField(3);
		Cuztom.Texto(tpre);
		tpre.setEditable(false);
		
		limpiar = new JButton("Limpiar");
		Cuztom.Boton(limpiar);
		
		reportes = new JTable();
		Cuztom.Tabla(reportes);
		reportes.getTableHeader().setBackground(Cuztom.LAVENDER);
		
		dimension = new JScrollPane(reportes);
		Cuztom.Dimension(dimension);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lfeci);
		x.add(tfeci);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		Cuztom.Panel(x);
		x.add(ltodos);
		x.add(tpre);
		norte.add(x);
		
		centro.add(dimension);
		
		sur.add(limpiar);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		crep = new Controlador_reportes(this);
		tfeci.getDocument().addDocumentListener(crep);
		crep.validar();
		limpiar.addActionListener(crep);

		pack();
		setLocationRelativeTo(null);
	}
	
	public void reportes_b() {
		preparar();
		
		norte = new JPanel();
		Cuztom.Panel(norte);
		centro = new JPanel();
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		reportes = new JTable();
		Cuztom.Tabla(reportes);
		reportes.getTableHeader().setBackground(Cuztom.LAVENDER);
		
		dimension = new JScrollPane(reportes);
		Cuztom.Dimension(dimension);
		
		crep = new Controlador_reportes(this);
		crep.listar_no_retornados();
		
		centro.add(dimension);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void alquilar() {
		preparar();
		
		norte = new JPanel();
		Cuztom.Panel(norte);
		centro = new JPanel();
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void preparar_reservas() {
		preparar();
		
		norte = new JPanel(new GridLayout(5, 1));
		Cuztom.Panel(norte);
		centro = new JPanel();
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		ldis = new JLabel("Reservas:");
		Cuztom.Label(ldis);
		lcla = new JLabel("Código:");
		Cuztom.Label(lcla);
		lpla = new JLabel("Placa:");
		Cuztom.Label(lpla);
		lfeci = new JLabel("Fecha de alquiler:");
		Cuztom.Label(lfeci);
		lfecf = new JLabel("Fecha de devolución:");
		Cuztom.Label(lfecf);
		
		tcla = new JTextField();
		Cuztom.Texto(tcla);
		tpla = new JTextField();
		Cuztom.Texto(tpla);
		
		ctipos = new JComboBox<String>();
		Cuztom.Combo(ctipos);
		cdisponible = new JComboBox<String>();
		Cuztom.Combo(cdisponible);
		
		tfeci = new JFormattedTextField("####-##-##");
		Cuztom.Texto(tfeci);
		tfecf = new JFormattedTextField("####-##-##");
		Cuztom.Texto(tfecf);
		
		volver = new JButton("Volver");
		Cuztom.Boton(volver);
		guardar = new JButton("Crear");
		Cuztom.Boton(guardar);
		limpiar = new JButton("Limpiar");
		Cuztom.Boton(limpiar);
		buscar = new JButton("Consultar");
		Cuztom.Boton(buscar);
	}
	
	public void consultar_reservas() {
		preparar_reservas();
		
		tcla.setEditable(false);
		tpla.setEditable(false);
		tfeci.setEditable(false);
		tfecf.setEditable(false);
		
		tcla.setText(cres.dar_reserva().getCodigo());
		tpla.setText(cres.dar_reserva().getPlaca_vehiculo());
		tfeci.setText(cres.dar_reserva().getFecha_alquila().toString());
		tfecf.setText(cres.dar_reserva().getFecha_devolucion().toString());
		
		ldis.setText("Reserva de " 
		+ usuario.getNombres() 
		+ " " 
		+ usuario.getApellidos());
		norte.add(ldis);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lcla);
		x.add(tcla);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lfeci);
		x.add(tfeci);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lfecf);
		x.add(tfecf);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lpla);
		x.add(tpla);
		norte.add(x);
		
		sur.add(volver);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		cres = new Controlador_reservas(this);
		volver.addActionListener(cres);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void crear_reservas() {
		preparar_reservas();
		
		tcla.setEditable(false);
		tfeci.setEditable(true);
		tfecf.setEditable(true);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(ldis);
		x.add(cdisponible);
		x.add(buscar);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lcla);
		x.add(tcla);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lfeci);
		x.add(tfeci);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lfecf);
		x.add(tfecf);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lpla);
		x.add(ctipos);
		norte.add(x);
		
		sur.add(guardar);
		sur.add(limpiar);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		cres = new Controlador_reservas(this);
		cres.dar_codigo();
		cres.validar_para_clientes();
		cres.cargar_datos();
		buscar.addActionListener(cres);
		guardar.addActionListener(cres);
		limpiar.addActionListener(cres);
		tfeci.getDocument().addDocumentListener(cres);
		tfecf.getDocument().addDocumentListener(cres);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void reservas() {
		preparar();
		
		norte = new JPanel(new GridLayout(6, 1));
		Cuztom.Panel(norte);
		centro = new JPanel();
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		lnom = new JLabel("Códigos:");
		Cuztom.Label(lnom);
		lcla = new JLabel("Código:");
		Cuztom.Label(lcla);
		lced = new JLabel("Cédula del cliente:");
		Cuztom.Label(lced);
		lpla = new JLabel("Placa del vehículo:");
		Cuztom.Label(lpla);
		lfeci = new JLabel("Fecha de alquiler:");
		Cuztom.Label(lfeci);
		lfecf = new JLabel("Fecha de devolución:");
		Cuztom.Label(lfecf);
		
		cdisponible = new JComboBox<String>();
		Cuztom.Combo(cdisponible);
		tcla = new JTextField(10);
		Cuztom.Texto(tcla);
		cusuarios = new JComboBox<String>();
		Cuztom.Combo(cusuarios);
		ctipos = new JComboBox<String>();
		Cuztom.Combo(ctipos);
		tfeci = new JFormattedTextField("####-##-##");
		Cuztom.Texto(tfeci);
		tfecf = new JFormattedTextField("####-##-##");
		Cuztom.Texto(tfecf);
		
		guardar = new JButton("Crear");
		Cuztom.Boton(guardar);
		buscar = new JButton("Buscar");
		Cuztom.Boton(buscar);
		consultar = new JButton("Buscar todo");
		Cuztom.Boton(consultar);
		limpiar = new JButton("Limpiar");
		Cuztom.Boton(limpiar);
		eliminar = new JButton("Eliminar");
		Cuztom.Boton(eliminar);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lnom);
		x.add(cdisponible);
		x.add(consultar);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lcla);
		x.add(tcla);
		x.add(buscar);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lced);
		x.add(cusuarios);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lfeci);
		x.add(tfeci);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lfecf);
		x.add(tfecf);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lpla);
		x.add(ctipos);
		norte.add(x);
		
		sur.add(guardar);
		sur.add(limpiar);
		sur.add(eliminar);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		cres = new Controlador_reservas(this);
		consultar.addActionListener(cres);
		buscar.addActionListener(cres);
		guardar.addActionListener(cres);
		limpiar.addActionListener(cres);
		eliminar.addActionListener(cres);
		tfeci.getDocument().addDocumentListener(cres);
		tfecf.getDocument().addDocumentListener(cres);
		tcla.getDocument().addDocumentListener(cres);
		cres.cargar_datos();
		cres.validar();
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void vehiculos() {
		preparar();
		norte = new JPanel(new GridLayout(8, 1));
		Cuztom.Panel(norte);
		centro = new JPanel();
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		ltodos = new JLabel("Placas:");
		Cuztom.Label(ltodos);
		lnom = new JLabel("Marca:");
		Cuztom.Label(lnom);
		lape = new JLabel("Modelo:");
		Cuztom.Label(lape);
		lpla = new JLabel("Placa:");
		Cuztom.Label(lpla);
		lcol = new JLabel("Color:");
		Cuztom.Label(lcol);
		lpre = new JLabel("Precio por día:");
		Cuztom.Label(lpre);
		lkil = new JLabel("Kilometraje:");
		Cuztom.Label(lkil);
		ldis = new JLabel("¿Es disponible?");
		Cuztom.Label(ldis);
		
		ctipos = new JComboBox<String>();
		Cuztom.Combo(ctipos);
		cdisponible = new JComboBox<String>(new String[] {"No", "Si"});
		Cuztom.Combo(cdisponible);
		
		tpla = new JTextField(10);
		Cuztom.Texto(tpla);
		tnom = new JTextField(10);
		Cuztom.Texto(tnom);
		tape = new JTextField(10);
		Cuztom.Texto(tape);
		tcol = new JTextField(10);
		Cuztom.Texto(tcol);
		tpre = new JTextField(10);
		Cuztom.Texto(tpre);
		tkil = new JTextField(10);
		Cuztom.Texto(tkil);
		
		guardar = new JButton("Guardar");
		Cuztom.Boton(guardar);
		limpiar = new JButton("Limpiar");
		Cuztom.Boton(limpiar);
		eliminar = new JButton("Eliminar");
		Cuztom.Boton(eliminar);
		buscar = new JButton("Buscar");
		Cuztom.Boton(buscar);
		consultar = new JButton("Buscar todo");
		Cuztom.Boton(consultar);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(ltodos);
		x.add(ctipos);
		x.add(consultar);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lpla);
		x.add(tpla);
		x.add(buscar);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lnom);
		x.add(tnom);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lape);
		x.add(tape);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lcol);
		x.add(tcol);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lpre);
		x.add(tpre);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lkil);
		x.add(tkil);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(ldis);
		x.add(cdisponible);
		norte.add(x);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		sur.add(guardar);
		sur.add(limpiar);
		sur.add(eliminar);
		
		cv = new Controlador_vehiculos(this);
		limpiar.addActionListener(cv);
		eliminar.addActionListener(cv);
		guardar.addActionListener(cv);
		buscar.addActionListener(cv);
		consultar.addActionListener(cv);
		tpla.getDocument().addDocumentListener(cv);
		tcol.getDocument().addDocumentListener(cv);
		tpre.getDocument().addDocumentListener(cv);
		tkil.getDocument().addDocumentListener(cv);
		tpre.addKeyListener(cv);
		tkil.addKeyListener(cv);
		cv.cargar_datos();
		cv.validar();
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void usuarios() {
		preparar();
		norte = new JPanel(new GridLayout(8, 1));
		Cuztom.Panel(norte);
		centro = new JPanel();
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		ltodos = new JLabel("Cédulas:");
		Cuztom.Label(ltodos);
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
		ltip = new JLabel("Tipo de usuario:");
		Cuztom.Label(ltip);
		lcla = new JLabel("Contraseña:");
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
		
		ctipos = new JComboBox<String>();
		Cuztom.Combo(ctipos);
		cusuarios = new JComboBox<String>();
		Cuztom.Combo(cusuarios);
		
		buscar = new JButton("Buscar");
		Cuztom.Boton(buscar);
		consultar = new JButton("Buscar todo");
		Cuztom.Boton(consultar);
		guardar = new JButton("Guardar");
		Cuztom.Boton(guardar);
		limpiar = new JButton("Limpiar");
		Cuztom.Boton(limpiar);
		eliminar = new JButton("Eliminar");
		Cuztom.Boton(eliminar);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(ltodos);
		x.add(cusuarios);
		x.add(consultar);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lced);
		x.add(tced);
		x.add(buscar);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lnom);
		x.add(tnom);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lape);
		x.add(tape);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(ldir);
		x.add(tdir);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(ltel);
		x.add(ttel);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(ltip);
		x.add(ctipos);
		norte.add(x);
		
		x = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Cuztom.Panel(x);
		x.add(lcla);
		x.add(tcla);
		norte.add(x);
		
		sur.add(guardar);
		sur.add(limpiar);
		sur.add(eliminar);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		cu = new Controlador_usuarios(this);
		buscar.addActionListener(cu);
		consultar.addActionListener(cu);
		guardar.addActionListener(cu);
		limpiar.addActionListener(cu);
		eliminar.addActionListener(cu);
		tced.getDocument().addDocumentListener(cu);
		tnom.getDocument().addDocumentListener(cu);
		tape.getDocument().addDocumentListener(cu);
		tdir.getDocument().addDocumentListener(cu);
		ttel.getDocument().addDocumentListener(cu);
		tcla.getDocument().addDocumentListener(cu);
		tced.addKeyListener(cu);
		tnom.addKeyListener(cu);
		tape.addKeyListener(cu);
		cu.revision_para_cargar();
		cu.validar();
		
		pack();
		setLocationRelativeTo(null);
	}

	public Modulos(String title, Cliente u) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		setUsuario(u);
		menu = new JMenuBar();
		Cuztom.Barra(menu);
		setJMenuBar(menu);
		
		setLayout(new BorderLayout());
		norte = new JPanel();
		Cuztom.Panel(norte);
		centro = new JPanel();
		Cuztom.Panel(centro);
		sur = new JPanel();
		Cuztom.Panel(sur);
		
		volver = new JButton("Volver");
		Cuztom.Boton(volver);
		
		sur.add(volver);
		
		add(norte, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(sur, BorderLayout.SOUTH);
		
		cm = new Controlador_modulos(this);
		volver.addActionListener(cm);
		cm.preparar();
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
