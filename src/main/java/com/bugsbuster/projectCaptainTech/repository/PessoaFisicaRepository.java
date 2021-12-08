package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugsbuster.projectCaptainTech.model.PessoaFisica;

/* Repositório de pessoa física para a comunicação com o banco de dados. */
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer>{
	/*Abaixo segue as Querys para pesquisa personalizadas*/
	
	/* Acha uma pessoa física pelo nome contendo a string passada. */
	public Iterable<PessoaFisica> findByNomeContaining(String nome);
	
	/* Acha uma pessoa física pelo cpf. */
	public Iterable<PessoaFisica> findByCpf(String cpf);
	
	/* Acha uma pessoa física pelo telefone. */
	public Iterable<PessoaFisica> findByTelefone(String telefone);
	
	/* Acha uma pessoa física e ordena pela data de nascimento. */
	public Iterable<PessoaFisica> findByDataNascimentoOrderByDataNascimento(String data);
	
	/* Acha uma pessoa física e ordena pela ocupação. */
	public Iterable<PessoaFisica> findByOcupacaoOrderByOcupacao(String ocupacao);
}
