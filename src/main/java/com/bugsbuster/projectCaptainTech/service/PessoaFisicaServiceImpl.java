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

/* Classe que trata as requisições para clientes pessoa física*/

// Define como uma aplicação e exclui classes não desejadas
@SpringBootApplication (exclude = {ContextInstanceDataAutoConfiguration.class,
		ContextStackAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class})
@Service // Define como uma classe de serviço
public class PessoaFisicaServiceImpl {
	// Ligação com o Cliente de SNS da amazon
	@Autowired
	AmazonSNSClient snsClient;
	
	// O ARN do tópico de SNS em que os clientes subscrevem
	String TOPIC_ARN = "arn:aws:sns:us-east-2:965934840569:BugBuster_Indisponivel";
	
	// Licação com o repositório de pessoa física
	@Autowired
	PessoaFisicaRepository pessoaFisicaRepository;
	
	/* Ligação com o repositório de conta. Usado para criar contas dinâmicamente
	**	junto com os clientes*/
	@Autowired
	ContaRepository contaRepository;

	public Iterable<PessoaFisica> obterTodos() {                    // Retorna todos os clientes pf
		System.out.println("Pesquisando todas as pessoas físicas"); // 
		return this.pessoaFisicaRepository.findAll();               // Repositório chama a busca no BD
	}

	public PessoaFisica obterPorId(int id) {                                            // Retorna pessoa física por id
		System.out.println("Pesquisando pessoa fisica por ID ... "+id);                 // 
		return this.pessoaFisicaRepository.findById(id).orElseThrow( 
				() -> new EntityNotFoundException("Id da pessoa não encontrado > "+id));// Chama a busca no repositório e lança
	}                                                                                   // uma exception no erro

	public PessoaFisica criarPessoaFisica(PessoaFisica pf) {                     // Cria um nobo cliente pf
		System.out.println("Criando uma nova  pessoa fisica ");                  //
		PessoaFisica novaPf = this.pessoaFisicaRepository.save(pf);              // Chama a busca no repositório
		boolean resp = true;                                                     // Variavel para controlar loop
		addSubscription(pf.getEmail());                                          // Envia email de subscrição para o cliente
		while (resp) {                                                           // Loop para verificar o número da conta
			int randomNumber = (int) (Math.random() * (99999 - 10000) + 10000);  // Gera uma numero random de 5 digitos
			if (!this.contaRepository.existsByNumero(randomNumber)) {            // Verifica se n existe
				Conta conta = new Conta(randomNumber, pf, 0.0);                  // Cria uma conta nova com os parâmetros
				this.contaRepository.save(conta);                                // Salva a nova conta no repositório
				resp = false;                                                    // Troca a variável para quebrar o loop
			}                                                                    //
		}                                                                        //
		return novaPf;                                                           // Retonorna o cliente cadastrado
	}
	
	public PessoaFisica atualizar(PessoaFisica pf) {               // Atualiza os dados de um pf
		System.out.println("Atualizando cadastro da pessoa fisica ["+pf.getId_cliente()+"] - "+pf.getNome());
		PessoaFisica newPF = this.obterPorId(pf.getId_cliente());  // Retorna os dados no DB desse cliente

		if (pf.getNome() != null) {                                // Se inserido um nome para alterar
			newPF.setNome(pf.getNome());                           // Altera o nome no cadastro
		}

		if (pf.getTelefone() != null) {                            // Se inserido um telefone para alterar
			newPF.setTelefone(pf.getTelefone());                   // Altera o telefone no cadastro
		}

		if (pf.getEmail() != null) {                               // Se inserido um email para alterar
			newPF.setEmail(pf.getEmail());                         // Altera o email no cadastro
		}
		
		if (pf.getOcupacao() != null) {                            // Se inserido uma ocupação para alterar
			newPF.setOcupacao(pf.getOcupacao());                   // Altera a ocupação no cadastro
		}

		return this.pessoaFisicaRepository.save(newPF);            // Salva as alterações no BD
	}

	/* A nossa solução para o desafio não inclui uma real deleção de Clientes.
	 * Realizamos uma deleção lógica, na qual uma variável do cliente na qual
	 * define se é um cliente ativo ou não.*/
	public PessoaFisica desativar(int id) {                  // Desativa um cliente de id recebido
		System.out.println("Desatovar pessoa fisica > "+id); //
		PessoaFisica newPF = obterPorId(id);                 // Obtem os dados do cliente do BD
		newPF.setAtivo(false);                               // Define o status de ativo como falso
		return this.pessoaFisicaRepository.save(newPF);      // Salva as alterações no BD
	}
	
	public Iterable<PessoaFisica> obterPorNome(String nome){                 // Obtem cliente pf em que o nome
		System.out.println("Consultando pessoa fisica por nome  ... "+nome); // contenha a string passada
		return this.pessoaFisicaRepository.findByNomeContaining(nome);       // Faz a busca no BD
	}
	
	public Iterable<PessoaFisica> obterPorCpf(String cpf){                 // Obtem cliente pf por cpf
		System.out.println("Consultando pessoa fisica por cpf  ... "+cpf); //
		return this.pessoaFisicaRepository.findByCpf(cpf);                 // Faz a busca no BD
	}
	
	public Iterable<PessoaFisica> obterPorTelefone(String telefone){       // Obtem clientes pelo telefone
		System.out.println("Consultando pessoa fisica por telefone  ... "+telefone);
		return this.pessoaFisicaRepository.findByTelefone(telefone);       // Faz a busca no BD
	}
	
	public Iterable<PessoaFisica> ObterPorDataNascimento(String data){     // Obtem clientes por data de nascimento
		System.out.println("Consultando pessoa fisica por Data de Nascimento  ... "+data);
		return this.pessoaFisicaRepository.findByDataNascimentoOrderByDataNascimento(data);
	}
	
	public Iterable<PessoaFisica> ObterPorOcupacao(String ocupacao){                 // Obtem clientes por ocupação
		System.out.println("Consultando pessoa fisica por ocupacao  ... "+ocupacao); //
		return this.pessoaFisicaRepository.findByOcupacaoOrderByOcupacao(ocupacao);  // Faz a busca no BD
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