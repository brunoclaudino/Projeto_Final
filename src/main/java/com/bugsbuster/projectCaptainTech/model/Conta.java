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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Conta implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_conta;
	
	@NotNull(message = "Campo Numero vazio - Classe Conta")
	@Size(min=14, max=14)
	private Integer numero;
	
	@NotNull(message = "Campo Agencia vazio - Classe Conta")
	@Size(min=14, max=14)
	private Integer agencia;
	
	@NotNull
	private Double saldo;
	
	@NotNull
	@Column(name = "data_abertura")
	private LocalDate dataAbertura;
	
	@OneToOne
	@JoinColumn(name="id_clienteFk", referencedColumnName = "id_cliente")
	@NotNull(message = "Campo cliente vazio - Classe Conta")
	private Cliente cliente;
	
	
	public Conta() {
		super();
	}

	public Conta(@NotNull(message = "Campo Numero vazio - Classe Conta") @Size(min = 14, max = 14) Integer numero,
			@NotNull(message = "Campo Agencia vazio - Classe Conta") @Size(min = 14, max = 14) Integer agencia,
			@NotNull Double saldo, @NotNull LocalDate dataAbertura,
			@NotNull(message = "Campo cliente vazio - Classe Conta") Cliente cliente) {
		super();
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = saldo;
		this.dataAbertura = dataAbertura;
		this.cliente = cliente;
	}

	public Integer getId() {
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

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataAbertura = LocalDate.parse(dataAbertura, date);
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
