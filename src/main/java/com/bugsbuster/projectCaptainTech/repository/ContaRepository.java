package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugsbuster.projectCaptainTech.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
	
	public boolean existsByNumero(int account);
}
