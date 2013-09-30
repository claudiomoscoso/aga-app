package clinica;

import java.util.logging.Level;
import java.util.logging.Logger;

import clinica.acciones.AccionesUsuario;
import clinica.beans.Medico;
import clinica.beans.Paciente;
import clinica.beans.Usuario;

public class Principal {

	public static void main(String[] args) {

		// Usuario paciente = new Paciente ();
		// Usuario paciente = new Paciente ();

		/**
		 * Usuario medico = new
		 * Medico("med","123","17589298-8","Cristobal","Manzano"
		 * ,"Traumatologo");
		 * 
		 * System.out.println("El login es: " + medico.getLogin());
		 * System.out.println("La pass es: "+ medico.getPass());
		 * System.out.println("La rut es: " + medico.getRut());
		 * System.out.println("El nombre es: "+ medico.getNombre());
		 * System.out.println("El apellido es: "+ medico.getApellido()); o =
		 * (Medico)medico; System.out.println("La especialidad es: "+
		 * o.getEspecialidad());
		 */
		AccionesUsuario acciones = new AccionesUsuario();
		try {
			Usuario elQueSeConecta = acciones.login("1-9", "123");
			if (elQueSeConecta != null) {
				if (elQueSeConecta instanceof Medico) {
					Medico m = (Medico) elQueSeConecta;
					System.out.println(m.toString());
				} else {
					Paciente p = (Paciente) elQueSeConecta;
					System.out.println(p.toString());

				}
			} else {
				System.out.println("Usuario no existe!");
			}

		} catch (Exception ex) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}
}
