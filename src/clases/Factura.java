package clases;

import java.sql.Date;

public class Factura {
	
	private String codigo, codigo_reserva;
	private Date fecha;

	public Factura(String codigo, String codigo_reserva, Date fecha) {
		super();
		setCodigo(codigo);
		setCodigo_reserva(codigo_reserva);
		setFecha(fecha);
	}
	
	public String getCodigo() {return codigo;}
	public void setCodigo(String codigo) {this.codigo = codigo;}

	public String getCodigo_reserva() {return codigo_reserva;}
	public void setCodigo_reserva(String codigo_reserva) {this.codigo_reserva = codigo_reserva;}

	public Date getFecha() {return fecha;}
	public void setFecha(Date fecha) {this.fecha = fecha;}

}
