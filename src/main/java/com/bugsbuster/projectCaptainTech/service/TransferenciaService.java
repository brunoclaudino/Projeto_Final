package com.bugsbuster.projectCaptainTech.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.model.Transferencia;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;
import com.bugsbuster.projectCaptainTech.repository.TransferenciaRepository;

/* Classe que trata as requisições para transferência. */

// Define como uma classe de serviço
@Service
public class TransferenciaService{
	/* Ligação com o repositório de transferência. */
	@Autowired
	TransferenciaRepository tranRepo;

	/* O ARN do tópico de SNS em que os clientes subscrevem. */
	@Autowired
	ContaRepository contaRepo;

	public Iterable<Transferencia> obterTodos() {                              // Retorna todas as transferências
		System.out.println("Consultando todas as transferências realizadas ");
		return this.tranRepo.findAll();                                        // Consulta o DB
	}

	public Iterable<Transferencia> obterPorDestino(int id) {                   // Obtem transferências por conta destino
		System.out.println("Consultando transferências por ID da conta destino  ... "+id);
		return this.tranRepo.findByDestino(id);                                // Consulta o DB
	}

	public Transferencia criarTransferencia(Transferencia tran) {                            // Cria uma transferencia nova
		Conta origem = contaRepo.getById(tran.getContaOrigem().getId_conta());               // Resgata conta origem
		Conta destino = contaRepo.getById(tran.getContaDestino().getId_conta());             // Resgata a conta de destino
		System.out.println("cadastrando uma nova pessoa transferência");
		System.out.println("Conta origem "+origem.getId_conta() + "Para Conta destino "+destino.getId_conta());
		System.out.println("o valor transferido é de "+tran.getValor());
		if(origem.getCliente().getAtivo() && destino.getCliente().getAtivo()) {              // Verifica se os clientes estão ativas
			if(origem.getSaldo() - tran.getValor() >= -200.0) {                              // Verifica se o cliente tem saldo (Damos crédito de 200 reais)
				BigDecimal or = BigDecimal.valueOf(origem.getSaldo() - tran.getValor());     // Faz a conversão do saldo
			    or = or.setScale(2, RoundingMode.HALF_UP);                                   // Formata para duas casas decimais
				origem.setSaldo(or.doubleValue());                                           // Define o novo saldo de origem
				BigDecimal dest = BigDecimal.valueOf(destino.getSaldo() + tran.getValor());  // Faz a conversão do saldo
			    dest = dest.setScale(2, RoundingMode.HALF_UP);                               // Formata para duas casas decimais
				destino.setSaldo(dest.doubleValue());                                        // Define o novo saldoa de destino
				tran.setData(getDateTime());                                                 // Pega data atual para a transferência
				contaRepo.save(origem);                                                      // Salva mudanças da conta de origem
				contaRepo.save(destino);                                                     // Salva mudanças da conta de destino
				return this.tranRepo.save(tran);                                             // Salva a transferência no DB
			}
		}
		return null;                                                                         // Caso contrário retorna null
		
	}

	public Iterable<Transferencia> obterPorOrigem(int id) {                                  // Obtem transferências por conta origem
		System.out.println("Consultando transferências por ID da conta origem  ... "+id);
		return this.tranRepo.findByOrigem(id);                                               // Consulta o DB
	}

	public Iterable<Transferencia> obterPorConta(int id) {                                   // Obtem transferências por numero de conta
		System.out.println("Consultando transferências por numero de conta  ... "+id);       // transferencias de saída e entrada
		return this.tranRepo.findHistConta(id);                                              // Consulta o DB
	}
	
	public Iterable<Transferencia> obterHistOrdenado(int conta){                             // Obtem as ultimas 3 transferencias de uma conta
		System.out.println("Consultando as ultimas transferências por conta. Conta pesquisada:: "+conta);
		return this.tranRepo.findHistOrdenado(conta);                                        // Consulta o DB
	}
	
	private String getDateTime() {                                           // Pega o horário local
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Formata a data
		Date date = new Date();                                              // Cria a data
		return dateFormat.format(date);                                      // Retorna a data
	}
}