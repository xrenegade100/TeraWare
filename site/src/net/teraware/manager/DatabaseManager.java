package net.teraware.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Manager per pool di istanze di <b>Connection</b> per eseguire query sul db.
 * Dal momento che il container serve su thread diversi le richieste, richiamando eventualmente pi� metodi delle servlet
 * simultaneamente, è necessario prevedere di poter fare pi� query simultaneamente utilizzando più istanze di Connection.<br>
 * <b>Questa classe è un Singleton</b>
 * */
public class DatabaseManager {

	private String user;
	private String password;
	private final String connectionURI;
	private ArrayList<Connection> freeConnections;
	private static DatabaseManager instance;
	private final int CONNECTION_POOL_SIZE = 100;

	/**
	 * Inizializza l'istanza di questa classe
	 * @param host L'host per la connessione al database
	 * @param user Il nome utente per la connessione al database
	 * @param password La password per l'utente per effettuare l'accesso al database
	 * @param port La porta per accedere al database
	 * */
	public static void initInstance(
		String host,
		String user,
		String password,
		int port,
		boolean first
	) {
		if (instance == null) instance =
			new DatabaseManager(host, user, password, port, first ? "" : "teraware");
	}

	/**
	 * Restituisce l'istanza di questa classe
	 * */
	public static DatabaseManager getInstance() {
		return instance;
	}

	private DatabaseManager(String host, String user, String password, int port, String dbName) {
		this.user = user;
		this.password = password;
		this.connectionURI =
			"jdbc:mysql://" +
			host +
			":" +
			port +
			"/" +
			dbName +
			"?useUnicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDateTimeCode=false&serverTimezone=Europe/Rome&allowMultiQueries=true";
		this.freeConnections = new ArrayList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Preleva un'istanza di connection dalla pool e la restituisce, se c'�,
	 * altrimenti ne crea una nuova e la restituisce.
	 * @return Un'istanza di {@link Connection} per interagire con il database
	 * */
	public synchronized Connection getConnection() throws SQLException {
		for (Connection c : this.freeConnections) {
			if (!c.isClosed()) {
				Connection conn = c;
				this.freeConnections.remove(c);
				return conn;
			}
		}
		return this.createConnection();
	}

	/**
	 * Rilascia un'istanza di {@link Connection} che non deve essere pi� utilizzata.
	 * Se c'� spazio nella pool (pool_size < CONNECTION_POOL_SIZE) lo inserisce nella pool,
	 * altrimenti viene eliminato dal garbage collector.
	 * (No memory leaks: rischio di avere una pool di oggetti eccessivamente grande)
	 * */
	public synchronized void releaseConnection(Connection toRelease) {
		if (this.freeConnections.size() < CONNECTION_POOL_SIZE) {
			this.freeConnections.add(toRelease);
		} else {
			try {
				toRelease.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean checkConnection(
		String host,
		String user,
		String password,
		int port,
		String dbName
	) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String connectionUri =
			"jdbc:mysql://" +
			host +
			":" +
			port +
			"/?useUnicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDateTimeCode=false&serverTimezone=Europe/Rome&allowMultiQueries=true";
		try {
			DriverManager.getConnection(connectionUri, user, password).close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private Connection createConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(this.connectionURI, user, password);
		return conn;
	}
}
