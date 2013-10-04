package clinica.test.acciones;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import clinica.acciones.AccionesPaciente;
import clinica.beans.Consulta;
import clinica.beans.Paciente;

public class PacienteObtenerUltimaConsulta {

	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testObtenerUltimaConsulta1() throws Exception {
		AccionesPaciente ap = new AccionesPaciente();

		Paciente p = new Paciente();
		p.setRut("1-7");
		
		Consulta consulta = ap.obtenerUltimaConsulta(p);

		assertTrue(consulta.getTratamiento().equals("reposo extricto"));
	}
}
