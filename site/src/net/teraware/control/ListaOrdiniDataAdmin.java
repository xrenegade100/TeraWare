package net.teraware.control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.model.OrdineModel;
import net.teraware.model.bean.OrdineBean;

@WebServlet("/admin/listaordinidata")
public class ListaOrdiniDataAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			int skip = Integer.parseInt(request.getParameter("skip"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			if (limit == 0) limit = 10;
			Date dateStart = new SimpleDateFormat("yyyy-MM-dd")
				.parse(request.getParameter("dateStart"));
			Date dateEnd = new SimpleDateFormat("yyyy-MM-dd")
				.parse(request.getParameter("dateEnd"));
			ArrayList<OrdineBean> ordini = new OrdineModel()
				.doRetriveByDate(dateStart, dateEnd, skip, limit);
			request.setAttribute("ordini", ordini);
			request.setAttribute("skip", skip);
			request.setAttribute("limit", limit);
			getServletContext()
				.getRequestDispatcher("/pages/admin/listaperdata.jsp")
				.forward(request, response);
			return;
		} catch (ParseException e) {
			response.getWriter().println("Errore nel convertire la data");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
