package br.com.natureza.fotos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "analise")
public class AnalisedeDados {

	
	@Id
	@GeneratedValue
	private Integer id;
	private String metodo;
	private long tempo;
	private long trocas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public long getTempo() {
		return tempo;
	}
	public void setTempo(long tempo) {
		this.tempo = tempo;
	}
	public long getTrocas() {
		return trocas;
	}
	public void setTrocas(long trocas) {
		this.trocas = trocas;
	}
	
	
	
	public AnalisedeDados(String metodo,long tempo, long trocas) {

		this.id = null;
		this.metodo = metodo;
		this.trocas = trocas;
		this.tempo = tempo;
	}
	
	public AnalisedeDados() {
		super();
	}
}
