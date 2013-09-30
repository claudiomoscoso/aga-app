package clinica.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {

    public static Connection obtenerConexion() throws Exception {
        String bd = "jdbc:odbc:seba";

        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con = null;

        con = DriverManager.getConnection(bd);

        return con;
    }

    public static void cerrarTodo(ResultSet rs, Statement stm, Connection conn) throws Exception {
        rs.close();
        stm.close();
        conn.close();
    }
}