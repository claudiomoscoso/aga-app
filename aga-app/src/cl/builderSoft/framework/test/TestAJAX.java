package cl.builderSoft.framework.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.util.BSStaticManager;

public class TestAJAX extends HttpServlet implements Servlet {

    public TestAJAX() {
        super();
    }

    protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
        processRequest(arg0, arg1);
    }

    protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
        processRequest(arg0, arg1);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String service = request.getParameter("SVC");
        try {
            if (service == null) {
                PrintWriter writer = response.getWriter();

                writer.print(getDefaultHTML());
            } else {

            }
        } catch (BSException e) {
            e.printStackTrace();
        }

    }

    private String getDefaultHTML() throws BSException {
        return (String)BSStaticManager.getFileContent("D:\\BS\\delete.me\\default.htm");
    }
}