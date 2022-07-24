package clases;

public class Vehiculo {
	
	private String placa, marca, modelo, color;
	private double precio_dia, kilometraje;
	private boolean disponible;
	
	public Vehiculo(String placa, String marca, String modelo, String color, double precio_dia, double kilometraje,
			boolean disponible) {
		super();
		setPlaca(placa);
		setMarca(marca);
		setModelo(modelo);
		setColor(color);
		setPrecio_dia(precio_dia);
		setKilometraje(kilometraje);
		setDisponible(disponible);
	}
	public String getPlaca() {return placa;}
	public void setPlaca(String placa) {this.placa = placa;}
	public String getMarca() {return marca;}
	public void setMarca(String marca) {this.marca = marca;}
	public String getModelo() {return modelo;}
	public void setModelo(String modelo) {this.modelo = modelo;}
	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}
	public double getPrecio_dia() {return precio_dia;}
	public void setPrecio_dia(double precio_dia) {this.precio_dia = precio_dia;}
	public double getKilometraje() {return kilometraje;}
	public void setKilometraje(double kilometraje) {this.kilometraje = kilometraje;}
	public boolean isDisponible() {return disponible;}
	public void setDisponible(boolean disponible) {this.disponible = disponible;}

}
