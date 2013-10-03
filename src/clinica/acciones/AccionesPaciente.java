package clinica.acciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import clinica.beans.Consulta;
import clinica.beans.Medico;
import clinica.beans.Paciente;
import clinica.beans.TipoConsulta;
import clinica.util.Conexion;

public class AccionesPaciente {

	public Paciente buscar(String rut) throws Exception {
		Paciente paciente = null;

		Connection conn = Conexion.obtenerConexion();

		PreparedStatement statement = conn.prepareStatement("select nombre,apellido,fecha_nacimiento from paciente where rut=?");
		statement.setString(1, rut);
		ResultSet rs = statement.executeQuery();

		boolean encontro = rs.next();

		if (encontro) {
			paciente = new Paciente();
			paciente.setRut(rut);
			paciente.setNombre(rs.getString("nombre"));
			paciente.setApellido(rs.getString("apellido"));
			paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
		}
		Conexion.cerrarTodo(rs, statement, conn);
		return paciente;
	}

	public Consulta obtenerUltimaConsulta(Paciente p) throws Exception {

		List<Consulta> consultas = obtenerConsultas(1, p);
		Consulta consulta = null;
		if (!consultas.isEmpty()) {
			consulta = consultas.get(0);
		}
		return consulta;
	}

	public List<Consulta> obtenerConsultas(int cantidad, Paciente p) throws Exception {
		Connection conn = Conexion.obtenerConexion();

		PreparedStatement statement = conn.prepareStatement("select codigo, rut_medico, fecha_consulta, diagnostico, "
				+ "tratamiento, tipo_consulta from consultas_medicas where rut_paciente=? ");
		statement.setString(1, p.getRut());
		ResultSet rs = statement.executeQuery();
		List<Consulta> lista = new ArrayList<Consulta>();

		while (rs.next()) {
			Consulta consulta = new Consulta();
			consulta.setCodigo(rs.getInt("codigo"));
			consulta.setFecha(rs.getDate("fecha_consulta"));
			consulta.setDiagnostico(rs.getString("diagnostico"));
			Medico m = buscarMedico(rs.getString("rut_medico"), conn);
			consulta.setMedico(m);
			consulta.setTratamiento(rs.getString("tratamiento"));
//			consulta.setObservaciones(rs.getString("Observaciones"));
			TipoConsulta tc = obtenerTipoConsulta(rs.getString("tipo_consulta"));
			consulta.setTipoConsulta(tc);
			// consu.setExamenes(rs.getList("Examenes"));

			lista.add(consulta);
		}
		Conexion.cerrarTodo(rs, statement, conn);
		return lista;
	}

	private Medico buscarMedico(String rut, Connection conn) throws Exception {
		Medico m = null;

		PreparedStatement statement = conn.prepareStatement("select nombre,apellido,especialidad from medico where rut=? ");
		statement.setString(1, rut);
		ResultSet rs = statement.executeQuery();

		boolean encontro = rs.next();

		if (encontro) {
			m = new Medico();
			m.setNombre(rs.getString("nombre"));
			m.setApellido(rs.getString("apellido"));
			m.setRut(rut);
			m.setEspecialidad(rs.getString("especialidad"));
		} else {
			System.out.println("No se encontraron datos");
		}
		return m;
	}

	private TipoConsulta obtenerTipoConsulta(String tipoc) {
		if (tipoc.equalsIgnoreCase("c")) {
			return TipoConsulta.ConsultaMedica;
		} else {
			return TipoConsulta.RevisionExamen;
		}
	}
}
