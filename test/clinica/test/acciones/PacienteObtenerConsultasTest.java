package clinica.test.acciones;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import clinica.acciones.AccionesPaciente;
import clinica.beans.Consulta;
import clinica.beans.Paciente;

public class PacienteObtenerConsultasTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testObtenerConsultas1() throws Exception {
		AccionesPaciente ap = new AccionesPaciente();

		Paciente p = new Paciente();
		p.setRut("XXX");
		
		List<Consulta> consultas = ap.obtenerConsultas(1, p);

		assertTrue(consultas != null);

	}

	@Test
	public void testObtenerConsultas2() throws Exception {
		AccionesPaciente ap = new AccionesPaciente();

		Paciente p = new Paciente();
		p.setRut("1-7");
		
		List<Consulta> consultas = ap.obtenerConsultas(1, p);

		assertTrue(consultas != null);

	}
}
