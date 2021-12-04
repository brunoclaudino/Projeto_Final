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

@Entity                                                // Define classe como endidade
public class PessoaJuridica extends Cliente{           // Classe herda de cliente
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Campo Razão Social Vazio")     // Campo não nulo. Pj deve ter uma razao social
	@Size(min=3)                                       // Tamanho mínimo de 3 caracteres
	private String razaoSocial;                        // Armazena a razão social
	
	@Size(min=5, max=50)                               // Define tamanho de 5 a 50 caracters
	private String nomeFantasia;                       // Armazena o nome fantasia
	
	@NotNull(message = "Campo CNPJ Vazio")             // Campo não nulo. Pj deve ter cnpj
	@Size(min=14, max=14)                              // Define o tamanho como 14 caracteres
	@Column(unique =  true)                            // Define como campo único
	private String cnpj;                               // Armazena o cnpj
	
	@Size(min=3)                                       // Tamanho mínimo de 3 caracters
	@Column(unique = true)                             // Define como atributo único na tabela
	private String inscricaoEstadual;                  // Armazena inscrição estadual
	
	@NotNull(message = "Campo Data Vazio")             // Campo não nulo. Pj deve ter dada de fundação
	@Past                                              // Define como uma data do passado
	@JsonFormat(pattern = "dd/MM/yyyy")                // Formata a data como desejado
	private LocalDate dataFundacao;                    // Armazena data de fundação
	
	/* Segue abaixo os construtores, Getters and Setters e Hashs*/
	public PessoaJuridica() {
		super();
	}
	
	public PessoaJuridica( @NotNull @Email @Size(min = 8) String email,
			@NotNull @Size(min = 14, max = 14) String telefone, Endereco endereco,
			@NotNull(message = "Campo Razão Social Vazio") @Size(min = 3) String razaoSocial,
			@NotNull(message = "Campo Nome Fantasia Vazio") @Size(min = 5, max = 50) String nomeFantasia,
			@NotNull(message = "Campo CNPJ Vazio") @Size(min = 14, max = 14) String cnpj,
			@NotNull(message = "Campo Inscrição Estual Vazio") @Size(min = 3) String inscricaoEstadual,
			@NotNull(message = "Campo Data Vazio") @Past String dataFundacao) throws ParseException {
		super(email, telefone, endereco);
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		setDataFundacao(dataFundacao);
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(String dataFundacao) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataFundacao = LocalDate.parse(dataFundacao, formatter);
	}
}