package com.bugsbuster.projectCaptainTech.model;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PessoaFisica extends Cliente{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Campo Nome Vazio")
	@Size(min=3)
	private String nome;
	@NotNull(message = "Campo CPF Vazio")
	@Size(min=11, max=11)
	@Column(unique = true)
	private String cpf;
	@NotNull(message = "Campo Data de Nascimento Vazio")
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	@NotNull
	@Size(min=3)
	private String ocupacao;
	
	public PessoaFisica() {
		super();
	}

	public PessoaFisica(@NotNull @Email @Size(min = 8) String email,
			@NotNull @Size(min = 14, max = 14) String telefone, Endereco endereco,
			@NotNull(message = "Campo Nome Vazio") @Size(min = 3) String nome,
			@NotNull(message = "Campo CPF Vazio") @Size(min = 11, max = 11) String cpf,
			@NotNull(message = "Campo CPF Vazio") @Past String dataNascimento,
			@NotNull @Size(min = 3) String ocupacao) throws ParseException {
		super(email, telefone, endereco);
		this.nome = nome;
		this.cpf = cpf;
		setDataNascimento(dataNascimento);
		this.ocupacao = ocupacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) throws ParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}
}
