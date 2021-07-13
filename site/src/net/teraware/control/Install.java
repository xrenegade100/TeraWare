package net.teraware.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.manager.DatabaseManager;
import net.teraware.model.UtenteModel;
import net.teraware.model.bean.UtenteBean;
import net.teraware.util.Config;
import net.teraware.util.ConfigHelper;
import net.teraware.validator.ParametersValidator;
import net.teraware.validator.StringType;

@WebServlet("/install")
public class Install extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ConfigHelper cfgHelper;
	private Config config;
	private ParametersValidator databaseParams;
	private ParametersValidator socParams;

	@Override
	public void init() throws ServletException {
		super.init();

		this.databaseParams = new ParametersValidator();
		this.socParams = new ParametersValidator();
		this.databaseParams.addStringParameter("username", StringType.TEXT, true);
		this.databaseParams.addStringParameter("password", StringType.TEXT, true);
		this.databaseParams.addStringParameter("host", StringType.TEXT, true);
		this.databaseParams.addNumberParameter("port", Integer.class, false);
		this.socParams.addStringParameter("piva", StringType.TEXT, true);
		this.socParams.addStringParameter("nomesocieta", StringType.TEXT, true);
		this.socParams.addStringParameter("emailsocieta", StringType.TEXT, true);
		this.socParams.addStringParameter("provincia", StringType.TEXT, true);
		this.socParams.addNumberParameter("cap", Integer.class, true);
		this.socParams.addStringParameter("via", StringType.TEXT, true);
		this.socParams.addNumberParameter("civico", Integer.class, true);
		this.socParams.addStringParameter("note", StringType.TEXT, false);

		this.cfgHelper = new ConfigHelper(getServletContext());
		try {
			this.config = cfgHelper.readConfig();
			DatabaseManager.initInstance(
				this.config.getHost(),
				this.config.getUsername(),
				this.config.getPassword(),
				this.config.getPort(),
				false
			);
			DatabaseManager.getInstance().getConnection().createStatement().execute("USE teraware");
		} catch (SQLException | FileNotFoundException e) {
			System.out.println("Config file not found");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.sendRedirect(getServletContext().getContextPath() + "/install/install.jsp");
	}

	//TODO Eventualmente separare i dati in 2 pagine per controllare prima la connessione al database
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		if (request.getParameterMap().containsKey("host")) {
			if (!this.databaseParams.validate(request)) {
				response.sendRedirect(
					getServletContext().getContextPath() +
					"/install/install.jsp#error=missing_parameter"
				);
				return;
			}

			Config dbConfig = genConfig(request);
			if (
				!DatabaseManager.checkConnection(
					dbConfig.getHost(),
					dbConfig.getUsername(),
					dbConfig.getPassword(),
					dbConfig.getPort(),
					""
				)
			) {
				response.sendRedirect(
					getServletContext().getContextPath() + "/install#error=database"
				);
				return;
			}

			cfgHelper.writeConfig(dbConfig);
			DatabaseManager.initInstance(
				dbConfig.getHost(),
				dbConfig.getUsername(),
				dbConfig.getPassword(),
				dbConfig.getPort(),
				true
			);
			try {
				createDatabase();
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			response.sendRedirect(getServletContext().getContextPath() + "/install/install2.jsp");
			return;
		} else if (request.getParameterMap().containsKey("nomesocieta")) {
			if (!this.socParams.validate(request)) {
				getServletContext()
					.getRequestDispatcher("/install/install2.jsp")
					.forward(request, response);
				return;
			}

			try {
				//FIXME move into societadao?
				Connection conn = DatabaseManager.getInstance().getConnection();
				conn.createStatement().execute("USE teraware");
				PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO societa VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
				);

				stmt.setString(1, request.getParameter("piva"));
				stmt.setString(2, request.getParameter("nomesocieta"));
				stmt.setString(3, request.getParameter("emailsocieta"));
				stmt.setString(4, request.getParameter("provincia"));
				stmt.setString(5, request.getParameter("cap"));
				stmt.setString(6, request.getParameter("via"));
				stmt.setInt(7, Integer.parseInt(request.getParameter("civico")));
				stmt.setString(
					8,
					request.getParameter("note") == null ||
						request.getParameter("note").trim() == ""
						? ""
						: request.getParameter("note")
				);
				stmt.execute();
				UtenteBean admin = new UtenteBean();
				admin.setNome(request.getParameter("adminNome"));
				admin.setCognome(request.getParameter("adminCognome"));
				admin.setEmail(request.getParameter("adminEmail"));
				admin.setPassword(request.getParameter("adminPassword"));
				admin.setCivico(request.getParameter("adminCivico"));
				admin.setCap(request.getParameter("adminCap"));
				admin.setProvincia(request.getParameter("adminProvincia"));
				admin.setVia(request.getParameter("adminVia"));
				admin.setRole(1);
				new UtenteModel().doSave(admin);
				DatabaseManager.getInstance().releaseConnection(conn);
				response.getWriter().append("Installazione completata! Riavviare il server");
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}

	private void createDatabase() throws SQLException, FileNotFoundException {
		DatabaseManager
			.getInstance()
			.getConnection()
			.createStatement()
			.execute(readFile(cfgHelper.getServletCtx()));
	}

	private String readFile(ServletContext ctx) throws FileNotFoundException {
		InputStream is = ctx.getResourceAsStream("/WEB-INF/create.sql");
		InputStreamReader isReader = new InputStreamReader(is);
		BufferedReader bf = new BufferedReader(isReader);
		String line;
		StringBuilder sb = new StringBuilder();
		try {
			while ((line = bf.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private Config genConfig(HttpServletRequest request) {
		if (request.getParameter("port").trim() == "") {
			return new Config(
				request.getParameter("username"),
				request.getParameter("password"),
				request.getParameter("host")
			);
		}
		return new Config(
			request.getParameter("username"),
			request.getParameter("password"),
			request.getParameter("host"),
			Integer.parseInt(request.getParameter("port"))
		);
	}
}
