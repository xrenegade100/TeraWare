package net.teraware.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.model.Cart;
import net.teraware.model.OrdineModel;
import net.teraware.model.ProdottoModel;
import net.teraware.model.ProdottoOrdineModel;
import net.teraware.model.bean.OrdineBean;
import net.teraware.model.bean.ProdottoOrdineBean;
import net.teraware.model.bean.UtenteBean;
import net.teraware.model.helper.CartEntry;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		if (request.getSession().getAttribute("user") != null) {
			if (cart != null) {
				OrdineBean ordine = createOrder(request);
				ArrayList<ProdottoOrdineBean> prodottiOrdine = createProducts(
					cart,
					ordine.getIdOrdine()
				);
				try {
					new OrdineModel().doSave(ordine);
					ProdottoOrdineModel prodModel = new ProdottoOrdineModel();
					for (ProdottoOrdineBean p : prodottiOrdine) {
						prodModel.doSave(p);
						new ProdottoModel().decreaseQuantity(p.getProdotto(), p.getQuantita());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				cart.clear();
			}
			response.sendRedirect(
				getServletContext().getContextPath() + "/pages/post-checkout.jsp"
			);
		} else {
			response.sendRedirect(getServletContext().getContextPath() + "/pages/login.jsp");
		}
	}

	private OrdineBean createOrder(HttpServletRequest request) {
		OrdineBean ordine = new OrdineBean();
		ordine.setData(new Date());
		ordine.setStato("preso in carico");
		ordine.setIdOrdine(UUID.randomUUID());
		ordine.setIdUtente(((UtenteBean) request.getSession().getAttribute("user")).getIdUtente());
		return ordine;
	}

	private ArrayList<ProdottoOrdineBean> createProducts(Cart carrello, UUID idOrdine) {
		ArrayList<ProdottoOrdineBean> result = new ArrayList<>();
		Iterator<CartEntry> iterator = carrello.getProdotti();
		while (iterator.hasNext()) {
			CartEntry entry = iterator.next();
			ProdottoOrdineBean bean = new ProdottoOrdineBean();
			bean.setProdotto(entry.getProdotto());
			bean.setPrezzoEffettivo(entry.getProdotto().getPrezzo());
			bean.setQuantita(entry.getQuantita());
			bean.setIdOrdine(idOrdine);
			result.add(bean);
		}
		return result;
	}
}
