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

@WebServlet("/admin/products")
public class Products extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// Ottieni tutti i prodotti e forwarda alla servlet
		ProdottoModel prodottoModel = new ProdottoModel();
		ArrayList<ProdottoBean> prodotti = null;
		try {
			prodotti = (ArrayList<ProdottoBean>) prodottoModel.doRetrieveAll(null);
		} catch (SQLException e) {
			response.getWriter().println("Errore"); //FIXME Redirect pagina di errore
			e.printStackTrace();
			return;
		}
		request.setAttribute("prodotti", prodotti);
		getServletContext().getRequestDispatcher("/pages/prodotti.jsp").forward(request, response);
	}
}
