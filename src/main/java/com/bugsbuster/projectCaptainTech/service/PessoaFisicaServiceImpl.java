package com.bugsbuster.projectCaptainTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.PessoaFisica;
import com.bugsbuster.projectCaptainTech.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaServiceImpl implements InterfacePessoaFisica {
	
	@Autowired
	PessoaFisicaRepository pessoaFisicaRepository;
	
	public Iterable<PessoaFisica> obterTodos() {
		return this.pessoaFisicaRepository.findAll();
	}
	
	public PessoaFisica obterPorId(int id){
		return this.pessoaFisicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}
	
	public PessoaFisica criarPessoaFisica(PessoaFisica pf) {
		return this.pessoaFisicaRepository.save(pf);
	}
	
//	public PessoaFisica desativarCliente(int id) {
//		PessoaFisica pf = (PessoaFisica) pessoaFisicaRepository.findById(id);
//		return null;
//	}
}
