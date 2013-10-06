package clinica.test.acciones;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import clinica.acciones.AccionesConsulta;
import clinica.acciones.AccionesPaciente;
import clinica.beans.Consulta;
import clinica.beans.Examen;
import clinica.beans.Medico;
import clinica.beans.Paciente;
import clinica.beans.TipoConsulta;
import clinica.util.Conexion;

public class ConsultaConfirmarTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void ConfirmarConsulta1() throws Exception {
		AccionesConsulta ac = new AccionesConsulta();
		Consulta c = new Consulta();
		Paciente p = new Paciente();
		Medico m = new Medico();

		p.setRut("1-7");
		m.setRut("1-9");

		c.setDiagnostico("resfrio");
		c.setExamenes(getExamenes());
		c.setFecha(new java.sql.Date(System.currentTimeMillis()));
		c.setMedico(m);
		c.setPaciente(p);
		c.setTipoConsulta(TipoConsulta.ConsultaMedica);
		c.setTratamiento("antigripal");

		ac.confirmar(c);

		Integer codigoUltimoExamen = (new AccionesPaciente()).obtenerUltimaConsulta(p).getCodigo();

		Boolean success = c.getCodigo() == codigoUltimoExamen;

		deleteConsulta(c.getCodigo());

		assertTrue(success);

	}

	private void deleteConsulta(Integer codigo) throws Exception {
		if (codigo != null) {
			Connection conn = Conexion.obtenerConexion();

			PreparedStatement stmt = conn.prepareStatement("delete from consultas_medicas where codigo=?");

			stmt.setInt(1, codigo);

			stmt.execute();

			Conexion.cerrarTodo(null, stmt, conn);
		}
	}

	private List<Examen> getExamenes() {
		List<Examen> out = new ArrayList<Examen>();
		Examen e = new Examen();
		e.setCodigo("abc");
		e.setNombre("Biopsia");
		e.setTipo("");

		out.add(e);

		return out;
	}
}
