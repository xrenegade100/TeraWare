package net.teraware.model.bean;

import java.io.Serializable;
import net.teraware.model.interf.IBean;

public class CategoriaProdottoBean extends IBean implements Serializable {

	private static final long serialVersionUID = -8989024712762507602L;
	private int idCategoria;
	private int idProdotto;

	public CategoriaProdottoBean() {
		this.idCategoria = 0;
		this.idProdotto = 0;
	}

	public CategoriaProdottoBean(int idCategoria, int idProdotto) {
		this.idCategoria = idCategoria;
		this.idProdotto = idProdotto;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
}
