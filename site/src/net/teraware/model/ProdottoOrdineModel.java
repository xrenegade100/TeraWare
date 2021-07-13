package net.teraware.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import net.teraware.manager.DatabaseManager;
import net.teraware.model.bean.OrdineBean;
import net.teraware.model.bean.ProdottoBean;
import net.teraware.model.bean.ProdottoOrdineBean;
import net.teraware.model.interf.IModel;

public class ProdottoOrdineModel implements IModel<ProdottoOrdineBean> {

	private static final String TABLE = "prodotto_ordine";

	//TODO ottimizzare con query di join e popolamento prodottoBena manuale

	@Override
	public void doSave(ProdottoOrdineBean prodottoOrdine) throws SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String query =
			"INSERT INTO " +
			TABLE +
			" (id_prodotto, id_ordine, prezzo_effettivo, iva ,quantita) VALUES(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, prodottoOrdine.getProdotto().getIdProdotto());
		ps.setString(2, prodottoOrdine.getIdOrdine().toString());
		ps.setFloat(3, prodottoOrdine.getPrezzoEffettivo());
		ps.setFloat(4, prodottoOrdine.getIva());
		ps.setInt(5, prodottoOrdine.getQuantita());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public boolean doDelete(int idProdottoOrdine) throws SQLException {
		return false;
	}

	@Override
	public ProdottoOrdineBean doRetrieveByKey(int idProdottoOrdine) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE + " WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, idProdottoOrdine);
		ResultSet rs = ps.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		ArrayList<ProdottoOrdineBean> prodotti = convertResultSet(rs);

		if (prodotti.isEmpty()) {
			return null;
		}
		return prodotti.get(0);
	}

	@Override
	public Collection<ProdottoOrdineBean> doRetrieveAll(String order) throws SQLException {
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

	public ArrayList<ProdottoOrdineBean> doRetrivebyOrder(OrdineBean ordine) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query =
			"SELECT * FROM " + TABLE + " WHERE id_ordine = '" + ordine.getIdOrdine() + "'";
		PreparedStatement ps1 = con.prepareStatement(query);
		ResultSet rs = ps1.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		return convertResultSet(rs);
	}

	private ArrayList<ProdottoOrdineBean> convertResultSet(ResultSet rs) {
		ArrayList<ProdottoOrdineBean> listaProdotti = new ArrayList<>();

		try {
			while (rs.next()) {
				ProdottoBean prod = new ProdottoModel().doRetrieveByKey(rs.getInt(2));
				ProdottoOrdineBean prodotto = new ProdottoOrdineBean(
					rs.getInt(1),
					UUID.fromString(rs.getString(3)),
					prod,
					rs.getFloat(4),
					rs.getFloat(5),
					rs.getInt(6)
				);
				listaProdotti.add(prodotto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaProdotti;
	}
}
