package net.teraware.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.model.ProdottoModel;
import net.teraware.model.bean.ProdottoBean;

@WebServlet("/product")
public class Product extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String productId = request.getParameter("id");
		if (productId != null) {
			try {
				ProdottoBean prodotto = new ProdottoModel()
					.doRetrieveByKey(Integer.parseInt(productId));
				request.setAttribute("prod", prodotto);
				getServletContext()
					.getRequestDispatcher("/pages/prodotto.jsp")
					.forward(request, response);
			} catch (NumberFormatException e) {
				//Il parametro id non è un numero che facciamo?
				response.sendError(400);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		} else {
			response.sendRedirect(getServletContext().getContextPath() + "/products");
		}
	}
}
