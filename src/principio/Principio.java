package principio;

/*import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;*/

import vista.Principal;

/*import java.util.ArrayList;

import modelo.Modelo_cliente;*/

public class Principio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Modelo_cliente m = new Modelo_cliente();
		ArrayList<String> cola = m.desc();
		for (String string : cola) {
			System.out.println(string);
		}*/
		
		
		Principal p = new Principal("Demo"); 
		p.setVisible(true);
		 
		
		/*
		 * Date i = Date.valueOf("2021-05-20"); Date j = Date.valueOf("2021-02-13");
		 * System.out.println(i.before(j)); long todo = i.getTime() - j.getTime();
		 * System.out.println(TimeUnit.DAYS.convert(todo, TimeUnit.MILLISECONDS));
		 * LocalDate p = LocalDate.parse(i.toString()); LocalDate s =
		 * LocalDate.parse(j.toString()); System.out.println(ChronoUnit.DAYS.between(s,
		 * p));
		 */
	}

}
