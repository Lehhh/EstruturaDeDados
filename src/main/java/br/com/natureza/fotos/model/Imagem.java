package br.com.natureza.fotos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "principal")
public class Imagem {

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String descricao;
	private String categoria;
	private String url;
	private int id_metodo;
	
	

	public int getId_metodo() {
		return id_metodo;
	}

	public void setId_metodo(int id_metodo) {
		this.id_metodo = id_metodo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Imagem(String nome, String descricao, String categoria, String url, int id_metodo) {
		this.id=null;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.url = url;
		this.id_metodo = id_metodo;
	}

	public Imagem() {
		
	}
	
	
	

}
