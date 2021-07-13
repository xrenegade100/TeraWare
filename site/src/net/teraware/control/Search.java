package net.teraware.control;

import com.google.gson.Gson;
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

@WebServlet("/search")
public class Search extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String query = request.getParameter("query");
		try {
			ArrayList<ProdottoBean> result = (ArrayList<ProdottoBean>) new ProdottoModel()
				.searchByName(query);
			response.getWriter().print(new Gson().toJson(result, ArrayList.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
