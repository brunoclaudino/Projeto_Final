package com.bugsbuster.projectCaptainTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.PessoaJuridica;
import com.bugsbuster.projectCaptainTech.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaServiceImpl {
	
	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;
	
	public Iterable<PessoaJuridica> obterTodos(){
		return this.pessoaJuridicaRepository.findAll();
	}
	
	public PessoaJuridica obterPorId(int id) {
		return this.pessoaJuridicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}
	
	public PessoaJuridica criarPessoaJuridica(PessoaJuridica pj) {
		return this.pessoaJuridicaRepository.save(pj);
	}
	
	public PessoaJuridica atualizar(PessoaJuridica pj) {
		PessoaJuridica newPJ = obterPorId(pj.getId_cliente());
		
		if (pj.getNomeFantasia() != null) {
			newPJ.setNomeFantasia(pj.getNomeFantasia());
		}
		
		if (pj.getTelefone() != null) {
			newPJ.setTelefone(pj.getTelefone());
		}
		
		if (pj.getEmail() != null) {
			newPJ.setEmail(pj.getEmail());
		}
				
		return this.pessoaJuridicaRepository.save(newPJ);
	}
	
	public PessoaJuridica desativar(int id) {
		PessoaJuridica newPJ = obterPorId(id);
		newPJ.setAtivo(false);
		return this.pessoaJuridicaRepository.save(newPJ);
	}
	
//	public void deletar(int id) {
//		pessoaJuridicaRepository.deleteById(id);
//	}
}
