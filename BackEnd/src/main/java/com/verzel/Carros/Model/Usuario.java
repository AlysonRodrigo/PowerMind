package com.verzel.Carros.Model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Usuario {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)Long idUsuario;
	private @NotBlank String nome;
	private @NotBlank String email;
	private @NotBlank String senha;
	private String tipo;
	
	@OneToMany(mappedBy = "publicador", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "publicador" })
	private List<Car> minhasPublicacoes = new ArrayList<>();
	
	@OneToMany(mappedBy = "minhasCompras", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "minhasCompras" })
	private List<Venda> minhasCompras = new ArrayList<>();
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<Car> getMinhasPublicacoes() {
		return minhasPublicacoes;
	}
	public void setMinhasPublicacoes(List<Car> minhasPublicacoes) {
		this.minhasPublicacoes = minhasPublicacoes;
	}
	
	public List<Venda> getMinhasCompras() {
		return minhasCompras;
	}
	public void setMinhasCompras(List<Venda> minhasCompras) {
		this.minhasCompras = minhasCompras;
	} 
	
}
