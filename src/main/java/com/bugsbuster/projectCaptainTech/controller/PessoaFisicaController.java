package com.bugsbuster.projectCaptainTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugsbuster.projectCaptainTech.model.PessoaFisica;
import com.bugsbuster.projectCaptainTech.repository.EnderecoRepository;
import com.bugsbuster.projectCaptainTech.service.PessoaFisicaServiceImpl;

@RestController
@RequestMapping(path = "/clientePF")
public class PessoaFisicaController {
	
	@Autowired
	public PessoaFisicaServiceImpl pessoaFisicaService;
	
	@Autowired
	public EnderecoRepository enderecoRepository;
	
	@GetMapping
	public Iterable<PessoaFisica> obterTodos() {
		return this.pessoaFisicaService.obterTodos();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaFisica> obterPorId(@PathVariable int id){
		PessoaFisica obj = this.pessoaFisicaService.obterPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public PessoaFisica criarPessoaFisica(@RequestBody PessoaFisica pf) {
//		Endereco end = enderecoRepository.findById(pf.getEndereco().getId_endereco()).orElseThrow();
//		pf.setEndereco(end);
		return this.pessoaFisicaService.criarPessoaFisica(pf);
		
	}
}
