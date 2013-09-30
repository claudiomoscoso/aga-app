package cl.builderSoft.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class TestConnection extends HttpServlet implements Servlet {
 
    public TestConnection() {
        super();
    }

    protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

    }

    String foo = "Not Connected";

    String bar = "";

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        try {

            Context ctx = new InitialContext();
            if (ctx == null)
                throw new Exception("Boom - No Context");

            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");

            if (ds != null) {
                Connection conn = ds.getConnection();

                if (conn != null) {
                    foo = "Got Connection " + conn.toString();
                    Statement stmt = conn.createStatement();
                    ResultSet rst = stmt.executeQuery("select * from tUser");
                    if (rst.next()) {
                        foo = rst.getString(2);
                        bar = rst.getString(3);
                        writer.println("Record: " + foo + " " + bar);
                    }
                    conn.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(writer);
            e.printStackTrace();
        }
    }

}