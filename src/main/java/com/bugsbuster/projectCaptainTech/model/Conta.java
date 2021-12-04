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

@Entity                                                                          // Define como uma entidade
public class Conta implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id                                                                          // Atributo identificador de contas
	@GeneratedValue(strategy = GenerationType.IDENTITY)                          // Gera id dinâmicamente
	private Integer id_conta;                                                    // Atributo identificador dessa classe/entidade
	
	@NotNull(message = "Campo Numero vazio - Classe Conta")                      // Campo não nulo. Uma conta deve ter um número
	private Integer numero;                                                      // Numero da conta
	
	@NotNull(message = "Campo Agencia vazio - Classe Conta")                     // Campo não nulo. Uma conta deve ter uma agência
	private static Integer agencia = 0001;                                       // Por ser uma fintech, todos os usuários recebem
	                                                                             // uma única agência
	@NotNull                                                                     // Campo não nulo. Uma conta deve ter um saldo
	private Double saldo;                                                        // campo que armazena o saldo
	
	@NotNull                                                                     // Campo não nulo. Conta deve ter dada de abertura
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone="GMT-3")               // Define a data de abertura
	@Temporal(TemporalType.TIMESTAMP)                                            // Usado para a persistência de datas
	private Date dataAbertura = new java.sql.Date(System.currentTimeMillis());   // Armazena o horário local
	
	@OneToOne                                                                    // Define relacionamento com cliente 1 para 1
	@JoinColumn(name="id_clienteFk", referencedColumnName = "id_cliente")        // Define nome da FK e onde referencia
	@NotNull(message = "Campo cliente vazio - Classe Conta")                     // Campo não nulo. Conta deve ter um cliente dono
	private Cliente cliente;                                                     // Campo que recebe o cliente
	
	
	/* Segue abaixo os construtores, getters and setters e Hashs*/
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