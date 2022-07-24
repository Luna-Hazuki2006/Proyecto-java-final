package clases;

public class Cliente {

	private String cedula, nombres, apellidos, direccion, telefono, tipo, clave;

	public Cliente(String cedula, String nombres, String apellidos, String direccion, String telefono, 
			String tipo, String clave) {
		super();
		setCedula(cedula);
		setNombres(nombres);
		setApellidos(apellidos);
		setDireccion(direccion);
		setTelefono(telefono);
		setTipo(tipo);
		setClave(clave);
	}

	public String getCedula() {return cedula;}

	public void setCedula(String cedula) {this.cedula = cedula;}

	public String getNombres() {return nombres;}

	public void setNombres(String nombres) {this.nombres = nombres;}

	public String getApellidos() {return apellidos;}

	public void setApellidos(String apellidos) {this.apellidos = apellidos;}

	public String getDireccion() {return direccion;}

	public void setDireccion(String direccion) {this.direccion = direccion;}

	public String getTelefono() {return telefono;}

	public void setTelefono(String telefono) {this.telefono = telefono;}

	public String getTipo() {return tipo;}

	public void setTipo(String tipo) {this.tipo = tipo;}

	public String getClave() {return clave;}

	public void setClave(String clave) {this.clave = clave;}
	
}
