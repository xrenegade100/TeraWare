package net.teraware.model.helper;

import net.teraware.model.Cart;
import net.teraware.model.bean.ProdottoBean;

public class CartEntry {

	private ProdottoBean prodotto;
	private int quantita;

	public CartEntry(ProdottoBean prodotto, int quantita) {
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public ProdottoBean getProdotto() {
		return prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodotto == null) ? 0 : prodotto.hashCode());
		result = prime * result + quantita;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cart)) return false;
		ProdottoBean other = (ProdottoBean) obj;
		if (other.getIdProdotto() == this.prodotto.getIdProdotto()) return true;
		return false;
	}

	@Override
	public String toString() {
		return "CartEntry [prodotto=" + prodotto + ", quantita=" + quantita + "]";
	}
}
