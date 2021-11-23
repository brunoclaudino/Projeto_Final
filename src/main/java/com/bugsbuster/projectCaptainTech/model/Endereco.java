package com.bugsbuster.projectCaptainTech.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_endereco;
	
	@NotNull(message = "Campo Logradouro Vazio")
	@Size(min=1)
	private String logradouro;
	
	private Integer numero;
	
	private String bairro;
	
	@NotNull(message = "Campo Cidade Vazio")
	@Size(min=3)
	private String cidade;
	
	@NotNull(message = "Campo Estado Vazio")
	@Enumerated(EnumType.STRING)
	private EnumEstado estado;
	
	@NotNull(message = "Campo CEP Vazio")
	@Size(min=8)
	private String cep;
	
	public Endereco() {
		super();
	}
	
	public Endereco(@NotNull(message = "Campo Logradouro Vazio") @Size(min = 1) String logradouro,
			Integer numero, String bairro, @NotNull(message = "Campo Cidade Vazio") @Size(min = 3) String cidade,
			@NotNull(message = "Campo Estado Vazio") EnumEstado estado,
			@NotNull(message = "Campo CEP Vazio") @Size(min = 8) String cep) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public Integer getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(Integer id_endereco) {
		this.id_endereco = id_endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public EnumEstado getEstado() {
		return estado;
	}

	public void setEstado(EnumEstado estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_endereco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(id_endereco, other.id_endereco);
	}
}
