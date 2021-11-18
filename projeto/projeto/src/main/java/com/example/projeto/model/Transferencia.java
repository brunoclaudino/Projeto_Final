package com.example.projeto.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transferencia implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private Conta contaOrigem;
	private Conta contaDestino;
	private double valor;
	private String data;
	public Transferencia() {
		super();
	}
	
	public Transferencia(int id, Conta contaOrigem, Conta contaDestino, double valor, String data) {
		super();
		this.id = id;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contaDestino, contaOrigem, data, id, valor);
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
		return contaDestino == other.contaDestino && contaOrigem == other.contaOrigem
				&& Objects.equals(data, other.data) && id == other.id
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}
}
