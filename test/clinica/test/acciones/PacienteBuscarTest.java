package clinica.test.acciones;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import clinica.acciones.AccionesPaciente;
import clinica.beans.Paciente;

public class PacienteBuscarTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuscarOK1() throws Exception {
		AccionesPaciente ap = new AccionesPaciente();

		Paciente p = ap.buscar("1-7");

		assertTrue(p != null);
	}
	
	@Test
	public void testBuscarOK2() throws Exception {
		AccionesPaciente ap = new AccionesPaciente();

		Paciente p = ap.buscar("1-7");

		assertTrue(p.getNombre().equalsIgnoreCase("Cristian"));
	}

	@Test
	public void testBuscarIsNull() throws Exception {
		AccionesPaciente ap = new AccionesPaciente();

		Paciente p = ap.buscar("xxx");

		assertTrue(p == null);
	}

}
