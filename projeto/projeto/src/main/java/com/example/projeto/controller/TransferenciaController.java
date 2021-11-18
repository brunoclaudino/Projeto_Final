package com.example.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.model.Conta;
import com.example.projeto.model.Transferencia;
import com.example.projeto.service.ContaService;
import com.example.projeto.service.TransferenciaService;

@RestController
@RequestMapping(path = "/transferencias")
public class TransferenciaController {
	@Autowired
	private TransferenciaService tranService;
	@Autowired
	private ContaService contaServ;
	
	@GetMapping
	public Iterable<Transferencia> obterTodos(){
		return this.tranService.obterTodos();
	}
	
	@GetMapping(value = "/destino/{destino}")
	public Iterable<Transferencia> obterPorDestino(@PathVariable int destino){
		return this.tranService.obterPorDestino(destino);
	}
	
	@GetMapping(value = "/origem/{origem}")
	public Iterable<Transferencia> obterPorOrigem(@PathVariable int origem){
		return this.tranService.obterPorOrigem(origem);
	}
	
	@GetMapping(value = "/historico/{conta}")
	public Iterable<Transferencia> obterHistConta(@PathVariable int conta){
		return this.tranService.obterPorConta(conta);
	}
	
	@PostMapping(value = "/{or}/{dest}/{value}/{date}")
	public Transferencia novaTransferencia(@PathVariable int or,
			@PathVariable int dest,
			@PathVariable double value,
			@PathVariable String date){
		Transferencia nova = new Transferencia();
		Conta destino = this.contaServ.findById(dest);
		Conta origem = this.contaServ.findById(or);

		nova.setContaDestino(destino);
		nova.setContaOrigem(origem);
		nova.setValor(value);
		nova.setData(date);
		return this.tranService.criarTransferencia(nova);
		
	}
}
