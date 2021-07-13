package net.teraware.model.bean;

import java.io.Serializable;
import java.util.UUID;
import net.teraware.model.interf.IBean;

public class ProdottoOrdineBean extends IBean implements Serializable {

	private static final long serialVersionUID = -1258634432830603623L;
	private int idProdottoOrdine;
	private UUID idOrdine;
	private ProdottoBean prodotto;
	private float prezzoEffettivo;
	private float iva;
	private int quantita;

	public ProdottoOrdineBean() {
		this.idProdottoOrdine = 0;
		this.prodotto = null;
		this.prezzoEffettivo = 0;
		this.iva = 0;
		this.quantita = 0;
	}

	public ProdottoOrdineBean(
		int idProdottoOrdine,
		UUID idOrdine,
		ProdottoBean prodotto,
		float prezzoEffettivo,
		float iva,
		int quantita
	) {
		this.idProdottoOrdine = idProdottoOrdine;
		this.idOrdine = idOrdine;
		this.prodotto = prodotto;
		this.prezzoEffettivo = prezzoEffettivo;
		this.iva = iva;
		this.quantita = quantita;
	}

	public int getIdProdottoOrdine() {
		return idProdottoOrdine;
	}

	public UUID getIdOrdine() {
		return idOrdine;
	}

	public ProdottoBean getProdotto() {
		return prodotto;
	}

	public float getPrezzoEffettivo() {
		return prezzoEffettivo;
	}

	public float getIva() {
		return iva;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setIdProdottoOrdine(int idProdottoOrdine) {
		this.idProdottoOrdine = idProdottoOrdine;
	}

	public void setIdOrdine(UUID idOrdine) {
		this.idOrdine = idOrdine;
	}

	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}

	public void setPrezzoEffettivo(float prezzoEffettivo) {
		this.prezzoEffettivo = prezzoEffettivo;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}
