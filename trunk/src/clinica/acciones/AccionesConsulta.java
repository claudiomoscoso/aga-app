package clinica.acciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import clinica.beans.Consulta;
import clinica.util.Conexion;

public class AccionesConsulta {
	public void confirmar(Consulta c) throws Exception {
		Connection conn = Conexion.obtenerConexion();

		Integer lastKey = getLastCode(conn, "consultas_medicas");
		lastKey++;
		c.setCodigo(lastKey);

		String sql = "insert into consultas_medicas (codigo, rut_paciente, rut_medico, ";
		sql += "fecha_consulta, diagnostico, tratamiento, tipo_consulta) ";
		sql += "values (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement s = conn.prepareStatement(sql);
		// Statement s = conn.createStatement( );

		// preparedStatement = conn.prepareStatement(sql,
		// Statement.RETURN_GENERATED_KEYS);
		s.setInt(1, c.getCodigo());
		s.setString(2, c.getPaciente().getRut());
		s.setString(3, c.getMedico().getRut());
		s.setDate(4, (java.sql.Date) c.getFecha());
		s.setString(5, c.getDiagnostico());
		s.setString(6, c.getTratamiento());
		s.setString(7, "" + c.getTipoConsulta().toString().charAt(0));

		s.executeUpdate();

		Conexion.cerrarTodo(null, s, conn);
	}

	private Integer getLastCode(Connection conn, String tabla) throws Exception {
		Integer out = -1;
		String sql = "select max(codigo) from " + tabla;
		PreparedStatement s = conn.prepareStatement(sql);

		ResultSet rs = s.executeQuery();
		if (rs.next()) {
			out = rs.getInt(1);
		}

		return out;
	}
}
