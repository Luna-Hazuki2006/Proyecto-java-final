package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import clases.Vehiculo;
import modelo.Modelo_reportes;
import vista.Modulos;

public class Controlador_reportes implements ActionListener, DocumentListener {
	
	private Modulos m;
	private Modelo_reportes mr;
	private ArrayList<Vehiculo> lista;

	public Controlador_reportes(Modulos mo) {
		// TODO Auto-generated constructor stub
		m = mo;
		mr = new Modelo_reportes();
		lista = new ArrayList<Vehiculo>();
	}
	
	public void limpiar() {
		m.setTfeci("####-##-##");
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		// TODO Auto-generated method stub
		if (eve.getActionCommand().equalsIgnoreCase("limpiar")) {
			limpiar();
		}
	}
	
	public void listar() {
		m.getReportes().setModel(datos());
	}
	
	public void original() {
		lista = new ArrayList<Vehiculo>();
	}
	
	public void listar_no_retornados() {
		lista = mr.listar_vehiculos_no_retornados();
		listar();
	}
	
	public TableModel datos() {
		TableModel datos = new AbstractTableModel() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				String dato;
				switch (columnIndex) {
				case 0:
					dato = lista.get(rowIndex).getPlaca();
					break;
				case 1:
					dato = lista.get(rowIndex).getMarca();
					break;
				case 2:
					dato = lista.get(rowIndex).getModelo();
					break;
				case 3:
					dato = lista.get(rowIndex).getColor();
					break;
				case 4:
					dato = String.valueOf(lista.get(rowIndex).getPrecio_dia());
					break;
				case 5:
					dato = String.valueOf(lista.get(rowIndex).getKilometraje());
					break;
				default:
					dato = null;
					break;
				}
				return dato;
			}
			
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return lista.size();
			}
			
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 6;
			}
			
			@Override
			public String getColumnName(int column) {
				// TODO Auto-generated method stub
				String dato;
				switch (column) {
				case 0:
					dato = "Placa";
					break;
				case 1:
					dato = "Marca";
					break;
				case 2:
					dato = "Modelo";
					break;
				case 3:
					dato = "Color";
					break;
				case 4:
					dato = "Precio por día";
					break;
				case 5:
					dato = "Kilometraje";
					break;
				default:
					dato = super.getColumnName(column);
					break;
				}
				return dato;
			}
		};
		return datos;
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
	
	public void validar() {
		if (validar_fechas(m.getTfeci())) {
			lista = mr.listar_vehiculos_fecha(Date.valueOf(m.getTfeci()));
		} else original();
		listar();
		
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
	
	

}
