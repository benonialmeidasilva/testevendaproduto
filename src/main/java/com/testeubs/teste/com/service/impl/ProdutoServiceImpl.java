package com.testeubs.teste.com.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testeubs.teste.com.model.Estoque;
import com.testeubs.teste.com.model.Produto;
import com.testeubs.teste.com.model.Venda;
import com.testeubs.teste.com.repository.ProdutoRepository;
import com.testeubs.teste.com.service.ProdutoService;
import com.testeubs.teste.com.utils.AbstractReader;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	
	@Override
	public boolean carregarDados() throws Exception{
		URL path = new ClassPathResource("/massa_produtos/data_1.json").getURL();
		System.out.println("path: " + path.toString());
		System.out.println("paths: " + path.getPath());
		List<Estoque> lista = lerEstoquesEmArquivoJSon(path.getPath());
		lista.forEach(item -> {
			System.out.println("item: " + item.getIndustry());
		});
		return false;
	}

	@Override
	public List<Venda> calcularVenda(String produtoSigla, int qtdeLogistas) {
		Optional<Produto> optional = produtoRepository.findBySigla(produtoSigla);
		if(optional.isPresent()) {
			Produto produto = produtoRepository.findBySigla(produtoSigla).get();
			return new ArrayList<Venda>();
		}
		return new ArrayList<Venda>();
	}
	
	public List<Estoque> lerEstoquesEmArquivoJSon(String path) throws IOException {
        String jsonText = AbstractReader.readJson(path);
        Type collectionType = new TypeToken<List<Estoque>>(){}.getType();
        return new Gson().fromJson(jsonText, collectionType);
    }
	
}
