package net.teraware.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import net.teraware.manager.DatabaseManager;
import net.teraware.model.bean.CategoriaBean;
import net.teraware.model.bean.ProdottoBean;
import net.teraware.model.interf.IModel;

public class ProdottoModel implements IModel<ProdottoBean> {

	private static final String TABLE = "prodotto";

	@Override
	public void doSave(ProdottoBean prodotto) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query;
		if (prodotto.getIdProdotto() <= 0) {
			query =
				"INSERT INTO " +
				TABLE +
				" (nome, brand, prezzo, quantita, image_url, informazioni)" +
				" VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, prodotto.getNome());
			ps.setString(2, prodotto.getBrand());
			ps.setFloat(3, prodotto.getPrezzo());
			ps.setInt(4, prodotto.getQuantita());
			ps.setString(5, prodotto.getImageUrl());
			ps.setString(6, prodotto.getInformazioni());
			ps.executeUpdate();

			String query2 =
				"INSERT INTO categoria_prodotto VALUES((select categoria.id from categoria where categoria.nome = ?), (SELECT MAX(id) FROM prodotto));";
			PreparedStatement ps1 = con.prepareStatement(query2);
			ps1.setString(1, prodotto.getCategoria());
			ps1.executeUpdate();
		} else {
			String imageUrl = prodotto.getImageUrl();
			String img = imageUrl != null ? " ,image_url = ?" : "";
			query =
				"UPDATE " +
				TABLE +
				" SET nome = ?, brand = ?, prezzo = ?, quantita = ?, informazioni = ?, visibile = ?" +
				img +
				" WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, prodotto.getNome());
			ps.setString(2, prodotto.getBrand());
			ps.setFloat(3, prodotto.getPrezzo());
			ps.setInt(4, prodotto.getQuantita());
			ps.setString(5, prodotto.getInformazioni());
			ps.setBoolean(6, prodotto.isVisibile());
			if (imageUrl != null) {
				ps.setString(7, imageUrl);
			}
			ps.setInt(imageUrl != null ? 8 : 7, prodotto.getIdProdotto());
			ps.executeUpdate();
			String query2 =
				"UPDATE categoria_prodotto SET id_categoria = (SELECT categoria.id FROM categoria WHERE categoria.nome = ?) WHERE id_prodotto = ?";
			PreparedStatement ps1 = con.prepareStatement(query2);
			ps1.setString(1, prodotto.getCategoria());
			ps1.setInt(2, prodotto.getIdProdotto());
			ps1.executeUpdate();
		}

		DatabaseManager.getInstance().releaseConnection(con);
	}

	@Override
	public boolean doDelete(int idProdotto) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "DELETE FROM " + TABLE + " WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, idProdotto);
		int result = ps.executeUpdate();

		DatabaseManager.getInstance().releaseConnection(con);
		if (result == 1) return true;
		return false;
	}

	public Collection<ProdottoBean> searchByName(String name) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE + " WHERE nome LIKE ?";
		PreparedStatement ps1 = con.prepareStatement(query);
		ps1.setString(1, name + "%");
		ResultSet rs = ps1.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		return convertResultSet(rs);
	}

	@Override
	public ProdottoBean doRetrieveByKey(int idProdotto) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE + " WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, idProdotto);
		ResultSet rs = ps.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		ArrayList<ProdottoBean> prodotti = convertResultSet(rs);

		if (prodotti.isEmpty()) {
			return null;
		}
		return prodotti.get(0);
	}

	public int getNumProdottiByCategory(String nomeCategoria) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();
		String query =
			"SELECT COUNT(*) from prodotto join categoria_prodotto WHERE id_categoria = ? AND id_prodotto = id;";

		CategoriaBean categoria = new CategoriaModel().doRetrieveByName(nomeCategoria);

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, categoria.getIdCategoria());
		ResultSet rs = ps.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		rs.next();
		return rs.getInt(1);
	}

	public int getNumProdotti() throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();
		String query = "SELECT COUNT(*) from prodotto;";

		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		rs.next();
		return rs.getInt(1);
	}

	public ArrayList<ProdottoBean> doRetrieveByCategory(String nomeCategoria, int skip, int limit)
		throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();
		String query =
			"SELECT prodotto.* FROM prodotto JOIN categoria_prodotto WHERE id_categoria = ? AND id_prodotto = prodotto.id LIMIT ?,?";

		CategoriaBean categoria = new CategoriaModel().doRetrieveByName(nomeCategoria);

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, categoria.getIdCategoria());
		ps.setInt(2, skip);
		ps.setInt(3, limit);
		ResultSet rs = ps.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		ArrayList<ProdottoBean> prodotti = convertResultSet(rs);

		return prodotti;
	}
	
	/**
	 * Ottieni i prodotti in ordine decrescente in base alle quantita' di vendita.
	 * */
	public ArrayList<ProdottoBean> doRetrieveMostSold(int skip, int limit)
			throws SQLException {
			Connection con = DatabaseManager.getInstance().getConnection();
			String query =
				"SELECT prodotto.*, SUM(prodotto_ordine.quantita) as p_quantita FROM prodotto_ordine RIGHT JOIN prodotto ON id_prodotto = prodotto.id GROUP BY prodotto.id ORDER BY p_quantita DESC LIMIT ?,?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, skip);
			ps.setInt(2, limit);
			ResultSet rs = ps.executeQuery();

			DatabaseManager.getInstance().releaseConnection(con);
			ArrayList<ProdottoBean> prodotti = convertResultSet(rs);

			return prodotti;
		}

	@Override
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException {
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

	public Collection<ProdottoBean> doRetrieveAll(String order, int skip, int limit)
		throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE;
		if (order != null && !order.equals("")) {
			query += " ORDER BY " + order;
		}
		query += " LIMIT ?, ?";
		PreparedStatement ps1 = con.prepareStatement(query);
		ps1.setInt(1, skip);
		ps1.setInt(2, limit);
		ResultSet rs = ps1.executeQuery();

		DatabaseManager.getInstance().releaseConnection(con);
		return convertResultSet(rs);
	}

	public void decreaseQuantity(ProdottoBean prodotto, int quantita) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query2 = "UPDATE " + TABLE + " SET quantita = quantita - ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query2);
		ps.setInt(1, quantita);
		ps.setInt(2, prodotto.getIdProdotto());
		ps.executeUpdate();

		DatabaseManager.getInstance().releaseConnection(con);
	}

	public String getCategoriaProdotto(ProdottoBean prodotto) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query =
			"select categoria.nome " +
			"from categoria_prodotto join prodotto join categoria " +
			"where categoria_prodotto.id_prodotto = ? && categoria.id = categoria_prodotto.id_categoria";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, prodotto.getIdProdotto());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) return rs.getString(1);
		return null;
	}

	private ArrayList<ProdottoBean> convertResultSet(ResultSet rs) {
		ArrayList<ProdottoBean> prodotti = new ArrayList<>();

		try {
			while (rs.next()) {
				ProdottoBean prodotto = new ProdottoBean(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getFloat(4),
					rs.getInt(5),
					rs.getString(6),
					rs.getString(7),
					rs.getBoolean(8)
				);
				prodotti.add(prodotto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prodotti;
	}
}
