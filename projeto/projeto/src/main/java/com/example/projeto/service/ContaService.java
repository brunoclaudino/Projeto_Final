package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto.model.Conta;
import com.example.projeto.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository repository;
	
	public Conta findById(Integer id) {
		Optional<Conta> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Conta> findAll() {
		return (List<Conta>) repository.findAll();
	}	

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Conta create(Conta obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Conta update(Integer id, Conta obj) {
		Conta newObj = findById(id);
		newObj.setNumero(obj.getNumero());
		newObj.setAgencia(obj.getAgencia());
		newObj.setSaldo(obj.getSaldo());
		newObj.setDataAbertura(obj.getDataAbertura());
		newObj.setId_cliente(obj.getId_cliente());
		return repository.save(newObj);
	}
	

}
