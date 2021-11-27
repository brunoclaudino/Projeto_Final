package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugsbuster.projectCaptainTech.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer> {
	public Iterable<PessoaJuridica> findByNomeFantasiaContaining(String nome);
	
	public Iterable<PessoaJuridica> findByCnpj(String cnpj);
	
	public Iterable<PessoaJuridica> findByTelefone(String telefone);
	
	public Iterable<PessoaJuridica> findByRazaoSocialContaining(String razaoSocial);
	
	public Iterable<PessoaJuridica> findByInscricaoEstadualContaining(String inscricaoEstadual);

}
