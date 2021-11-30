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
	
	@GetMapping(value="/nf/{nf}")
	public Iterable<PessoaJuridica> obterPorNomeFantasia(@PathVariable String nf){
		return this.pessoaJuridicaService.obterPorNomeFantasia(nf);
	}
	
	@GetMapping(value="/cnpj/{cnpj}")
	public Iterable<PessoaJuridica> obterPorCnpj(@PathVariable String cnpj){
		return this.pessoaJuridicaService.obterPorCnpj(cnpj);
	}
	
	@GetMapping(value="/telefone/{telefone}")
	public Iterable<PessoaJuridica> obterPorTelefone(@PathVariable String telefone){
		return this.pessoaJuridicaService.obterPorTelefone(telefone);
	}
	
	@GetMapping(value="/razao/{razao}")
	public Iterable<PessoaJuridica> obterPorRazaoSocial(@PathVariable String razao) {
		return this.pessoaJuridicaService.obterPorRazaoSocial(razao);
	}
	
	@GetMapping(value="/inscricao/{inscricao}")
	public Iterable<PessoaJuridica> obterPorInscricaoEstadual(@PathVariable String inscricao) {
		return this.pessoaJuridicaService.obterPorInscricaoEstadual(inscricao);
	}
	@PostMapping(value="/mensagem")
	public String enviarMensagem(@RequestBody String mensagem) {
		return this.pessoaJuridicaService.publishMessageToTpoic(mensagem);
	}
	
	@PutMapping(path= "/atualizar")
	public PessoaJuridica atualizarPj(@RequestBody PessoaJuridica pj) {
		return this.pessoaJuridicaService.atualizar(pj);
	}
}
