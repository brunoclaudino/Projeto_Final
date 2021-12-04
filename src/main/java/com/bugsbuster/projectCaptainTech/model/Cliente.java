package com.bugsbuster.projectCaptainTech.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity                                                                             // Define como uma entidade
@Inheritance(strategy = InheritanceType.JOINED)                                     // Define como a herença vai ser traduzida para o DB
public abstract class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id                                                                             // Tag que define atributo como identificador de tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY)                             // Realiza a definição automática datributo id
	private Integer id_cliente;                                                     // Atributo identificador de um cliente
	
	@NotNull                                                                        // Define o campo como não nulo
	@JsonFormat( pattern = "dd/MM/yyyy HH:mm:ss", timezone="GMT-3")                 // Realiza a formatação do campo
	@Temporal(TemporalType.TIMESTAMP)                                               // Notation usada para guardar datas
	private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());      // Coluna que irá armazenar a data de cadastro do cliente
	
	@NotNull                                                                        // Define como campo não nulo
	@Email                                                                          // Notation para definir campo como email
	@Size(min=8)                                                                    // Tamanho mínimo dado para string é 8 caracteres
	@Column(unique = true)                                                          // Dois clientes não podem ter emails iguais
	private String email;                                                           // Campo que guarda o email de cliente
	@NotNull                                                                        // Define como campo não nulo
	@Size(min=14, max=14)                                                           // Define o tamanho fixo de 14 caracteres
	private String telefone;                                                        // Campo que armazena o numero de telefone
	
	
	@JoinColumn(name="id_enderecoFk", referencedColumnName = "id_endereco")         // Campo que fará a referência a tabela endereço
	@NotNull(message = "Campo Endereco vazio - Classe Cliente")                     // Campo não nulo. Cliente deve ter um endereço
	@OneToOne(cascade = CascadeType.ALL)                                            // Define uma relação na tabela de 1 para 1
	private Endereco endereco;                                                      // Campo que armazena um endereço obtido
	
	@NotNull                                                                        // Campo não nulo. Cliente deve ter um status
	private boolean ativo = true;                                                   // Usado para deleção lógica. Cliente não são 
	                                                                                // apagados, somente são desativados.
	
	/* Segue abaixo os contrutores, getters and setters e hashs*/
	public Cliente() {
		super();
	}
	
	public Cliente(@NotNull @Email @Size(min = 8) String email,
			@NotNull @Size(min = 14, max = 14) String telefone,
			@NotNull(message = "Campo Endereco vazio - Classe Cliente") Endereco endereco) {
		super();
		this.email = email;
		this.telefone = telefone;
		//this.endereco = endereco;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public Date getDataCadastro() {
		return dataCadastro;
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