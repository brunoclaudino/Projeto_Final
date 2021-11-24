package com.bugsbuster.projectCaptainTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.Endereco;
import com.bugsbuster.projectCaptainTech.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoReposity;
	
	public Iterable<Endereco> obterTodos(){
		return this.enderecoReposity.findAll();
	}
	
	public Endereco obterPorId(Integer id) {
		return this.enderecoReposity.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de Endereço Inválido"));
	}
	
	public Endereco criarEndereco(Endereco end) {
		return this.enderecoReposity.save(end);
	}

}
