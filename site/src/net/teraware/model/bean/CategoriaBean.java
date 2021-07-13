package net.teraware.model.bean;

import java.io.Serializable;
import net.teraware.model.interf.IBean;

public class CategoriaBean extends IBean implements Serializable {

	private static final long serialVersionUID = 5632096784227938885L;
	private int idCategoria;
	private String nome;

	public CategoriaBean() {
		this.idCategoria = 0;
		this.nome = null;
	}

	public CategoriaBean(int idCategoria, String nome) {
		this.idCategoria = idCategoria;
		this.nome = nome;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
