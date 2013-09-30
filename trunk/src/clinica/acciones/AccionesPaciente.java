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

		PreparedStatement statement = conn
				.prepareStatement("select nombre,apellido,fecha_nacimiento from paciente where rut=?");
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

	public List<Consulta> obtenerConsultas(int cantidad, Paciente p)
			throws Exception {
		Connection conn = Conexion.obtenerConexion();

		PreparedStatement statement = conn
				.prepareStatement("select fecha,paciente,medico,diagnostico,tratamiento,observaciones,tipo,examenes from consulta where rut=? ");
		statement.setString(1, p.getRut());
		ResultSet rs = statement.executeQuery();
		List<Consulta> lista = new ArrayList<Consulta>();

		while (rs.next()) {
			Consulta consu = new Consulta();
			consu.setFecha(rs.getDate("fecha"));
			consu.setDiagnostico(rs.getString("diagnostico"));
			Medico m = buscarMedico(rs.getString("medico"), conn);
			consu.setMedico(m);
			consu.setTratamiento(rs.getString("tratamiento"));
			consu.setObservaciones(rs.getString("Observaciones"));
			TipoConsulta tc = obtenerTipoConsulta(rs.getString("tipoConsulta"));
			consu.setTipoConsulta(tc);
			// consu.setExamenes(rs.getList("Examenes"));

			lista.add(consu);
		}
		Conexion.cerrarTodo(rs, statement, conn);
		return lista;
	}

	private Medico buscarMedico(String rut, Connection conn) throws Exception {
		Medico m = null;

		PreparedStatement statement = conn
				.prepareStatement("select nombre,apellido,fechaNacimiento from paciente where rut=? ");
		statement.setString(1, rut);
		ResultSet rs = statement.executeQuery();

		boolean encontro = rs.next();

		if (encontro) {
			m = new Medico();
			m.setNombre(rs.getString("nombre"));
			m.setApellido(rs.getString("apellido"));
			m.setRut(rs.getString("rut"));
			m.setEspecialidad(rs.getString("Especialidad"));
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
