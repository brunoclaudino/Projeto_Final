package com.bugsbuster.projectCaptainTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.service.ContaService;

@RestController
@RequestMapping(path = "/contas")                                         // Endpoint geral de contas.
public class ContaController {
	@Autowired
	ContaService contaService;                                            // Cria uma instância do serviço de contas.
	
	@GetMapping                                                           // Mapeia como uma requisição do tipo GET.
	public Iterable<Conta> obterTodos(){                                  // Obtem todas as contas.
		return this.contaService.obterTodos();                            // Acessa o serviço que trata a busca.
	}
	
	@GetMapping(value = "/{numero}")                                      // Requisição do tipo GET que recebe parâmetro via URL.
	public Iterable<Conta> obterPorNumero(@PathVariable int numero){      // Retorna uma conta pelo número dela. Id_conta != numero.
		return this.contaService.obterPorNumero(numero);
	}
}
