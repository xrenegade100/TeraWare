package net.teraware.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import net.teraware.manager.DatabaseManager;
import net.teraware.model.bean.OrdineBean;
import net.teraware.model.bean.UtenteBean;
import net.teraware.model.interf.IModel;

public class OrdineModel implements IModel<OrdineBean> {

	private static final String TABLE = "ordine";

	@Override
	public void doSave(OrdineBean ordine) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query =
			"INSERT INTO " +
			TABLE +
			" (id, id_utente, _data, stato)" +
			" VALUES (?,?,CURRENT_TIMESTAMP(),?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, ordine.getIdOrdine().toString());
		ps.setInt(2, ordine.getIdUtente());
		ps.setString(3, ordine.getStato());
		ps.executeUpdate();

		DatabaseManager.getInstance().releaseConnection(con);
	}

	@Override
	public boolean doDelete(int idOrdine) throws SQLException {
		return false;
	}

	public OrdineBean doRetrieveByKey(UUID idOrdine) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE + " WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, idOrdine.toString());
		ResultSet rs = ps.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		ArrayList<OrdineBean> ordine = convertResultSet(rs);

		if (ordine.isEmpty()) {
			return null;
		}

		return ordine.get(0);
	}

	@Override
	public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException {
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

	public ArrayList<OrdineBean> doRetriveByUser(UtenteBean user, String order)
		throws SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String query = "SELECT * FROM " + TABLE + " WHERE id_utente = " + user.getIdUtente();
		if (order != null) {
			query += " ORDER BY " + order;
		}
		PreparedStatement st = conn.prepareStatement(query);
		ResultSet result = st.executeQuery();

		DatabaseManager.getInstance().releaseConnection(conn);
		return convertResultSet(result);
	}

	public ArrayList<OrdineBean> doRetriveByUser(
		UtenteBean user,
		String order,
		int skip,
		int limit
	) throws SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String query = "SELECT * FROM " + TABLE + " WHERE id_utente = ?";
		if (order != null) {
			query += " ORDER BY " + order;
		}
		query += " LIMIT ?, ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(1, user.getIdUtente());
		st.setInt(2, skip);
		st.setInt(3, limit);
		ResultSet result = st.executeQuery();
		DatabaseManager.getInstance().releaseConnection(conn);
		return convertResultSet(result);
	}

	public ArrayList<OrdineBean> doRetriveByDate(Date init, Date end, int skip, int limit)
		throws SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String query =
			"SELECT * FROM " +
			TABLE +
			" WHERE _data >= ? AND _data <= ? ORDER BY _data DESC LIMIT ?, ?"; // LIMIT skip, limit
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(init));
		st.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(end) + " 23:59:59");
		st.setInt(3, skip);
		st.setInt(4, limit);
		ResultSet result = st.executeQuery();
		DatabaseManager.getInstance().releaseConnection(conn);
		return convertResultSet(result);
	}

	private ArrayList<OrdineBean> convertResultSet(ResultSet result) {
		ArrayList<OrdineBean> ordini = new ArrayList<>();

		try {
			while (result.next()) {
				OrdineBean ordine = new OrdineBean();
				ordine.setIdOrdine(UUID.fromString(result.getString(1)));
				ordine.setIdUtente(result.getInt(2));
				ordine.setData(new Date(result.getTimestamp(3).getTime()));
				ordine.setStato(result.getString(4));
				ordini.add(ordine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ordini;
	}

	@Override
	public OrdineBean doRetrieveByKey(int objectId) throws SQLException {
		return null;
	}
}
