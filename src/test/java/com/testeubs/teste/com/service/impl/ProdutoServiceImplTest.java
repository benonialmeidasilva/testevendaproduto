package com.testeubs.teste.com.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.testeubs.teste.com.model.Estoque;
import com.testeubs.teste.com.model.Produto;
import com.testeubs.teste.com.model.Venda;
import com.testeubs.teste.com.model.VendaItem;
import com.testeubs.teste.com.model.helper.EstoqueHelper;
import com.testeubs.teste.com.repository.EstoqueRepository;
import com.testeubs.teste.com.repository.ProdutoRepository;
import com.testeubs.teste.com.utils.Constantes;

@RunWith(MockitoJUnitRunner.class)
class ProdutoServiceImplTest {
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@Mock
	private EstoqueRepository estoqueRepository;
	
	@InjectMocks
	private ProdutoServiceImpl produtoService = new ProdutoServiceImpl();

	@Test
	void testLerEstoquesEmArquivoJSon() {
		try {
			Path pathArquivo1 = Paths.get(Constantes.PATHS_ARQUIVOS_JSON_PRODUTOS.PATH_ARQUIVO_1);
			List<EstoqueHelper> listaItens = new ArrayList<EstoqueHelper>();
			String dir = pathArquivo1.toAbsolutePath().toString();
			listaItens.addAll(produtoService.lerEstoquesEmArquivoJSon(dir));
			assertEquals(true, listaItens.size() > 0);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Erro ao ler os arquivos JSON.");
		}
	}

	@Test
	void testDistribuirEstoquesEntreLogistas() {
		try {
			int qtdeLogistas = 2;
			List<Venda> vendas = new ArrayList<Venda>();
			Venda venda1 = new Venda("Logista 1", new ArrayList<VendaItem>(), new Long(0), 0, 0);
			vendas.add(venda1);
			Venda venda2 = new Venda("Logista 2", new ArrayList<VendaItem>(), new Long(0), 0, 0);
			vendas.add(venda2);
			Produto produto = new Produto();
			produto.setSigla("EMMS");
			produto.setEstoques(new ArrayList<Estoque>());
			Estoque e1 = new Estoque(produto, 35, 5, "$", "3XL", "Industria teste", "PR");
			Estoque e2 = new Estoque(produto, 40, 7, "$", "3XL", "Industria teste", "PR");
			produto.getEstoques().add(e1);
			produto.getEstoques().add(e2);
			vendas = produtoService.distribuirEstoquesEntreLogistas(vendas, produto, qtdeLogistas);
			assertEquals(true, vendas.get(vendas.size() - 1).getItens().size() > 0);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao distribuir os estoques para venda.");
		}
	}

}
