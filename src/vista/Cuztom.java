package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class Cuztom {
	
	public static final Color LAVENDERBLUSH = new Color(255, 240, 245);
	public static final Color LAVENDER = new Color(230, 230, 250);
	public static final Color SEASHELL = new Color(255, 245, 238);
	public static final Font BOOK_ANTIQUA = new Font("Book Antiqua", Font.PLAIN, 16);
	public static final Color MINT_CREAM = new Color(245, 255, 250);

	public Cuztom() {
		// TODO Auto-generated constructor stub
	}
	
	public static void Combo(JComboBox<String> c) {
		c.setFont(BOOK_ANTIQUA);
		c.setBackground(SEASHELL);
	}
	
	public static void Boton(JButton b) {
		b.setFont(BOOK_ANTIQUA);
		b.setBackground(LAVENDER);
	}
	
	public static void Texto(JTextField t) {
		t.setFont(BOOK_ANTIQUA);
		t.setBackground(SEASHELL);
	}
	
	public static void Panel(JPanel p) {
		p.setBackground(LAVENDERBLUSH);
	}
	
	public static void Label(JLabel l) {
		l.setFont(BOOK_ANTIQUA);
	}
	
	public static void Menu(JMenu m) {
		m.setFont(BOOK_ANTIQUA);
		m.setBackground(MINT_CREAM);
	}
	
	public static void Barra(JMenuBar b) {
		b.setFont(BOOK_ANTIQUA);
		b.setBackground(MINT_CREAM);
	}
	
	public static void Opcion(JMenuItem i) {
		i.setFont(BOOK_ANTIQUA);
		i.setBackground(MINT_CREAM);
	}
	
	public static void Area(JTextArea a) {
		a.setFont(BOOK_ANTIQUA);
		a.setBackground(SEASHELL);
	}
	
	public static void Tabla(JTable t) {
		t.setFont(BOOK_ANTIQUA);
		t.setBackground(MINT_CREAM);
	}
	
	public static void Dimension(JScrollPane p) {
		p.setFont(BOOK_ANTIQUA);
		p.setBackground(SEASHELL);
	}

}
