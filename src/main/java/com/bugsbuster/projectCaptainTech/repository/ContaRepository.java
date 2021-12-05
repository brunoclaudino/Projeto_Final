package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugsbuster.projectCaptainTech.model.Conta;
/* Repositório de Conta para comunicação com o banco de dados*/
public interface ContaRepository extends JpaRepository<Conta, Integer> {
	/*Abaixo segue as Querys para pesquisa personalizadas*/
	
	/* Verifica que se um número de conta já existe*/
	public boolean existsByNumero(int account);
	
	/* Acha uma conta pelo número dela. Lembrando que numero != id. */
	public Iterable<Conta> findByNumero(int numero);
	
	/* Acha uma conta pela data de abertura*/
	public Iterable<Conta> findByDataAbertura(String data);
}