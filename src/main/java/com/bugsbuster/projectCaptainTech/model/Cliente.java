package com.bugsbuster.projectCaptainTech.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_cliente;
	
	@NotNull
	@JsonFormat( pattern = "dd/MM/yyyy HH:mm:ss", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());
	
	@NotNull
	@Email
	@Size(min=8)
	@Column(unique = true)
	private String email;
	@NotNull
	@Size(min=14, max=14)
	private String telefone;
	
	
	@JoinColumn(name="id_enderecoFk", referencedColumnName = "id_endereco")
	@NotNull(message = "Campo Endereco vazio - Classe Cliente")
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@NotNull
	private boolean ativo = true;         // Deleção Lógica
	
	public Cliente() {
		super();
	}
	
	public Cliente(@NotNull @Email @Size(min = 8) String email,
			@NotNull @Size(min = 14, max = 14) String telefone,
			@NotNull(message = "Campo Endereco vazio - Classe Cliente") Endereco endereco) {
		super();
		this.email = email;
		this.telefone = telefone;
		//this.endereco = endereco;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_cliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id_cliente, other.id_cliente);
	}
}