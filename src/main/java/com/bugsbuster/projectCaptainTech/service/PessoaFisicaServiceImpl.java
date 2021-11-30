package com.bugsbuster.projectCaptainTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.model.PessoaFisica;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;
import com.bugsbuster.projectCaptainTech.repository.PessoaFisicaRepository;
import com.bugsbuster.projectCaptainTech.service.exceptions.EntityNotFoundException;



@SpringBootApplication (exclude = {ContextInstanceDataAutoConfiguration.class,
		ContextStackAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class})
@Service
public class PessoaFisicaServiceImpl {
	@Autowired
	AmazonSNSClient snsClient;
	
	String TOPIC_ARN = "arn:aws:sns:us-east-2:965934840569:BugBuster_Indisponivel";
	
	@Autowired
	PessoaFisicaRepository pessoaFisicaRepository;
	
	@Autowired
	ContaRepository contaRepository;

	public Iterable<PessoaFisica> obterTodos() {
		System.out.println("Pesquisando todas as pessoas físicas");
		return this.pessoaFisicaRepository.findAll();
	}

	public PessoaFisica obterPorId(int id) {
		System.out.println("Pesquisando pessoa fisica por ID ... "+id);
		return this.pessoaFisicaRepository.findById(id).orElseThrow( 
				() -> new EntityNotFoundException("Id da pessoa não encontrado > "+id));
	}

	public PessoaFisica criarPessoaFisica(PessoaFisica pf) {
		System.out.println("Criando uma nova  pessoa fisica ");
		PessoaFisica novaPf = this.pessoaFisicaRepository.save(pf);
		boolean resp = true;
		addSubscription(pf.getEmail());
		while (resp) {
			int randomNumber = (int) (Math.random() * (99999 - 10000) + 10000);
			if (!this.contaRepository.existsByNumero(randomNumber)) {
				Conta conta = new Conta(randomNumber, pf, 0.0);
				this.contaRepository.save(conta);
				resp = false;
			}
		}
		return novaPf;
	}
	
	public PessoaFisica atualizar(PessoaFisica pf) {
		System.out.println("Atualizando cadastro da pessoa fisica ["+pf.getId_cliente()+"] - "+pf.getNome());
		PessoaFisica newPF = this.obterPorId(pf.getId_cliente());
		

		if (pf.getNome() != null) {
			newPF.setNome(pf.getNome());
		}

		if (pf.getTelefone() != null) {
			newPF.setTelefone(pf.getTelefone());
		}

		if (pf.getEmail() != null) {
			newPF.setEmail(pf.getEmail());
		}
		
		if (pf.getOcupacao() != null) {
			newPF.setOcupacao(pf.getOcupacao());
		}

		return this.pessoaFisicaRepository.save(newPF);
	}

	public PessoaFisica desativar(int id) {
		System.out.println("Desatovar pessoa fisica > "+id);
		PessoaFisica newPF = obterPorId(id);
		newPF.setAtivo(false);
		return this.pessoaFisicaRepository.save(newPF);
	}
	
	public Iterable<PessoaFisica> obterPorNome(String nome){
		System.out.println("Consultando pessoa fisica por nome  ... "+nome);
		return this.pessoaFisicaRepository.findByNomeContaining(nome);
	}
	
	public Iterable<PessoaFisica> obterPorCpf(String cpf){
		System.out.println("Consultando pessoa fisica por cpf  ... "+cpf);
		return this.pessoaFisicaRepository.findByCpf(cpf);
	}
	
	public Iterable<PessoaFisica> obterPorTelefone(String telefone){
		System.out.println("Consultando pessoa fisica por telefone  ... "+telefone);
		return this.pessoaFisicaRepository.findByTelefone(telefone);
	}
	
	public Iterable<PessoaFisica> ObterPorDataNascimento(String data){
		System.out.println("Consultando pessoa fisica por Data de Nascimento  ... "+data);
		return this.pessoaFisicaRepository.findByDataNascimentoOrderByDataNascimento(data);
	}
	
	public Iterable<PessoaFisica> ObterPorOcupacao(String ocupacao){
		System.out.println("Consultando pessoa fisica por ocupacao  ... "+ocupacao);
		return this.pessoaFisicaRepository.findByOcupacaoOrderByOcupacao(ocupacao);
	}
	
	public String addSubscription(String email){
		SubscribeRequest request = new SubscribeRequest(TOPIC_ARN,
				"email", email);
		snsClient.subscribe(request);
		return "Pedido de Subscrição enviado para o email:"+
				email+". Verifique sua caixa de mensagem";
	}
	
	public String publishMessageToTpoic(String mensagem) {
		PublishRequest publishRequest=new PublishRequest(TOPIC_ARN,
				mensagem, "BlueBank: Notificação");
		snsClient.publish(publishRequest);
		return "Notificação Enviada!";
	}
}