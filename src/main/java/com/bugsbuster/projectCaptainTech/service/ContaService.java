package com.bugsbuster.projectCaptainTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	ContaRepository contaRepository;
	
	public Iterable<Conta> obterTodos(){
		System.out.println("Consultando todas as contas");
		return this.contaRepository.findAll();
	}
	
	public Conta obterPorId(Integer id){
		System.out.println("Pesquisando conta por ID ... "+id);
		return this.contaRepository.findById(id)
				.orElseThrow(
						() -> new IllegalArgumentException());
	}
	
	public Conta criarConta(Conta conta) {
		System.out.println("Cadastrando uma nova conta ");
		return this.contaRepository.save(conta);
	}
	
	public Iterable<Conta> obterPorNumero(int numero){
		System.out.println("Pesquisando conta pelo número ... "+numero);
		return this.contaRepository.findByNumero(numero);
	}
}