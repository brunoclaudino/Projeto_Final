package com.bugsbuster.projectCaptainTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaJuridica> obterPorId(@PathVariable int id){
		PessoaJuridica pj = this.pessoaJuridicaService.obterPorId(id);
		return ResponseEntity.ok().body(pj);
	}
	
	
}
