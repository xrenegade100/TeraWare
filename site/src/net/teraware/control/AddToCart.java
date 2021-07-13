package net.teraware.control;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.model.Cart;
import net.teraware.model.ProdottoModel;
import net.teraware.model.bean.ProdottoBean;
import net.teraware.model.helper.CartEntry;

@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		if (request.getParameter("pId") == null || request.getParameter("q") == null) {
			sendErrorResponse(response);
			return;
		}
		try {
			int productId = Integer.parseInt(request.getParameter("pId"));
			int productQuantity = Integer.parseInt(request.getParameter("q"));
			ProdottoBean prod = new ProdottoModel().doRetrieveByKey(productId);
			Cart cart = (Cart) request.getSession(true).getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
			}
			CartEntry c = new CartEntry(
				prod,
				productQuantity <= prod.getQuantita() ? productQuantity : prod.getQuantita()
			);
			cart.set(c);
			request.getSession().setAttribute("cart", cart);
			response.getWriter().print(new Gson().toJson(cart, Cart.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void sendErrorResponse(HttpServletResponse response) throws IOException {
		response.sendError(400);
	}
}
