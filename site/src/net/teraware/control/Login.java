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

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (email == null || password == null) {
			response.sendError(400);
			return;
		}
		try {
			UtenteBean utente = new UtenteModel().doRetrieveByEmailAndPassword(email, password);
			if (utente == null) {
				response.sendError(403);
				return;
			}
			request.getSession(true).setAttribute("user", utente);
			request.getSession().setAttribute("redirect", null);
			response.sendRedirect(getServletContext().getContextPath() + "");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
