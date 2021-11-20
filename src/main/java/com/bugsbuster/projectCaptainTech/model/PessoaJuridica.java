package com.bugsbuster.projectCaptainTech.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class PessoaJuridica extends Cliente{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Campo Razão Social Vazio")
	@Size(min=3)
	private String razaoSocial;
	
	@NotNull(message = "Campo Nome Fantasia Vazio")
	@Size(min=5, max=50)
	private String nomeFantasia;
	
	@NotNull(message = "Campo CNPJ Vazio")
	@Size(min=14, max=14)
	@Column(unique =  true)
	private String cnpj;
	
	@NotNull(message = "Campo Inscrição Estual Vazio")
	@Size(min=3)
	private String inscricaoEstadual;
	
	@NotNull(message = "Campo Data Vazio")
	@Size(min=3)
	@Past
	private Date dataFuncadacao;
	
	public PessoaJuridica() {
		super();
	}
	
	

	public PessoaJuridica(@NotNull String dataCadastro, @NotNull @Email @Size(min = 8) String email,
			@NotNull @Size(min = 14, max = 14) String telefone, Endereco endereco,
			@NotNull(message = "Campo Razão Social Vazio") @Size(min = 3) String razaoSocial,
			@NotNull(message = "Campo Nome Fantasia Vazio") @Size(min = 5, max = 50) String nomeFantasia,
			@NotNull(message = "Campo CNPJ Vazio") @Size(min = 14, max = 14) String cnpj,
			@NotNull(message = "Campo Inscrição Estual Vazio") @Size(min = 3) String inscricaoEstadual,
			@NotNull(message = "Campo Data Vazio") @Size(min = 3) @Past String dataFuncadacao) throws ParseException {
		super(dataCadastro, email, telefone, endereco);
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		setDataFuncadacao(dataFuncadacao);
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

	public Date getDataFuncadacao() {
		return dataFuncadacao;
	}

	public void setDataFuncadacao(String dataFuncadacao) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		this.dataFuncadacao = formato.parse(dataFuncadacao);
	}
	
}
