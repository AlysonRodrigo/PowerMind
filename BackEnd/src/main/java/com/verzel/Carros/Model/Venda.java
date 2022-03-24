package com.verzel.Carros.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Venda {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long idVendas;
	private int quantParcela;
	private double valorParcela;
	private double valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "usuarios")
	@JsonIgnoreProperties({ "minhasCompras" })
	private Usuario minhasCompras;
	
	 @OneToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "valores")
	  private Car carros;

	public int getQuantParcela() {
		return quantParcela;
	}

	public void setQuantParcela(int quantParcela) {
		this.quantParcela = quantParcela;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Car getCarros() {
		return carros;
	}

	public void setCarros(Car carros) {
		this.carros = carros;
	}

	public long getIdVendas() {
		return idVendas;
	}

	public void setIdVendas(long idVendas) {
		this.idVendas = idVendas;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Usuario getMinhasCompras() {
		return minhasCompras;
	}

	public void setMinhasCompras(Usuario minhasCompras) {
		this.minhasCompras = minhasCompras;
	}
	
}
