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
@RequestMapping(path = "/clientePJ")                                          // Endpoint para cliente pessoa jurídica
public class PessoaJuridicaController {
	
	@Autowired
	PessoaJuridicaServiceImpl pessoaJuridicaService;                          // Instância da camada de serviço de pessoa jurídica
	
	@GetMapping                                                               // Requisição GET
	public Iterable<PessoaJuridica> obterTodos(){                             // Busca todos os cliente pj
		return this.pessoaJuridicaService.obterTodos();                     
	}
	
	@PostMapping                                                              // Requisição POST
	public PessoaJuridica criarPj(@RequestBody PessoaJuridica pj) {           // Cria PJ recebendo um json com as informações
		return this.pessoaJuridicaService.criarPessoaJuridica(pj);            // Requisição na camada de serviço
	}
	
	@GetMapping(value = "/{id}")                                              // Requisição GET com parâmetro na URL
	public ResponseEntity<PessoaJuridica> obterPorId(@PathVariable int id){   // Obtem um cliente pj pelo id
		PessoaJuridica pj = this.pessoaJuridicaService.obterPorId(id);        // Retorna o resultado da requisição no serviço
		return ResponseEntity.ok().body(pj);                                  // Testa a resposta para tratamento de erro
	}
	
	@PutMapping(value =  "/desativar/{id}")                                   // Requisição PUT com parâmetro de URL
	public PessoaJuridica desativarPj(@PathVariable int id) {                 // Faz a deleção LÓGICA do cliente
		return this.pessoaJuridicaService.desativar(id);                      // desativa a conta com o id passado
	}
	
	@GetMapping(value="/nf/{nf}")                                                  // Requisição GET com parâmetro de URL
	public Iterable<PessoaJuridica> obterPorNomeFantasia(@PathVariable String nf){ // Obtem cliente a partir do nome fantasia (nf)
		return this.pessoaJuridicaService.obterPorNomeFantasia(nf);
	}
	
	@GetMapping(value="/cnpj/{cnpj}")                                         // Requisição GET com parâmetro de URL
	public Iterable<PessoaJuridica> obterPorCnpj(@PathVariable String cnpj){  // Obtem os cliente pj por cnpj dado
		return this.pessoaJuridicaService.obterPorCnpj(cnpj);
	}
	
	@GetMapping(value="/telefone/{telefone}")                                        // Requisição GET com parâmetro de URL
	public Iterable<PessoaJuridica> obterPorTelefone(@PathVariable String telefone){ // Obtem os clientes pj com um dado número de telefone
		return this.pessoaJuridicaService.obterPorTelefone(telefone);
	}
	
	@GetMapping(value="/razao/{razao}")                                               // Requisição GET com parâmetro de URL
	public Iterable<PessoaJuridica> obterPorRazaoSocial(@PathVariable String razao) { // Obtem o cliente pj dado uma razão social
		return this.pessoaJuridicaService.obterPorRazaoSocial(razao);
	}
	
	@GetMapping(value="/inscricao/{inscricao}")
	public Iterable<PessoaJuridica> obterPorInscricaoEstadual(@PathVariable String inscricao) { // Obtem o cliente pj dado uma inscrição estadual
		return this.pessoaJuridicaService.obterPorInscricaoEstadual(inscricao);
	}
	@PostMapping(value="/mensagem")                                           // Requisição GET com parâmetro de URL
	public String enviarMensagem(@RequestBody String mensagem) {              // Envia uma mensagem para o email
		return this.pessoaJuridicaService.publishMessageToTpoic(mensagem);    // dos clientes subscritos no tópico (AMAZON SNS)
	}
	
	@PutMapping(path= "/atualizar")                                           // Requisição PUT 
	public PessoaJuridica atualizarPj(@RequestBody PessoaJuridica pj) {       // atualiza os dados de uma pessoa jurídica
		return this.pessoaJuridicaService.atualizar(pj);
	}
}