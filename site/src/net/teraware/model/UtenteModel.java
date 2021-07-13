package net.teraware.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import net.teraware.manager.DatabaseManager;
import net.teraware.model.bean.UtenteBean;
import net.teraware.model.interf.IModel;

public class UtenteModel implements IModel<UtenteBean> {

	private static final String TABLE = "utente";

	@Override
	public void doSave(UtenteBean utente) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query =
			"USE teraware;" +
			"INSERT INTO " +
			TABLE +
			" (nome, cognome, email, _password, oauth, propic_url, provincia, cap, via, civico, note, _role)" +
			" VALUES (?,?,?,MD5(?),?,?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, utente.getNome());
		ps.setString(2, utente.getCognome());
		ps.setString(3, utente.getEmail());
		ps.setString(4, utente.getPassword());
		ps.setBoolean(5, utente.isOauth());
		ps.setString(6, utente.getPropicUrl());
		ps.setString(7, utente.getProvincia());
		ps.setString(8, utente.getCap());
		ps.setString(9, utente.getVia());
		ps.setString(10, utente.getCivico());
		ps.setString(11, utente.getNote());
		ps.setInt(12, utente.getRole());
		ps.executeUpdate();

		DatabaseManager.getInstance().releaseConnection(con);
	}

	@Override
	public boolean doDelete(int idUtente) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "DELETE FROM " + TABLE + " WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, idUtente);
		int result = ps.executeUpdate();

		DatabaseManager.getInstance().releaseConnection(con);
		if (result == 1) return true;
		return false;
	}

	@Override
	public UtenteBean doRetrieveByKey(int idUtente) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE + " WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, idUtente);
		ResultSet rs = ps.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		ArrayList<UtenteBean> utenti = convertResultSet(rs);

		if (utenti.isEmpty()) {
			return null;
		}
		return utenti.get(0);
	}

	@Override
	public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE;
		if (order != null && !order.equals("")) {
			query += " ORDER BY " + order;
		}

		PreparedStatement ps1 = con.prepareStatement(query);
		ResultSet rs = ps1.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		return convertResultSet(rs);
	}

	public UtenteBean doRetrieveByEmailAndPassword(String email, String password)
		throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE + " WHERE email = ? AND _password = MD5(?)";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		ArrayList<UtenteBean> utenti = convertResultSet(rs);

		if (utenti.isEmpty()) {
			return null;
		}
		return utenti.get(0);
	}

	public UtenteBean doRetrieveByEmail(String email) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE + " WHERE email = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		ArrayList<UtenteBean> utenti = convertResultSet(rs);

		if (utenti.isEmpty()) {
			return null;
		}
		return utenti.get(0);
	}

	private ArrayList<UtenteBean> convertResultSet(ResultSet rs) {
		ArrayList<UtenteBean> utenti = new ArrayList<>();

		try {
			while (rs.next()) {
				UtenteBean utente = new UtenteBean(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getBoolean(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11),
					rs.getString(12),
					rs.getInt(13)
				);
				utenti.add(utente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return utenti;
	}
}
