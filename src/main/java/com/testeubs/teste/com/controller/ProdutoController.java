package com.testeubs.teste.com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.testeubs.teste.com.model.Produto;
import com.testeubs.teste.com.service.impl.ProdutoServiceImpl;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoServiceImpl produtoService;
	
	@RequestMapping("/listar")
    public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> lista = produtoService.listarProdutos();
		ResponseEntity<List<Produto>> response = new ResponseEntity<List<Produto>>(lista, HttpStatus.OK);
        return response;
    }
	
}
