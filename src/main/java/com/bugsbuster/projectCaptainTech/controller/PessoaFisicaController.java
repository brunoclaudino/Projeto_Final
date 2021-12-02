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

import com.bugsbuster.projectCaptainTech.model.PessoaFisica;
import com.bugsbuster.projectCaptainTech.repository.EnderecoRepository;
import com.bugsbuster.projectCaptainTech.service.PessoaFisicaServiceImpl;


@RestController
@RequestMapping(path = "/clientePF")                                           // Endpoint de acesso aos serviços de pessoa física.
public class PessoaFisicaController {
	
	@Autowired
	public PessoaFisicaServiceImpl pessoaFisicaService;                       // Instância da camada de serviço de pessoa física.
	
	@Autowired
	public EnderecoRepository enderecoRepository;                             // Instância do repositório de endereço.
	                                                                          // usado para criar novos endereços
	@GetMapping                                                               // Mapeia uma requisição do tipo GET.
	public Iterable<PessoaFisica> obterTodos() {                              // Retorna todos os clientes do tipo pessoa física.
		return this.pessoaFisicaService.obterTodos();
	}
	
	@GetMapping(value = "/{id}")                                              // Requisição do tipo GET com parâmetro na URL
	public ResponseEntity<PessoaFisica> obterPorId(@PathVariable int id){     // Retorna uma pessoa física que tiver o id passado
		PessoaFisica obj = this.pessoaFisicaService.obterPorId(id);           // Obtém o retorno da requisição por ID
		return ResponseEntity.ok().body(obj);                                 // Testa a resposta para tratamento
	}
	
	@PostMapping                                                              // Requisição do tipo POST
	public PessoaFisica criarPessoaFisica(@RequestBody PessoaFisica pf) {     // Cria uma cliente pessoa fisica novo e
		return this.pessoaFisicaService.criarPessoaFisica(pf);                // recebe as informações como json
		
	}
	
	@GetMapping(value = "/nome/{nome}")                                       // Requisição GET com endpoint e valor pego na URL
	public Iterable<PessoaFisica> obterPorNome(@PathVariable String nome){    // Busca um cliente na qual o nome contenha a string passada
		return this.pessoaFisicaService.obterPorNome(nome);                   // Faz a requisição no service
	}
	
	@GetMapping(value = "/cpf/{cpf}")                                         // Requisição GET com endpoint e valor pego na URL
	public Iterable<PessoaFisica> obterPorCpf(@PathVariable String cpf){      // Busca um cliente com o cpf dado
		return this.pessoaFisicaService.obterPorCpf(cpf);
	}
	
	@GetMapping(value = "/telefone/{telefone}")                                    // Requisição GET com endpoint e valor pego na URL
	public Iterable<PessoaFisica> obterPorTelefone(@PathVariable String telefone){ // Busca um cliente pelo telefone dado
		return this.pessoaFisicaService.obterPorTelefone(telefone);
	}
	
	@GetMapping(value = "/ocupacao/{ocupacao}")                                    // Requisição GET com endpoint e valor pego na URL
	public Iterable<PessoaFisica> ObterPorOcupacao(@PathVariable String ocupacao){ // Busca um cliente por ocupação
		return this.pessoaFisicaService.ObterPorOcupacao(ocupacao);                
	}
	
	@PostMapping(value="/mensagem")                                                // Endpoint de mensagem
	public String enviarMensagem(@RequestBody String mensagem) {                   // Envia uma mensagem passada em texto para os clientes
		return this.pessoaFisicaService.publishMessageToTpoic(mensagem);           // que estiverem subscritos no email (AMAZON SNS)
	}
	
	@PutMapping(path= "/atualizar")                                                // Endpoint para atualizar pessoa física
	public PessoaFisica atualizarPf(@RequestBody PessoaFisica pf) {                // Atualiza as informações do cliente pf
		return this.pessoaFisicaService.atualizar(pf);                             // com as informações que forem atualizáveis
	}
	
	@PutMapping(value = "/desativar/{id}")                                         // Endpoint GET para desativar cliente
	public PessoaFisica desativarPf (@PathVariable int id) {                       // Faz a deleção LÓGICA do cliente de
		return this.pessoaFisicaService.desativar(id);                             // id informado. Não exclui informações do BD
	}                                                                              // só muda informação como desativado
	
}
