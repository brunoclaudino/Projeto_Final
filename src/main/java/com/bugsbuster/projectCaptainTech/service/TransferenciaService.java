package com.bugsbuster.projectCaptainTech.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.bugsbuster.projectCaptainTech.model.Transferencia;
import com.bugsbuster.projectCaptainTech.repository.TransferenciaRepository;

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
		tran.setData(pegarData());
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
	
	public String pegarData() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dtf.format(LocalDateTime.now());
	}

}
