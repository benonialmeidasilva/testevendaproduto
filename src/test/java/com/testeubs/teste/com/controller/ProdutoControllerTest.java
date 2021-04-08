package com.testeubs.teste.com.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.testeubs.teste.com.repository.EstoqueRepository;
import com.testeubs.teste.com.repository.ProdutoRepository;
import com.testeubs.teste.com.service.impl.ProdutoServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ProdutoControllerTest {
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@Mock
	private EstoqueRepository estoqueRepository;
	 
	@Mock
	private ProdutoServiceImpl produtoService = new ProdutoServiceImpl();
	
	@InjectMocks
	ProdutoController produtoController;
	
	@Test
	void testCalcularVenda() {
		try {
			ResponseEntity<?> response = produtoController.calcularVenda("EMMS", 2);
			assertEquals(response.getStatusCode(), HttpStatus.OK);
		}
		catch(Exception e) {
			fail("Falha no endpoint de cálculo");
		}
	}

	@Test
	void testCarregarDados() {
		try {
			ResponseEntity<String> response = produtoController.carregarDados();
			assertEquals(response.getStatusCode(), HttpStatus.OK);
		}
		catch(Exception e) {
			fail("Falha no endpoint de carga dos dados");
		}
	}

}
