package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugsbuster.projectCaptainTech.model.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {
	
}
