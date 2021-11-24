package com.bugsbuster.projectCaptainTech.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugsbuster.projectCaptainTech.model.Transferencia;
import com.bugsbuster.projectCaptainTech.service.TransferenciaService;

@RestController
@RequestMapping(path = "/transferencias")
public class TransferenciaController {
	@Autowired
	private TransferenciaService tranService;
	
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
	
	@PostMapping()
	public Transferencia novaTransferencia(@RequestBody Transferencia tran){
		return this.tranService.criarTransferencia(tran);
	}
}
