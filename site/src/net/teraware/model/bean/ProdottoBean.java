package net.teraware.model.bean;

import java.io.Serializable;
import net.teraware.model.interf.IBean;

public class ProdottoBean extends IBean implements Serializable {

	private static final long serialVersionUID = 6377561525795362075L;
	private int idProdotto;
	private String nome;
	private String brand;
	private float prezzo;
	private int quantita;
	private String imageUrl;
	private String informazioni;
	private boolean visibile;
	private String categoria;

	public ProdottoBean() {
		this.idProdotto = 0;
		this.nome = null;
		this.brand = null;
		this.prezzo = 0;
		this.quantita = 0;
		this.imageUrl = null;
		this.informazioni = null;
		this.visibile = true;
		categoria = null;
	}

	public ProdottoBean(
		int idProdotto,
		String nome,
		String brand,
		float prezzo,
		int quantita,
		String imageUrl,
		String informazioni,
		boolean visibile
	) {
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.brand = brand;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.imageUrl = imageUrl;
		this.informazioni = informazioni;
		this.visibile = visibile;
	}

	public ProdottoBean(
		int idProdotto,
		String nome,
		String brand,
		float prezzo,
		int quantita,
		String imageUrl,
		String informazioni,
		boolean visibile,
		String categoria
	) {
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.brand = brand;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.imageUrl = imageUrl;
		this.informazioni = informazioni;
		this.visibile = visibile;
		this.categoria = categoria;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public String getNome() {
		return nome;
	}

	public String getBrand() {
		return brand;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getInformazioni() {
		return informazioni;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setInformazioni(String informazioni) {
		this.informazioni = informazioni;
	}

	public boolean isVisibile() {
		return visibile;
	}

	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + idProdotto;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((informazioni == null) ? 0 : informazioni.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + Float.floatToIntBits(prezzo);
		result = prime * result + quantita;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ProdottoBean other = (ProdottoBean) obj;
		if (brand == null) {
			if (other.brand != null) return false;
		} else if (!brand.equals(other.brand)) return false;
		if (idProdotto != other.idProdotto) return false;
		if (imageUrl == null) {
			if (other.imageUrl != null) return false;
		} else if (!imageUrl.equals(other.imageUrl)) return false;
		if (informazioni == null) {
			if (other.informazioni != null) return false;
		} else if (!informazioni.equals(other.informazioni)) return false;
		if (nome == null) {
			if (other.nome != null) return false;
		} else if (!nome.equals(other.nome)) return false;
		if (Float.floatToIntBits(prezzo) != Float.floatToIntBits(other.prezzo)) return false;
		if (quantita != other.quantita) return false;
		return true;
	}

	@Override
	public String toString() {
		return (
			"ProdottoBean [idProdotto=" +
			idProdotto +
			", nome=" +
			nome +
			", brand=" +
			brand +
			", prezzo=" +
			prezzo +
			", quantita=" +
			quantita +
			", imageUrl=" +
			imageUrl +
			", informazioni=" +
			informazioni +
			"]"
		);
	}
}
