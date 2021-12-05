package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugsbuster.projectCaptainTech.model.Endereco;

/* Repositório de Endereço para comunicação com o banco de dados. */
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
