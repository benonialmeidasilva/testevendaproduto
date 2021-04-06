package com.testeubs.teste.com.service;

import java.util.List;
import com.testeubs.teste.com.model.Venda;

public interface ProdutoService {
	
	public List<Venda> calcularVenda(String produtoSigla, int qtdeLogistas);
	
}
