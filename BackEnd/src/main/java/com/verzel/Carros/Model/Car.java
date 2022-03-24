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
public class Car {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long idCar;
	private String marca;
	private String modelo;
	private String foto;
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "criadores")
	@JsonIgnoreProperties({ "minhasPublicacoes" })
	private Usuario publicador;
	
	 @OneToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "valores")
	  private Venda carros;
	
	public long getIdCar() {
		return idCar;
	}
	public void setIdCar(long idCar) {
		this.idCar = idCar;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Usuario getPublicador() {
		return publicador;
	}
	public void setPublicador(Usuario publicador) {
		this.publicador = publicador;
	}
	public Venda getCarros() {
		return carros;
	}
	public void setCarros(Venda carros) {
		this.carros = carros;
	}
	
}
