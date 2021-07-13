package net.teraware.model.bean;

import java.io.Serializable;
import net.teraware.model.interf.IBean;

public class SocietaBean extends IBean implements Serializable {

	private static final long serialVersionUID = 823255686718318210L;
	private String partitaIva;
	private String nome;
	private String email;
	private String provincia;
	private String cap;
	private String via;
	private String civico;
	private String note;

	public SocietaBean() {
		this.partitaIva = null;
		this.nome = null;
		this.email = null;
		this.provincia = null;
		this.cap = null;
		this.via = null;
		this.civico = null;
		this.note = null;
	}

	public SocietaBean(
		String partitaIva,
		String nome,
		String email,
		String provincia,
		String cap,
		String via,
		String civico,
		String note
	) {
		this.partitaIva = partitaIva;
		this.nome = nome;
		this.email = email;
		this.provincia = provincia;
		this.cap = cap;
		this.via = via;
		this.civico = civico;
		this.note = note;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getCap() {
		return cap;
	}

	public String getVia() {
		return via;
	}

	public String getCivico() {
		return civico;
	}

	public String getNote() {
		return note;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
