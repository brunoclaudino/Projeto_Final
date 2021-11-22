package com.bugsbuster.projectCaptainTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.service.ContaService;

@RestController
@RequestMapping(path = "/contas")
public class ContaController {
	@Autowired
	ContaService contaService;
	
	@GetMapping
	public Iterable<Conta> obterTodos(){
		return this.contaService.obterTodos();
	}

}
