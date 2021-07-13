package net.teraware.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.model.ProdottoModel;
import net.teraware.model.bean.ProdottoBean;

@WebServlet("/category")
public class Category extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String category = request.getParameter("category");
		int skip = Integer.parseInt(request.getParameter("skip"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		if (!category.equals("all")) {
			try {
				request.setAttribute(
					"prodotti",
					new ProdottoModel().doRetrieveByCategory(category, skip, limit)
				);
				request.setAttribute(
					"numProdotti",
					new ProdottoModel().getNumProdottiByCategory(category)
				);
				getServletContext()
					.getRequestDispatcher("/pages/categoria.jsp")
					.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) new ProdottoModel()
					.doRetrieveAll(null, skip, limit);
				request.setAttribute("prodotti", prodotti);
				request.setAttribute("numProdotti", new ProdottoModel().getNumProdotti());
				getServletContext()
					.getRequestDispatcher("/pages/categoria.jsp")
					.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
