package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.bugsbuster.projectCaptainTech.model.Transferencia;

public interface TransferenciaRepository extends CrudRepository<Transferencia, Integer>{
	
	@Query("from Transferencia where conta_destino=:destino")
    public Iterable<Transferencia> findByDestino(int destino);
	
	@Query("from Transferencia where conta_origem=:origem")
    public Iterable<Transferencia> findByOrigem(int origem);
	
	@Query("from Transferencia where conta_origem=:conta or conta_destino=:conta")
    public Iterable<Transferencia> findHistConta(int conta);
	
	@Query(value = "SELECT * from Transferencia where (conta_origem=:conta or conta_destino=:conta) order by data desc LIMIT 3", nativeQuery = true)
	public Iterable<Transferencia> findHistOrdenado(int conta);

}
