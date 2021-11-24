package com.bugsbuster.projectCaptainTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.model.PessoaFisica;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;
import com.bugsbuster.projectCaptainTech.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaServiceImpl{
	
	@Autowired
	PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired
	ContaRepository contaRepository;
	
	public Iterable<PessoaFisica> obterTodos() {
		return this.pessoaFisicaRepository.findAll();
	}
	
	public PessoaFisica obterPorId(int id){
		return this.pessoaFisicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de Pessoa Física Inválido"));
	}
	
	public PessoaFisica criarPessoaFisica(PessoaFisica pf) {
		PessoaFisica novaPf = this.pessoaFisicaRepository.save(pf);
		Conta conta = new Conta(10, pf, 0.00);
		this.contaRepository.save(conta);
		return novaPf;
	}
	
	public PessoaFisica desativar(int id) {
		PessoaFisica newPF = obterPorId(id);
		newPF.setAtivo(false);
		return this.pessoaFisicaRepository.save(newPF);
	}
	
//	public PessoaFisica desativarCliente(int id) {
//		PessoaFisica pf = (PessoaFisica) pessoaFisicaRepository.findById(id);
//		return null;
//	}
}
