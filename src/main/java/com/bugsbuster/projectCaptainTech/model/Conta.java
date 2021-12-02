package com.bugsbuster.projectCaptainTech.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Conta implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_conta;
	
	@NotNull(message = "Campo Numero vazio - Classe Conta")
	private Integer numero;
	
	@NotNull(message = "Campo Agencia vazio - Classe Conta")
	private static Integer agencia = 0001;
	
	@NotNull
	private Double saldo;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAbertura = new java.sql.Date(System.currentTimeMillis());
	
	@OneToOne
	@JoinColumn(name="id_clienteFk", referencedColumnName = "id_cliente")
	@NotNull(message = "Campo cliente vazio - Classe Conta")
	private Cliente cliente;
	
	
	public Conta() {
		super();
	}

	public Conta(@NotNull(message = "Campo Numero vazio - Classe Conta")Integer numero,
			@NotNull(message = "Campo cliente vazio - Classe Conta") Cliente cliente, 
			@NotNull Double saldo) {
		super();
		this.numero = numero;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public Integer getId_conta() {
		return id_conta;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_conta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(id_conta, other.id_conta);
	}
}