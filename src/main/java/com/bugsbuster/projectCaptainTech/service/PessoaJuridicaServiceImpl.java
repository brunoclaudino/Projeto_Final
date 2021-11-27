package com.bugsbuster.projectCaptainTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.bugsbuster.projectCaptainTech.model.PessoaJuridica;
=======
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.model.PessoaJuridica;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;
import com.bugsbuster.projectCaptainTech.repository.EnderecoRepository;
>>>>>>> c42bac9213b577e656297d032572bcc6423a7875
import com.bugsbuster.projectCaptainTech.repository.PessoaJuridicaRepository;

@SpringBootApplication (exclude = {ContextInstanceDataAutoConfiguration.class,
		ContextStackAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class})
@Service
public class PessoaJuridicaServiceImpl {
	@Autowired
	AmazonSNSClient snsClient;
	
	String TOPIC_ARN = "arn:aws:sns:us-east-2:965934840569:BugBuster_Indisponivel";
	
	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;
<<<<<<< HEAD
	
	public Iterable<PessoaJuridica> obterTodos(){
=======
	@Autowired
	ContaRepository contaRepository;
	@Autowired
	EnderecoRepository enderecoRepository;

	public Iterable<PessoaJuridica> obterTodos() {
>>>>>>> c42bac9213b577e656297d032572bcc6423a7875
		return this.pessoaJuridicaRepository.findAll();
	}

	public PessoaJuridica obterPorId(int id) {
		return this.pessoaJuridicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	public PessoaJuridica criarPessoaJuridica(PessoaJuridica pj) {
<<<<<<< HEAD
		return this.pessoaJuridicaRepository.save(pj);
=======
		PessoaJuridica novaPj = this.pessoaJuridicaRepository.save(pj);
		boolean resp = true;
		addSubscription(pj.getEmail());
		while (resp) {
			int randomNumber = (int) (Math.random() * (99999 - 10000) + 10000);
			if (!this.contaRepository.existsByNumero(randomNumber)) {
				Conta conta = new Conta(randomNumber, pj, 0.0);
				this.contaRepository.save(conta);
				resp = false;
			}
		}
		return novaPj;
>>>>>>> c42bac9213b577e656297d032572bcc6423a7875
	}

	public PessoaJuridica atualizar(PessoaJuridica pj) {
		PessoaJuridica newPJ = obterPorId(pj.getId_cliente());

		if (pj.getNomeFantasia() != null) {
			newPJ.setNomeFantasia(pj.getNomeFantasia());
		}

		if (pj.getTelefone() != null) {
			newPJ.setTelefone(pj.getTelefone());
		}

		if (pj.getEmail() != null) {
			newPJ.setEmail(pj.getEmail());
		}

		return this.pessoaJuridicaRepository.save(newPJ);
	}

	public PessoaJuridica desativar(int id) {
		PessoaJuridica newPJ = obterPorId(id);
		newPJ.setAtivo(false);
		return this.pessoaJuridicaRepository.save(newPJ);
	}
	
<<<<<<< HEAD
//	public void deletar(int id) {
//		pessoaJuridicaRepository.deleteById(id);
//	}
=======
	public Iterable<PessoaJuridica> obterPorNomeFantasia(String nome){
		return this.pessoaJuridicaRepository.findByNomeFantasiaContaining(nome);
	}
	
	public Iterable<PessoaJuridica> obterPorCnpj(String cnpj){
		return this.pessoaJuridicaRepository.findByCnpj(cnpj);
	}
	
	public Iterable<PessoaJuridica> obterPorTelefone(String telefone){
		return this.pessoaJuridicaRepository.findByTelefone(telefone);
	}
	
	public Iterable<PessoaJuridica> obterPorRazaoSocial(String razao){
		return this.pessoaJuridicaRepository.findByRazaoSocialContaining(razao);
	}
	
	public Iterable<PessoaJuridica> obterPorInscricaoEstadual(String inscricao){
		return this.pessoaJuridicaRepository.findByInscricaoEstadualContaining(inscricao);
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
	
>>>>>>> c42bac9213b577e656297d032572bcc6423a7875
}
