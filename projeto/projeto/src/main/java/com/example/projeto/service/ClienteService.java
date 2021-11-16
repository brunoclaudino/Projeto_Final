package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto.model.Cliente;
import com.example.projeto.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Cliente create(Cliente obj) {
		obj.setId_cliente(null);
		return repository.save(obj);
	}
	
	public Cliente update(Integer id, Cliente obj) {
		Cliente newObj = findById(id);
		newObj.setNome(obj.getNome());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setEmail(obj.getEmail());
		newObj.setDataCadastro(obj.getDataCadastro());
		return repository.save(newObj);
	}

}
