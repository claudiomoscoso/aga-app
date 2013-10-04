package clinica.acciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import clinica.beans.Consulta;
import clinica.util.Conexion;

public class AccionesConsulta {
	public void confirmar(Consulta c) throws Exception {
		Connection conn = Conexion.obtenerConexion();

		String sql = "insert into consultas_medicas ( rut_paciente, rut_medico, ";
		sql += "fecha_consulta, diagnostico, tratamiento, tipo_consulta) ";
		sql += "values (?, ?, ?, ?, ?, ?)";

		PreparedStatement s = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		s.setString(1, c.getPaciente().getRut());
		s.setString(2, c.getMedico().getRut());
		s.setDate(3, (java.sql.Date) c.getFecha());
		s.setString(4, c.getDiagnostico());
		s.setString(5, c.getTratamiento());
		s.setString(6, "" + c.getTipoConsulta().toString().charAt(0));

		s.executeUpdate();

		ResultSet generatedKeys = s.getGeneratedKeys();
		if (generatedKeys.next()) {
			c.setCodigo(((Long) generatedKeys.getLong(1)).intValue());
		}

		Conexion.cerrarTodo(null, s, conn);
	}
}
