package net.teraware.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.teraware.model.UtenteModel;
import net.teraware.model.bean.UtenteBean;

@WebServlet("/signup")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 5, // Max file size 5MB
	maxRequestSize = 1024 * 1024 * 50 // Max request size 50 MB
)
public class Signup extends HttpServlet {

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
		UtenteBean utente = new UtenteBean();
		ArrayList<String> uploadedFiles = uploadFiles(request);
		utente.setEmail(request.getParameter("email"));
		utente.setPassword(request.getParameter("password"));
		utente.setNome(request.getParameter("nome"));
		utente.setCognome(request.getParameter("cognome"));
		utente.setPropicUrl(
			uploadedFiles.size() == 0
				? ""
				: getServletContext().getContextPath() + uploadedFiles.get(0)
		);
		utente.setCap(request.getParameter("cap"));
		utente.setCivico(request.getParameter("civico"));
		utente.setProvincia(request.getParameter("provincia"));
		utente.setVia(request.getParameter("via"));
		utente.setNote("");
		utente.setOauth(false);
		try {
			new UtenteModel().doSave(utente);
			response.sendRedirect(getServletContext().getContextPath() + "/pages/post-signup.jsp");
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				e.printStackTrace();
				response.getWriter().println("Email già utilizzata");
				return;
			}
			return;
		}
	}

	private ArrayList<String> uploadFiles(HttpServletRequest request) {
		ArrayList<String> uploadedFileNames = new ArrayList<>();
		try {
			if (
				request.getParts().size() < 0 || request.getParts() == null
			) return uploadedFileNames;
			for (Part p : request.getParts()) {
				if (p.getSize() != 0) {
					String fileName = extractFileName(p);
					if (fileName.trim() != "" && fileName != null) {
						p.write(savePath + File.separator + fileName);
						uploadedFileNames.add("/uploads/" + IMG_DIR + "/" + fileName);
					}
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
			if (s.trim().startsWith("filename")) {
				return new Date().getTime() + "_" + s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
