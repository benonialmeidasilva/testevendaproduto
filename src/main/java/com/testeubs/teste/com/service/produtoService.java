package com.testeubs.teste.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeubs.teste.com.model.Produto;
import com.testeubs.teste.com.repository.ProdutoRepository;

@Service
public class produtoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	private Produto produto;
	

}
