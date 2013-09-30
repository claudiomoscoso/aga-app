package clinica.acciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import clinica.beans.Medico;
import clinica.beans.Paciente;
import clinica.beans.Usuario;
import clinica.util.Conexion;

public class AccionesUsuario {

    /**
     * Accion que permite hacer login
     */
    public Usuario login(String rut, String password) throws Exception {
        Connection conn = Conexion.obtenerConexion();
        PreparedStatement statement = conn.prepareStatement("select tipo from usuario where rut=? and password=?");
        statement.setString(1, rut);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();

        boolean encontro = rs.next();
        Usuario usuarioFinal = null;
        if (encontro) {
            String tipo = rs.getString("tipo");

            if (tipo.equalsIgnoreCase("m")) {
                usuarioFinal = new Medico();
            } else {
                usuarioFinal = new Paciente();
            }
            usuarioFinal.setPass(password);
            usuarioFinal.setRut(rut);

            llenarObjeto(conn, usuarioFinal);

        }

        Conexion.cerrarTodo(rs, statement, conn);

        return usuarioFinal;
    }

    private void llenarObjeto(Connection conn, Usuario usuarioFinal) throws Exception {
        if (usuarioFinal instanceof Medico) {
            Medico m = (Medico) usuarioFinal;

            PreparedStatement statement = conn.prepareStatement("select nombre,apellido,especialidad from medico where rut=? ");
            statement.setString(1, m.getRut());
            ResultSet rs = statement.executeQuery();

            boolean encontro = rs.next();

            if (encontro) {
                m.setNombre(rs.getString("nombre"));
                m.setApellido(rs.getString("apellido"));
                m.setEspecialidad(rs.getString("especialidad"));
            }

        } else {
            Paciente p = (Paciente) usuarioFinal;
            PreparedStatement statement = conn.prepareStatement("select rut,nombre,apellido,fecha_nacimiento from paciente where rut=? ");
            statement.setString(1, p.getRut());
            ResultSet rs = statement.executeQuery();

            boolean encontro = rs.next();

            if (encontro) {
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setRut(rs.getString("rut"));
                p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            }
        }
    }
}
