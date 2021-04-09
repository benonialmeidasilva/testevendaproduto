package com.vendaproduto.teste.com.service;

import java.util.List;
import com.vendaproduto.teste.com.model.Venda;

public interface ProdutoService {
	
	public String carregarDados(boolean recarregar) throws Exception;
	public List<Venda> calcularVenda(String produtoSigla, int qtdeLogistas);
	
}
