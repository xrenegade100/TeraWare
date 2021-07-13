package net.teraware.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.model.OrdineModel;
import net.teraware.model.UtenteModel;
import net.teraware.model.bean.OrdineBean;
import net.teraware.model.bean.UtenteBean;

@WebServlet("/admin/listaordiniemail")
public class ListaOrdiniEmailAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			int skip = Integer.parseInt(request.getParameter("skip"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			if (limit == 0) limit = 10;
			String emailCliente = request.getParameter("email");
			UtenteBean utente = new UtenteModel().doRetrieveByEmail(emailCliente);
			ArrayList<OrdineBean> ordini = new ArrayList<>();
			if (utente != null) {
				ordini = new OrdineModel().doRetriveByUser(utente, "_data DESC", skip, limit);
				request.setAttribute("cliente", utente);
			}
			request.setAttribute("ordini", ordini);
			request.setAttribute("skip", skip);
			request.setAttribute("limit", limit);
			getServletContext()
				.getRequestDispatcher("/pages/admin/listaperutente.jsp")
				.forward(request, response);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
