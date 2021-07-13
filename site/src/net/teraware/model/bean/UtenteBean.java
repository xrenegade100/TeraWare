package net.teraware.model.bean;

import java.io.Serializable;
import net.teraware.model.interf.IBean;

public class UtenteBean extends IBean implements Serializable {

	private static final long serialVersionUID = 299314862854593630L;
	private int idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private boolean oauth;
	private String propicUrl;
	private String provincia;
	private String cap;
	private String via;
	private String civico;
	private String note;
	private int role;

	public UtenteBean() {
		this.idUtente = 0;
		this.nome = null;
		this.cognome = null;
		this.email = null;
		this.password = null;
		this.oauth = false;
		this.propicUrl =
			"https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png";
		this.provincia = null;
		this.cap = null;
		this.via = null;
		this.civico = null;
		this.nome = null;
		this.role = 0;
	}

	public UtenteBean(
		int idUtente,
		String nome,
		String cognome,
		String email,
		String password,
		boolean oauth,
		String propicUrl,
		String provincia,
		String cap,
		String via,
		String civico,
		String note,
		int role
	) {
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.oauth = oauth;
		this.propicUrl = propicUrl;
		this.provincia = provincia;
		this.cap = cap;
		this.via = via;
		this.civico = civico;
		this.note = note;
		this.role = role;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public boolean isOauth() {
		return oauth;
	}

	public String getPropicUrl() {
		return propicUrl;
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

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPropicUrl(String propicUrl) {
		this.propicUrl = propicUrl;
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

	public void setOauth(boolean oauth) {
		this.oauth = oauth;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return (
			"UtenteBean [idUtente=" +
			idUtente +
			", nome=" +
			nome +
			", cognome=" +
			cognome +
			", email=" +
			email +
			", password=" +
			password +
			", oauth=" +
			oauth +
			", propicUrl=" +
			propicUrl +
			", provincia=" +
			provincia +
			", cap=" +
			cap +
			", via=" +
			via +
			", civico=" +
			civico +
			", note=" +
			note +
			", role=" +
			role +
			"]"
		);
	}
}
