package com.bugsbuster.projectCaptainTech.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	ContaRepository contaRepository;
	
	public Iterable<Conta> obterTodos(){
		return this.contaRepository.findAll();
	}
	
	public Conta obterPorId(Integer id){
		return this.contaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}
	
	public Conta criarConta(Conta conta) {
		return this.contaRepository.save(conta);
	}
}
