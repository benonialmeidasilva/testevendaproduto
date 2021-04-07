package com.testeubs.teste.com.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testeubs.teste.com.model.Estoque;
import com.testeubs.teste.com.model.Produto;
import com.testeubs.teste.com.model.Venda;
import com.testeubs.teste.com.model.helper.EstoqueHelper;
import com.testeubs.teste.com.repository.EstoqueRepository;
import com.testeubs.teste.com.repository.ProdutoRepository;
import com.testeubs.teste.com.service.ProdutoService;
import com.testeubs.teste.com.utils.Constantes;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String carregarDados() throws Exception{
		
		Path pathArquivo1 = Paths.get(Constantes.PATHS_ARQUIVOS_JSON_PRODUTOS.PATH_ARQUIVO_1);
		Path pathArquivo2 = Paths.get(Constantes.PATHS_ARQUIVOS_JSON_PRODUTOS.PATH_ARQUIVO_2);
		Path pathArquivo3 = Paths.get(Constantes.PATHS_ARQUIVOS_JSON_PRODUTOS.PATH_ARQUIVO_3);
		Path pathArquivo4 = Paths.get(Constantes.PATHS_ARQUIVOS_JSON_PRODUTOS.PATH_ARQUIVO_4);
		
		List<Estoque> listaEstoques = new ArrayList<Estoque>();
		List<EstoqueHelper> listaItens = new ArrayList<EstoqueHelper>();
		listaItens.addAll(lerEstoquesEmArquivoJSon(pathArquivo1.toAbsolutePath().toString()));
		listaItens.addAll(lerEstoquesEmArquivoJSon(pathArquivo2.toAbsolutePath().toString()));
		listaItens.addAll(lerEstoquesEmArquivoJSon(pathArquivo3.toAbsolutePath().toString()));
		listaItens.addAll(lerEstoquesEmArquivoJSon(pathArquivo4.toAbsolutePath().toString()));
		listaItens.forEach(item -> {
			Estoque estoque = new Estoque(obterProdutoExistenteOuNovo(item.getProduct()), item.getQuantity(),
					                      Float.parseFloat(item.getPrice().replace("$", "")),
					                      item.getPrice().substring(0, item.getPrice().indexOf("$") + 1),
					                      item.getType(), item.getIndustry(), item.getOrigin());
			listaEstoques.add(estoque);
			//estoqueRepository.saveAndFlush(estoque);
		});
		Iterable<Estoque> it = listaEstoques;
		estoqueRepository.saveAll(it);
		return "OK - Gravou " + listaEstoques.size() + " itens.";
		
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
	
	public List<EstoqueHelper> lerEstoquesEmArquivoJSon(String path) throws IOException {
        String jsonText = String.join(" ", Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
        Type collectionType = new TypeToken<List<EstoqueHelper>>(){}.getType();
        List<EstoqueHelper> lista = new Gson().fromJson(jsonText, collectionType);
        return lista;
    }
	
	public Produto obterProdutoExistenteOuNovo(String sigla) {
		Produto produto;
		Optional<Produto> opt = produtoRepository.findBySigla(sigla);
		if(opt.isPresent())
			produto = opt.get();
		else
			produto = new Produto(sigla);
			produto = produtoRepository.save(produto);
		return produto;
	}
	
}
