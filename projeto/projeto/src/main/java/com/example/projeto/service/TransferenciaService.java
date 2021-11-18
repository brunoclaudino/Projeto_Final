package com.example.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto.model.Transferencia;
import com.example.projeto.repository.TransferenciaRepository;

@Service
public class TransferenciaService implements InterfaceTransferenciaService{
	@Autowired
	TransferenciaRepository tranRepo;

	@Override
	public Iterable<Transferencia> obterTodos() {
		return this.tranRepo.findAll();
	}

	@Override
	public Iterable<Transferencia> obterPorDestino(int id) {
		return  this.tranRepo.findByDestino(id);
	}

	@Override
	public Transferencia criarTransferencia(Transferencia tran) {
		return this.tranRepo.save(tran);
	}

	@Override
	public Iterable<Transferencia> obterPorOrigem(int id) {
		return this.tranRepo.findByOrigem(id);
	}

	@Override
	public Iterable<Transferencia> obterPorConta(int id) {
		return this.tranRepo.findHistConta(id);
	}

}
