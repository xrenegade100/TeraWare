package net.teraware.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import net.teraware.model.interf.IBean;

public class OrdineBean extends IBean implements Serializable {

	private static final long serialVersionUID = -180567153743308798L;
	private UUID idOrdine;
	private int idUtente;
	private Date data;
	private String stato;

	public OrdineBean() {
		this.idOrdine = null;
		this.idUtente = 0;
		this.data = null;
		this.stato = null;
	}

	public OrdineBean(UUID idOrdine, int idUtente, Date data, String stato) {
		this.idOrdine = idOrdine;
		this.idUtente = idUtente;
		this.data = data;
		this.stato = stato;
	}

	public UUID getIdOrdine() {
		return idOrdine;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public Date getData() {
		return data;
	}

	public String getStato() {
		return stato;
	}

	public void setIdOrdine(UUID idOrdine) {
		this.idOrdine = idOrdine;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
}
