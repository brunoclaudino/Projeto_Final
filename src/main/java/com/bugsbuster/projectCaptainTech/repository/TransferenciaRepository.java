package com.bugsbuster.projectCaptainTech.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.bugsbuster.projectCaptainTech.model.Transferencia;

/* Repositório de transferência para comunicação com o banco de dados. */
public interface TransferenciaRepository extends CrudRepository<Transferencia, Integer>{
	/*Abaixo segue as Querys para pesquisa personalizadas*/
	
	/* Retorna transferências de entrada de uma conta. */
	@Query("from Transferencia where conta_destino=:destino")
    public Iterable<Transferencia> findByDestino(int destino);
	
	/* Retorna transferências de saída de uma conta. */
	@Query("from Transferencia where conta_origem=:origem")
    public Iterable<Transferencia> findByOrigem(int origem);
	
	/* Retorna todas as transferências de uma conta. */
	@Query("from Transferencia where conta_origem=:conta or conta_destino=:conta")
    public Iterable<Transferencia> findHistConta(int conta);
	
	/* Retorna as ultimas 3 transferências de uma conta ordenada por data. */
	@Query(value = "SELECT * from Transferencia where (conta_origem=:conta or conta_destino=:conta) order by data desc LIMIT 3", nativeQuery = true)
	public Iterable<Transferencia> findHistOrdenado(int conta);

}
