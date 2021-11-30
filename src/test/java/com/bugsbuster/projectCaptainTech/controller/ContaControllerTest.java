package com.bugsbuster.projectCaptainTech.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.bugsbuster.projectCaptainTech.model.PessoaFisica;
import com.bugsbuster.projectCaptainTech.service.PessoaFisicaServiceImpl;

import io.restassured.http.ContentType;

//import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*; // static para usar os metodos sem a descrição inicial

@WebMvcTest
public class ContaControllerTest {
	
	@Autowired
	private PessoaFisicaController pessoaFisicaController;
	
	@MockBean
	private PessoaFisicaServiceImpl pessoaFisicaService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.pessoaFisicaController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPessoaFisica() {
		
		when(this.pessoaFisicaService.obterPorId(1))
			.thenReturn(new PessoaFisica("teste2021@email.com","(51)99740 2000" , "Cliente Teste Unitario", "00432518054", "23/10/1984", "desenvolvedor"));
		
		
		given()
			.accept(ContentType.JSON)
			.when()
				.get("/clientePF")
			.then()
				.statusCode(HttpStatus.OK.value());
	}

}
