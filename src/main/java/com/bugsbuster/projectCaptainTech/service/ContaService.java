package com.bugsbuster.projectCaptainTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;

/* Define os comportamentos de conta para as requisições.*/
@Service // Define com uma classe de serviço
public class ContaService {
	@Autowired
	ContaRepository contaRepository;                             // Conexão com o repositório de contas
	
	public Iterable<Conta> obterTodos(){                         // Retorna todas as contas cadastradas
		System.out.println("Consultando todas as contas");
		return this.contaRepository.findAll();                   // Chama a busca no repositório
	}
	
	public Conta obterPorId(Integer id){                         // Retorna a conta pelo id dela
		System.out.println("Pesquisando conta por ID ... "+id);
		return this.contaRepository.findById(id)                 // Chama a busca no repositório
				.orElseThrow(
						() -> new IllegalArgumentException());   // Caso não exista, lança uma exception
	}
	
	public Conta criarConta(Conta conta) {                       // Cria uma conta nova
		System.out.println("Cadastrando uma nova conta ");
		return this.contaRepository.save(conta);                 // O repositório salva do BD
	}
	
	public Iterable<Conta> obterPorNumero(int numero){           // Obtem conta pelo número. Numero != id
		System.out.println("Pesquisando conta pelo número ... "+numero);
		return this.contaRepository.findByNumero(numero);        // Chama a busca no repositório
	}
}