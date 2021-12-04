package com.bugsbuster.projectCaptainTech.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity                                                                      // Define como entidade
public class Transferencia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id                                                                      // Define como identificador da classe/entidade
	@GeneratedValue(strategy = GenerationType.IDENTITY)                      // Gera o id dinâmicamente
	private int id_transferencia;
	
	@ManyToOne                                                               // Define um relacionamento 1 para N
	@JoinColumn(name = "conta_origem", referencedColumnName = "id_conta")    // Define atributo FK e onde referência
	@NotNull(message = "Campo contaOrigem vazio - Classe Transferencia")     // Campo não nulo. Transferência deve ter
	private Conta contaOrigem;                                               // uma conta de origem (onde saí o dinheiro)
	
	@ManyToOne                                                               // Define um relacionamento 1 para N
	@JoinColumn(name = "conta_destino", referencedColumnName = "id_conta")   // Define atributo FK e onde referência
	@NotNull(message = "Campo contaDestino vazio - Classe Transferencia")    // Campo não nulo. Transferencia deve ter
	private Conta contaDestino;                                              // uma conta de destino (onde entra o dinheiro)
	
	@NotNull(message = "Campo valor vazio - Classe Transferencia")           // Campo não nulo. Transferência deve ter um valor
	private double valor;                                                    // Armazena o valor da transferência
	
	@NotNull(message = "Campo data vazio - Classe Transferencia")            // Campo não nulo. Transferência deve ter uma data 
	private LocalDate data;                                                  // Armazena a data
	
	/* Segue abaixo os construtores, getters and setters e Hashs*/
	public Transferencia() {
		super();
	}
	
	public Transferencia(Conta contaOrigem, Conta contaDestino, double valor, String data) {
		super();
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		//setData(data);
	}

	public int getId() {
		return id_transferencia;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(String data) {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.data = LocalDate.parse(data, date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_transferencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transferencia other = (Transferencia) obj;
		return id_transferencia == other.id_transferencia;
	}
}
