package com.bugsbuster.projectCaptainTech.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_cliente;
	@NotNull
	private LocalDate dataCadastro;
	@NotNull
	@Email
	@Size(min=8)
	private String email;
	@NotNull
	@Size(min=14, max=14)
	private String telefone;
	
	
	@JoinColumn(name="id_enderecoFk", referencedColumnName = "id_endereco")
	@NotNull(message = "Campo Endereco vazio - Classe Cliente")
	@OneToOne
	private Endereco endereco;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 1") 
	private boolean ativo;         // Deleção Lógica
	
	public Cliente() {
		super();
	}

	public Cliente( @NotNull String dataCadastro, @NotNull @Email @Size(min = 8) String email,
			@NotNull @Size(min = 14, max = 14) String telefone, Endereco endereco) {
		super();
		setDataCadastro(dataCadastro);
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataCadastro = LocalDate.parse(dataCadastro, date);
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
