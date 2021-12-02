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
@RequestMapping(path = "/transferencias")                                        // Endpoint para serviços de transferência
public class TransferenciaController {
	@Autowired
	private TransferenciaService tranService;                                    // Instância da camada de serviço de transferência
	
	@GetMapping                                                                  // Requisição GET
	public Iterable<Transferencia> obterTodos(){                                 // Obtem todas as transferências do BD
		return this.tranService.obterTodos();
	}
	
	@GetMapping(value = "/destino/{destino}")                                    // Requisição GET com parâmetro por URL
	public Iterable<Transferencia> obterPorDestino(@PathVariable int destino){   // Obtem as transferências por conta de
		return this.tranService.obterPorDestino(destino);                        // destino passada
	}
	
	@GetMapping(value = "/origem/{origem}")                                      // Requisição GET com parâmetro da URL
	public Iterable<Transferencia> obterPorOrigem(@PathVariable int origem){     // Obtem as transferências por conta de origem
		return this.tranService.obterPorOrigem(origem);                          
	}
	
	@GetMapping(value = "/historico/{conta}")                                    // Requisição GET de histório de conta
	public Iterable<Transferencia> obterHistConta(@PathVariable int conta){      // Obtem as transferências de uma conta passada
		return this.tranService.obterPorConta(conta);                            // (tanto dinheiro que saiu quanto o que entrou)
	}
	
	/**
	 * 
	 * @param conta numero da conta
	 * @return RETORNA AS ULTIMAS TRANSFERENCIAS ORDENADAS
	 */
	@GetMapping(value = "/ordenadas/{conta}")                                    // Requisição GET de Histórico ordenada
	public Iterable<Transferencia> findHistOrdenado(@PathVariable int conta){    // Obtem o Histórico da conta porem ordenado
		return this.tranService.obterHistOrdenado(conta);
	}
	
	@PostMapping()
	public Transferencia novaTransferencia(@RequestBody Transferencia tran){     // Requisição POST para criar nova transferencia
		return this.tranService.criarTransferencia(tran);
	}
}