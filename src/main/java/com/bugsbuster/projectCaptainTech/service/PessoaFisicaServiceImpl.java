package com.bugsbuster.projectCaptainTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.model.PessoaFisica;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;
import com.bugsbuster.projectCaptainTech.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaServiceImpl {

	@Autowired
	PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired
	ContaRepository contaRepository;

	public Iterable<PessoaFisica> obterTodos() {
		return this.pessoaFisicaRepository.findAll();
	}

	public PessoaFisica obterPorId(int id) {
		return this.pessoaFisicaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id de Pessoa Física Inválido"));
	}

	public PessoaFisica criarPessoaFisica(PessoaFisica pf) {
		PessoaFisica novaPf = this.pessoaFisicaRepository.save(pf);
		boolean resp = true;
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

	public PessoaFisica desativar(int id) {
		PessoaFisica newPF = obterPorId(id);
		newPF.setAtivo(false);
		return this.pessoaFisicaRepository.save(newPF);
	}
	
	public Iterable<PessoaFisica> obterPorNome(String nome){
		return this.pessoaFisicaRepository.findByNomeContaining(nome);
	}
	
	public Iterable<PessoaFisica> obterPorCpf(String cpf){
		return this.pessoaFisicaRepository.findByCpf(cpf);
	}
	
	public Iterable<PessoaFisica> obterPorTelefone(String telefone){
		return this.pessoaFisicaRepository.findByTelefone(telefone);
	}
	
	public Iterable<PessoaFisica> ObterPorDataNascimento(String data){
		return this.pessoaFisicaRepository.findByDataNascimentoOrderByDataNascimento(data);
	}
	
	public Iterable<PessoaFisica> ObterPorOcupacao(String ocupacao){
		return this.pessoaFisicaRepository.findByOcupacaoOrderByOcupacao(ocupacao);
	}
}
