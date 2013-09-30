
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 9057435021820715049L;

	public Redirect() {
        super();
    }

    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        processRequest(rq, rs);
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        processRequest(rq, rs);
    }

    private void processRequest(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        rs.sendRedirect("/app");
    }
}