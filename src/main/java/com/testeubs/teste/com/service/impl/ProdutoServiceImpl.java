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
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testeubs.teste.com.model.Estoque;
import com.testeubs.teste.com.model.Produto;
import com.testeubs.teste.com.model.Venda;
import com.testeubs.teste.com.model.VendaItem;
import com.testeubs.teste.com.model.helper.EstoqueHelper;
import com.testeubs.teste.com.repository.EstoqueRepository;
import com.testeubs.teste.com.repository.ProdutoRepository;
import com.testeubs.teste.com.service.ProdutoService;
import com.testeubs.teste.com.threads.GravacaoEstoquesAssincrona;
import com.testeubs.teste.com.utils.Constantes;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Override
	@Transactional
	public String carregarDados() throws Exception{
		
		if(!Constantes.CARGA_DADOS_INICIADA) {
			
			Constantes.CARGA_DADOS_INICIADA = true;
			Path pathArquivo1 = Paths.get(Constantes.PATHS_ARQUIVOS_JSON_PRODUTOS.PATH_ARQUIVO_1);
			Path pathArquivo2 = Paths.get(Constantes.PATHS_ARQUIVOS_JSON_PRODUTOS.PATH_ARQUIVO_2);
			Path pathArquivo3 = Paths.get(Constantes.PATHS_ARQUIVOS_JSON_PRODUTOS.PATH_ARQUIVO_3);
			Path pathArquivo4 = Paths.get(Constantes.PATHS_ARQUIVOS_JSON_PRODUTOS.PATH_ARQUIVO_4);
			
			List<EstoqueHelper> listaItens = new ArrayList<EstoqueHelper>();
			listaItens.addAll(lerEstoquesEmArquivoJSon(pathArquivo1.toAbsolutePath().toString()));
			listaItens.addAll(lerEstoquesEmArquivoJSon(pathArquivo2.toAbsolutePath().toString()));
			listaItens.addAll(lerEstoquesEmArquivoJSon(pathArquivo3.toAbsolutePath().toString()));
			listaItens.addAll(lerEstoquesEmArquivoJSon(pathArquivo4.toAbsolutePath().toString()));
			
			if(estoqueRepository.count() != Long.valueOf(listaItens.size())) {
				GravacaoEstoquesAssincrona gravacaoEstoquesAssincrona = new GravacaoEstoquesAssincrona(listaItens, estoqueRepository, produtoRepository);
			    Thread tarefaAssincrona = new Thread(gravacaoEstoquesAssincrona);
			    tarefaAssincrona.start();
			    System.out.println("Tarefa " + tarefaAssincrona.getId() + " - " + tarefaAssincrona.getName() + " iniciada.");
				return "Solicitação enviada, o sistema está gravando " + listaItens.size() + " itens.";
			}
			else {
				return "Todos os " + listaItens.size() + " itens já estão gravados na base.";
			}
		}
		else {
			return "Já existe uma carga de dados em execução!";
		}
		
	}

	@Override
	public List<Venda> calcularVenda(String produtoSigla, int qtdeLogistas) {
		
		Optional<Produto> optional = produtoRepository.findBySigla(produtoSigla);
		
		if(optional.isPresent() && qtdeLogistas > 0) {
			
			Produto produto = produtoRepository.findBySigla(produtoSigla).get();
			
			if(produto.getEstoques() != null && produto.getEstoques().size() > 0) {
			
				//Criando uma venda para cada logista.
				List<Venda> vendas = new ArrayList<Venda>();
				int numLogista = 1;
				while(numLogista <= qtdeLogistas) {
					Venda venda = new Venda("Logista " + numLogista, new ArrayList<VendaItem>(), new Long(0), 0, 0);
					vendas.add(venda);
					numLogista++;
				}
				
				vendas = distribuirEstoquesEntreLogistas(vendas, produto, qtdeLogistas);
				
				return vendas;
			}
			else {
				return new ArrayList<Venda>();
			}
			
		}
		return new ArrayList<Venda>();
	}
	
	public List<EstoqueHelper> lerEstoquesEmArquivoJSon(String path) throws IOException {
        String jsonText = String.join(" ", Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
        Type collectionType = new TypeToken<List<EstoqueHelper>>(){}.getType();
        List<EstoqueHelper> lista = new Gson().fromJson(jsonText, collectionType);
        return lista;
    }
	
	public List<Venda> distribuirEstoquesEntreLogistas(List<Venda> vendas, Produto produto, int qtdeLogistas){
		//Os estoques virão ordenados por preço em ordem decrescente
		int numLogista = 0;
		for(Estoque estoque: produto.getEstoques()) {
			Long qtdeDividido = estoque.getQuantity() / qtdeLogistas;
			Long qtdeRestanteDaDistribuicao = estoque.getQuantity() % qtdeLogistas;
			vendas.forEach(venda -> {
				VendaItem item = new VendaItem(produto.getSigla(), qtdeDividido, estoque.getPrice(),
						                       estoque.getPrice_unit(), estoque.getType(),
						                       estoque.getIndustry(), estoque.getOrigin());
				venda.getItens().add(item);
				venda.calcularTotais();
			});
			while(qtdeRestanteDaDistribuicao > 0){
				int itemDaVez = vendas.get(numLogista).getItens().size() - 1;
				vendas.get(numLogista).getItens().get(itemDaVez).setQuantity(vendas.get(numLogista).getItens().get(itemDaVez).getQuantity() + 1);
				vendas.get(numLogista).calcularTotais();
				qtdeRestanteDaDistribuicao--;
				if(numLogista < (qtdeLogistas - 1)) {
					numLogista++;
				}
				else {
					numLogista = 0;
				}
			}
		}
		return vendas;
	}
	
}
