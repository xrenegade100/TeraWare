package net.teraware.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.auth.GoogleOAuthUser;
import net.teraware.util.GoogleOAuth;
import net.teraware.util.SecretsReader;

@WebServlet("/oauth")
public class OAuth extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SecretsReader.initInstance(getServletContext());
	}

	/**
	 * TODO Probabilmente qui dobbiamo prendere oltre code come parametro anche se si vuole fare il login o la registrazione dell'utente
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		if (request.getParameter("code") == null) {
			response.sendError(400);
			return;
		}
		GoogleOAuthUser googleUser = GoogleOAuth.getGoogleUser(request.getParameter("code"));
		response.getWriter().append("User: ").append(googleUser.toString());
	}
}
