package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugsbuster.projectCaptainTech.model.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer>{
	public Iterable<PessoaFisica> findByNomeContaining(String nome);
	
	public Iterable<PessoaFisica> findByCpf(String cpf);
	
	public Iterable<PessoaFisica> findByTelefone(String telefone);
}
