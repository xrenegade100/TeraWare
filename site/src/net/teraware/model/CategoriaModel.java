package net.teraware.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import net.teraware.manager.DatabaseManager;
import net.teraware.model.bean.CategoriaBean;
import net.teraware.model.interf.IModel;

public class CategoriaModel implements IModel<CategoriaBean> {

	private static final String TABLE = "categoria";

	@Override
	public void doSave(CategoriaBean categoria) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "INSERT INTO " + TABLE + " (nome)" + " VALUES (?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, categoria.getNome());
		ps.executeUpdate();

		DatabaseManager.getInstance().releaseConnection(con);
	}

	@Override
	public boolean doDelete(int idCategoria) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "DELETE FROM " + TABLE + " WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, idCategoria);
		int result = ps.executeUpdate();

		DatabaseManager.getInstance().releaseConnection(con);
		if (result == 1) return true;
		return false;
	}

	@Override
	public CategoriaBean doRetrieveByKey(int idCategoria) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE + " WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, idCategoria);
		ResultSet rs = ps.executeQuery();
		CategoriaBean categoria = null;

		while (rs.next()) {
			categoria = new CategoriaBean(rs.getInt(1), rs.getString(2));
		}
		DatabaseManager.getInstance().releaseConnection(con);
		return categoria;
	}

	public CategoriaBean doRetrieveByName(String nomeCategoria) throws SQLException {
		Connection con = DatabaseManager.getInstance().getConnection();
		String query1 = "SELECT id FROM " + TABLE + " WHERE nome = ?";

		PreparedStatement ps = con.prepareStatement(query1);
		ps.setString(1, nomeCategoria);
		ResultSet rs = ps.executeQuery();
		CategoriaBean categoria = null;

		if (rs.next()) {
			categoria = new CategoriaBean(rs.getInt(1), nomeCategoria);
		}

		DatabaseManager.getInstance().releaseConnection(con);
		return categoria;
	}

	@Override
	public Collection<CategoriaBean> doRetrieveAll(String order) throws SQLException {
		ArrayList<CategoriaBean> categorie = new ArrayList<>();
		Connection con = DatabaseManager.getInstance().getConnection();

		String query = "SELECT * FROM " + TABLE;
		if (order != null && !order.equals("")) {
			query += " ORDER BY " + order;
		}

		PreparedStatement ps1 = con.prepareStatement(query);
		ResultSet rs = ps1.executeQuery();
		while (rs.next()) {
			CategoriaBean categoria = new CategoriaBean(rs.getInt(1), rs.getString(2));
			categorie.add(categoria);
		}

		DatabaseManager.getInstance().releaseConnection(con);
		return categorie;
	}
}
