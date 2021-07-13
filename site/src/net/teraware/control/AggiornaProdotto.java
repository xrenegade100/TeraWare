package net.teraware.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.teraware.model.ProdottoModel;
import net.teraware.model.bean.ProdottoBean;

@WebServlet("/admin/aggiornaprodotto")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 50)
public class AggiornaProdotto extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String savePath;
	private final String IMG_DIR = "IMG_DIR";

	@Override
	public void init() {
		savePath =
			getServletContext().getRealPath("") +
			File.separator +
			"uploads" +
			File.separator +
			IMG_DIR;

		File imgSaveDir = new File(savePath);
		if (!imgSaveDir.exists()) imgSaveDir.mkdirs();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String nomeProdotto = request.getParameter("nomeProdotto");
		String brand = request.getParameter("brand");
		String informazioni = request.getParameter("informazioni");
		int quantita = Integer.parseInt(request.getParameter("quantita"));
		float prezzo = Float.parseFloat(request.getParameter("prezzo"));
		String categoria = request.getParameter("categoria");
		System.out.println(categoria);
		ArrayList<String> fileNames = uploadFiles(request);
		ProdottoBean newProdotto = new ProdottoBean();
		newProdotto.setNome(nomeProdotto);
		newProdotto.setPrezzo(prezzo);
		newProdotto.setBrand(brand);
		newProdotto.setInformazioni(informazioni);
		newProdotto.setImageUrl(fileNames.size() == 0 ? null : fileNames.get(0));
		newProdotto.setQuantita(quantita);
		newProdotto.setCategoria(categoria);
		if (request.getParameter("id") != null) {
			newProdotto.setIdProdotto(Integer.parseInt(request.getParameter("id")));
		}

		if (request.getParameter("visibile") != null) {
			newProdotto.setVisibile(true);
		} else {
			newProdotto.setVisibile(false);
		}

		try {
			new ProdottoModel().doSave(newProdotto);
			response.sendRedirect(
				getServletContext().getContextPath() + "/pages/admin/catalogo.jsp"
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> uploadFiles(HttpServletRequest request) {
		ArrayList<String> uploadedFileNames = new ArrayList<>();
		try {
			if (
				request.getParts().size() < 0 || request.getParts() == null
			) return uploadedFileNames;
			for (Part p : request.getParts()) {
				String fileName = extractFileName(p);
				if (fileName.trim() != "" && fileName != null) {
					p.write(savePath + File.separator + fileName);
					uploadedFileNames.add("/uploads/" + IMG_DIR + "/" + fileName);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return uploadedFileNames;
	}

	private String extractFileName(Part part) {
		// content-disposition: form-data; name="file"; filename="file.txt"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename") && !s.trim().equals("filename=\"\"")) {
				return new Date().getTime() + "_" + s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
