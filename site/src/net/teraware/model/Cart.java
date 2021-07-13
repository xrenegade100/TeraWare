package net.teraware.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import net.teraware.model.helper.CartEntry;

public class Cart implements Serializable {

	private static final long serialVersionUID = 3497262402613555555L;
	private HashSet<CartEntry> prodotti = new HashSet<>();

	public Cart() {
		this.prodotti = new HashSet<>();
	}

	public Iterator<CartEntry> getProdotti() {
		return this.prodotti.iterator();
	}

	public int getNumProdotti() {
		return this.prodotti.size();
	}

	public void set(CartEntry entry) {
		CartEntry prodotto = this.findById(entry.getProdotto().getIdProdotto());
		if (entry.getQuantita() <= 0) {
			this.prodotti.remove(prodotto);
			return;
		}
		if (prodotto != null && prodotto.getQuantita() != entry.getQuantita()) {
			this.prodotti.remove(prodotto);
			this.prodotti.add(entry);
		} else if (prodotto == null) {
			this.prodotti.add(entry);
		}
	}

	public void clear() {
		this.prodotti.clear();
	}

	public CartEntry findById(int productId) {
		for (CartEntry c : this.prodotti) {
			if (c.getProdotto().getIdProdotto() == productId) return c;
		}
		return null;
	}

	@Override
	public String toString() {
		return "CartBean [prodotti=" + prodotti + "]";
	}
}
