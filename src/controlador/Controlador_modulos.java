package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Modulos;
import vista.Principal;

public class Controlador_modulos implements ActionListener {
	
	private Modulos m;

	public Controlador_modulos(Modulos mo) {
		// TODO Auto-generated constructor stub
		m = mo;
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		// TODO Auto-generated method stub
		if (eve.getActionCommand().equalsIgnoreCase("volver")) {
			Principal p = new Principal("Demo");
			p.setVisible(true);
			m.dispose();
		} else if (eve.getActionCommand().equalsIgnoreCase("usuarios")) {
			m.usuarios();
		} else if (eve.getActionCommand().equalsIgnoreCase("vehículos")) {
			m.vehiculos();
		} else if (eve.getActionCommand().equalsIgnoreCase("clientes")) {
			m.usuarios();
		} else if (eve.getActionCommand().equalsIgnoreCase("reservas")) {
			if (m.getUsuario().getTipo().equals("0001")) m.crear_reservas();
			else m.reservas();
		} else if (eve.getActionCommand().equalsIgnoreCase("Vehículos disponibles")) {
			m.reportes_a();
		} else if (eve.getActionCommand().equalsIgnoreCase("Vehículos no retornados")) {
			m.reportes_b();
		} /*
			 * else if (eve.getActionCommand().equalsIgnoreCase("Alquileres")) {
			 * m.alquilar(); }
			 */
	}
	
	public void preparar() {
		if (m.getUsuario().getTipo().equals("0001")) m.preparar_cliente();
		else if (m.getUsuario().getTipo().equals("0002")) m.preparar_empleado();
		else if (m.getUsuario().getTipo().equals("0003")) m.preparar_administrador();
	}

}
