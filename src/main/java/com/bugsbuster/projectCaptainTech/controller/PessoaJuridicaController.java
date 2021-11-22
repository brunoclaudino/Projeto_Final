package com.bugsbuster.projectCaptainTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugsbuster.projectCaptainTech.model.PessoaJuridica;
import com.bugsbuster.projectCaptainTech.service.PessoaJuridicaServiceImpl;

@RestController
@RequestMapping(path = "/clientePJ")
public class PessoaJuridicaController {
	
	@Autowired
	PessoaJuridicaServiceImpl pessoaJuridicaService;
	
	@GetMapping
	public Iterable<PessoaJuridica> obterTodos(){
		return this.pessoaJuridicaService.obterTodos();
	}
	
	@PostMapping
	public PessoaJuridica criarPj(@RequestBody PessoaJuridica pj) {
		return this.pessoaJuridicaService.criarPessoaJuridica(pj);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaJuridica> obterPorId(@PathVariable int id){
		PessoaJuridica pj = this.pessoaJuridicaService.obterPorId(id);
		return ResponseEntity.ok().body(pj);
	}
	
	@PutMapping(value =  "/{id}")
	public PessoaJuridica desativarPj(@PathVariable int id) {
		return this.pessoaJuridicaService.desativar(id);
	}
	
	@PutMapping
	public PessoaJuridica atualizarPj(@RequestBody PessoaJuridica pj) {
		return this.pessoaJuridicaService.atualizar(pj);
	}
}
