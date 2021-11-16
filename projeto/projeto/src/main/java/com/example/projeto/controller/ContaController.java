package com.example.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.model.Conta;
import com.example.projeto.service.ContaService;


@RestController
@RequestMapping(path = "/conta")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public List<Conta> findAll() {
		return this.contaService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Conta> findById(@PathVariable Integer id) {
		Conta obj = this.contaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public Conta criar(@RequestBody Conta conta) {
		return this.contaService.create(conta);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Conta> update(@PathVariable Integer id, @RequestBody Conta obj) {
		Conta newObj = contaService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		contaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}