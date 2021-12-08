package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugsbuster.projectCaptainTech.model.PessoaJuridica;

/* Repositório de pessoa jurídica para comunicação com o banco de dados. */
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer> {
	/*Abaixo segue as Querys para pesquisa personalizadas*/
	
	/* Retorna pessoa jurídica com nome fantasia contendo a string passada. */
	public Iterable<PessoaJuridica> findByNomeFantasiaContaining(String nome);
	
	/* Retorna pessoa jurídica pelo cnpj. */
	public Iterable<PessoaJuridica> findByCnpj(String cnpj);
	
	/* Retorna pessoa jurídica pelo telefone. */
	public Iterable<PessoaJuridica> findByTelefone(String telefone);
	
	/* Retorna pessoa jurídica com a razão social contendo a string passada. */
	public Iterable<PessoaJuridica> findByRazaoSocialContaining(String razaoSocial);
	
	/* Retorna pessoa jurídica com a inscrição estadual contendo a string passada. */
	public Iterable<PessoaJuridica> findByInscricaoEstadualContaining(String inscricaoEstadual);

}
