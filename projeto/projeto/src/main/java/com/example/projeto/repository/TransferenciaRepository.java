package com.example.projeto.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.projeto.model.Transferencia;


public interface TransferenciaRepository extends CrudRepository<Transferencia, Integer>{
	
	@Query("from Transferencia where conta_destino=:destino")
    public Iterable<Transferencia> findByDestino(int destino);
	
	@Query("from Transferencia where conta_origem=:origem")
    public Iterable<Transferencia> findByOrigem(int origem);
	
	@Query("from Transferencia where conta_origem=:conta or conta_destino=:conta")
    public Iterable<Transferencia> findHistConta(int conta);
}
