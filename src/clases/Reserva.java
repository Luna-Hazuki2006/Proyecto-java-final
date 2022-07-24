package clases;

import java.sql.Date;

public class Reserva {
	
	private String codigo, cedula_cliente, placa_vehiculo;
	private Date fecha_alquila, fecha_devolucion;
	private double precio_total;
	
	public Reserva(String codigo, String cedula_cliente, String placa_vehiculo, Date fecha_alquila,
			Date fecha_devolucion, double precio_total) {
		super();
		setCodigo(codigo);
		setCedula_cliente(cedula_cliente);
		setPlaca_vehiculo(placa_vehiculo);
		setFecha_alquila(fecha_alquila);
		setFecha_devolucion(fecha_devolucion);
		setPrecio_total(precio_total);
	}
	public String getCodigo() {return codigo;}
	public void setCodigo(String codigo) {this.codigo = codigo;}
	public String getCedula_cliente() {return cedula_cliente;}
	public void setCedula_cliente(String cedula_cliente) {this.cedula_cliente = cedula_cliente;}
	public String getPlaca_vehiculo() {return placa_vehiculo;}
	public void setPlaca_vehiculo(String placa_vehiculo) {this.placa_vehiculo = placa_vehiculo;}
	public Date getFecha_alquila() {return fecha_alquila;}
	public void setFecha_alquila(Date fecha_alquila) {this.fecha_alquila = fecha_alquila;}
	public Date getFecha_devolucion() {return fecha_devolucion;}
	public void setFecha_devolucion(Date fecha_devolucion) {this.fecha_devolucion = fecha_devolucion;}
	public double getPrecio_total() {return precio_total;}
	public void setPrecio_total(double precio_total) {this.precio_total = precio_total;}

}
