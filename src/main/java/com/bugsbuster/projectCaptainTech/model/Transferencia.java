package com.bugsbuster.projectCaptainTech.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Transferencia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_transferencia;
	
	@ManyToOne
	@JoinColumn(name = "conta_origem", referencedColumnName = "id_conta")
	//@NotNull(message = "Campo contaOrigem vazio - Classe Transferencia")
	private Conta contaOrigem;
	
	@ManyToOne
	@JoinColumn(name = "conta_destino", referencedColumnName = "id_conta")
	//@NotNull(message = "Campo contaDestino vazio - Classe Transferencia")
	private Conta contaDestino;
	
	@NotNull(message = "Campo valor vazio - Classe Transferencia")
	private double valor;
	
	@NotNull(message = "Campo data vazio - Classe Transferencia")
	private Date data;
	
	public Transferencia() {
		super();
	}
	
	public Transferencia(Conta contaOrigem, Conta contaDestino, double valor, String data) throws ParseException {
		super();
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		setData(data);
	}

	public int getId_transferencia() {
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

	public Date getData() {
		return data;
	}

	public void setData(String data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS"); 
		this.data = formato.parse(data);
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
