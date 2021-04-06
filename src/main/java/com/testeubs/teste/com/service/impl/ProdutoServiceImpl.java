package com.testeubs.teste.com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.testeubs.teste.com.model.Produto;
import com.testeubs.teste.com.model.Venda;
import com.testeubs.teste.com.repository.ProdutoRepository;
import com.testeubs.teste.com.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Venda> calcularVenda(String produtoSigla, int qtdeLogistas) {
		System.out.println("sigla: " + produtoSigla + ", qtde: " + qtdeLogistas);
		Optional<Produto> optional = produtoRepository.findBySigla(produtoSigla);
		if(optional.isPresent()) {
			Produto produto = produtoRepository.findBySigla(produtoSigla).get();
			return new ArrayList<Venda>();
		}
		return new ArrayList<Venda>();
	}
	
}
