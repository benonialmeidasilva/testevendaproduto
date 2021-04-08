package com.testeubs.teste.com.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.testeubs.teste.com.model.Estoque;
import com.testeubs.teste.com.model.Produto;
import com.testeubs.teste.com.model.helper.EstoqueHelper;
import com.testeubs.teste.com.repository.EstoqueRepository;
import com.testeubs.teste.com.repository.ProdutoRepository;
import com.testeubs.teste.com.utils.Constantes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GravacaoEstoquesAssincrona implements Runnable {
	
	private EstoqueRepository estoqueRepository;
	private ProdutoRepository produtoRepository;
	private List<EstoqueHelper> listaItens;
	
	public GravacaoEstoquesAssincrona(List<EstoqueHelper> listaItens, EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository) {
		this.listaItens = listaItens;
		this.estoqueRepository = estoqueRepository;
		this.produtoRepository = produtoRepository;
	}

	@Override
	public void run() {
		List<Estoque> listaEstoques = new ArrayList<Estoque>();
		listaItens.forEach(item -> {
			Produto produto;
			Optional<Produto> opt = produtoRepository.findBySigla(item.getProduct());
			if(opt.isPresent())
				produto = opt.get();
			else
				produto = new Produto(item.getProduct());
				produto = produtoRepository.save(produto);
			Estoque estoque = new Estoque(produto, item.getQuantity(),
					                      Float.parseFloat(item.getPrice().replace("$", "")),
					                      item.getPrice().substring(0, item.getPrice().indexOf("$") + 1),
					                      item.getType(), item.getIndustry(), item.getOrigin());
			listaEstoques.add(estoque);
		});
		estoqueRepository.deleteAll();
		Iterable<Estoque> it = listaEstoques;
		estoqueRepository.saveAll(it);
		Constantes.CARGA_DADOS_INICIADA = false;
		System.out.println("Tarefa executada.");
	}

}
