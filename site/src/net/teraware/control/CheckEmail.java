package net.teraware.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.model.UtenteModel;
import net.teraware.model.bean.UtenteBean;

@WebServlet("/checkemail")
public class CheckEmail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String email = request.getParameter("email");
		try {
			UtenteBean utente = new UtenteModel().doRetrieveByEmail(email);
			if (utente == null) {
				response.getWriter().print("0");
				return;
			}
			response.getWriter().print("1");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
