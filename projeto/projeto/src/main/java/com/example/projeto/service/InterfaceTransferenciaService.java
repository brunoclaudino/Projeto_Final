package com.example.projeto.service;

import com.example.projeto.model.Transferencia;

public interface InterfaceTransferenciaService {
	public Iterable<Transferencia> obterTodos();
	
	public Iterable<Transferencia> obterPorDestino(int id);
	
	public Iterable<Transferencia> obterPorOrigem(int id);
	
	public Iterable<Transferencia> obterPorConta(int id);
	
	public Transferencia criarTransferencia(Transferencia tran);

}
