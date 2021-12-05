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
import com.bugsbuster.projectCaptainTech.model.PessoaJuridica;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;
import com.bugsbuster.projectCaptainTech.repository.EnderecoRepository;
import com.bugsbuster.projectCaptainTech.repository.PessoaJuridicaRepository;

/* Classe que trata as requisições para clientes pessoa Jurídica*/

/* Define como uma aplicação e exclui classes não desejadas */
@SpringBootApplication (exclude = {ContextInstanceDataAutoConfiguration.class,
		ContextStackAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class})
@Service // Define como uma classe de serviço
public class PessoaJuridicaServiceImpl {
	// Ligação com o Cliente de SNS da amazon
	@Autowired
	AmazonSNSClient snsClient;
	
	// O ARN do tópico de SNS em que os clientes subscrevem
	String TOPIC_ARN = "arn:aws:sns:us-east-2:965934840569:BugBuster_Indisponivel";
	
	// Licação com o repositório de pessoa Jurídica
	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;
	
	/* Ligação com o repositório de conta. Usado para criar contas dinâmicamente
	**	junto com os clientes. */
	@Autowired
	ContaRepository contaRepository;
	
	/* Ligação para o repositório de endereço. */
	@Autowired
	EnderecoRepository enderecoRepository;

	public Iterable<PessoaJuridica> obterTodos() {                     // Retorna todos os clientes pj
		System.out.println("Consultando todas as pessoas juridicas "); //
		return this.pessoaJuridicaRepository.findAll();                // Repositório chama a busca no BD
	}

	public PessoaJuridica obterPorId(int id) {                            // Retorna pessoa física por id
		System.out.println("Consultando pessoa juridica por ID  ... "+id);
		return this.pessoaJuridicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	public PessoaJuridica criarPessoaJuridica(PessoaJuridica pj) {              // Cria um nobo cliente pj
		System.out.println("Criando uma nova pessoa juridica");                 //
		PessoaJuridica novaPj = this.pessoaJuridicaRepository.save(pj);         // Chama a busca no repositório
		boolean resp = true;                                                    // Variavel para controlar loop
		addSubscription(pj.getEmail());                                         // Envia email de subscrição para o cliente
		while (resp) {                                                          // Loop para verificar o número da conta
			int randomNumber = (int) (Math.random() * (99999 - 10000) + 10000); // Gera uma numero random de 5 digitos
			if (!this.contaRepository.existsByNumero(randomNumber)) {           // Verifica se n existe
				Conta conta = new Conta(randomNumber, pj, 0.0);                 // Cria uma conta nova com os parâmetros
				this.contaRepository.save(conta);                               // Salva a nova conta no repositório
				resp = false;                                                   // Troca a variável para quebrar o loop
			}                                                                   //
		}                                                                       //
		return novaPj;                                                          // Retonorna o cliente cadastrado
	}

	public PessoaJuridica atualizar(PessoaJuridica pj) {       // Atualiza os dados de um pj
		System.out.println("Atualizando cadastro da pessoa juridica ["+pj.getId_cliente()+"] - "+pj.getRazaoSocial());
		PessoaJuridica newPJ = obterPorId(pj.getId_cliente()); // Retorna os dados no DB desse cliente

		if (pj.getNomeFantasia() != null) {                    // Se inserido um nome fantasia para alterar
			newPJ.setNomeFantasia(pj.getNomeFantasia());       // Altera o nome fantasia no cadastro
		}

		if (pj.getTelefone() != null) {                        // Se inserido um telefone para alterar
			newPJ.setTelefone(pj.getTelefone());               // Altera o telefone no cadastro
		}

		if (pj.getEmail() != null) {                           // Se inserido um email para alterar
			newPJ.setEmail(pj.getEmail());                     // Altera o email no cadastro
		}

		return this.pessoaJuridicaRepository.save(newPJ);      // Salva as alterações no BD
	}

	/* A nossa solução para o desafio não inclui uma real deleção de Clientes.
	 * Realizamos uma deleção lógica, na qual uma variável do cliente na qual
	 * define se é um cliente ativo ou não.*/
	public PessoaJuridica desativar(int id) {                       // Desativa um cliente de id recebido
		System.out.println("Desativando pessoa juridica >>  "+id);  //
		PessoaJuridica newPJ = obterPorId(id);                      // Obtem os dados do cliente do BD
		newPJ.setAtivo(false);                                      // Define o status de ativo como falso
		return this.pessoaJuridicaRepository.save(newPJ);           // Salva as alterações no BD
	}
	
	public Iterable<PessoaJuridica> obterPorNomeFantasia(String nome){           // Obtem cliente pj em que o nome fantasia
		System.out.println("Consultando pessoa juridica por nome  ... "+nome);   // contem a string passada
		return this.pessoaJuridicaRepository.findByNomeFantasiaContaining(nome); // Faz a consulta no DB
	}
	
	public Iterable<PessoaJuridica> obterPorCnpj(String cnpj){                   // Obter cliente pj por cnpj
		System.out.println("Consultando pessoa juridica por cnpj  ... "+cnpj);   //
		return this.pessoaJuridicaRepository.findByCnpj(cnpj);                   // Faz a consulta no DB
	}
	
	public Iterable<PessoaJuridica> obterPorTelefone(String telefone){           // Obtem cliente pj por Telefone
		System.out.println("Consultando pessoa juridica por telefone  ... "+telefone);
		return this.pessoaJuridicaRepository.findByTelefone(telefone);           // Faz a consulta no DB
	}
	
	public Iterable<PessoaJuridica> obterPorRazaoSocial(String razao){           // Obtem cliente onde a razão social
		System.out.println("Consultando pessoa juridica por Razão Social  ... "+razao); // contem a string passada
		return this.pessoaJuridicaRepository.findByRazaoSocialContaining(razao); // Faz a consulta no DB
	}
	
	public Iterable<PessoaJuridica> obterPorInscricaoEstadual(String inscricao){ // Obtem cliente onde inscrição estudal
		System.out.println("Consultando pessoa juridica por Inscrição estadual  ... "+inscricao); // contem a string passada
		return this.pessoaJuridicaRepository.findByInscricaoEstadualContaining(inscricao); // consulta o DB
	}

	public String addSubscription(String email){                   // Realiza a subscrição de emails de clientes
		SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, // Cria a requisição passando o tópico SNS,
				"email", email);                                   // O tipo de servico, que é email, e o email de cadastro
		snsClient.subscribe(request);                              // Subscreve a solicitação
		return "Pedido de Subscrição enviado para o email:"+
				email+". Verifique sua caixa de mensagem";         // Retorna uma mensagem de envio de solicitação
	}
	
	public String publishMessageToTpoic(String mensagem) {          // Envia uma mensagem para os emails subscritos
		PublishRequest publishRequest=new PublishRequest(TOPIC_ARN, // Cria uma requisição de publicação com o tópico,
				mensagem, "BlueBank: Notificação");                 // a mensagem passada e o título do email
		snsClient.publish(publishRequest);                          // Publica a mensagem
		return "Notificação Enviada!";                              // Retorna uma mensagem de sucesso
	}	
	
}