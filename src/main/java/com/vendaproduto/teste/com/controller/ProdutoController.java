package com.vendaproduto.teste.com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.vendaproduto.teste.com.model.Venda;
import com.vendaproduto.teste.com.service.impl.ProdutoServiceImpl;
import com.vendaproduto.teste.com.utils.Constantes;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoServiceImpl produtoService;
	
	@ResponseBody
	@GetMapping(value="calcularVenda/{produto}/{qtde}")
    public ResponseEntity<?> calcularVenda(@PathVariable String produto, @PathVariable int qtde) {
		try {
			List<Venda> lista = produtoService.calcularVenda(produto, qtde);
			if(lista != null && lista.size() > 0) {
				return ResponseEntity.ok(lista);
			}
			else {
				return ResponseEntity.ok("Não foram encontrados estoques para o produto desejado! Se o produto digitado estiver correto, favor executar a carga dos dados, ou aguardar até que ela seja finalizada e tentar novamente.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
    }
	
	@ResponseBody
	@RequestMapping(value="/carregarDados", method=RequestMethod.GET)
    public ResponseEntity<String> carregarDados()  {
		ResponseEntity<String> response;
		try {
			String retornoSaveItens = produtoService.carregarDados(false);
			response = new ResponseEntity<String>(retornoSaveItens, HttpStatus.OK);
		} catch (Exception e) {
			Constantes.CARGA_DADOS_INICIADA = false;
			response = new ResponseEntity<String>("Erro - Os dados não foram carregados!", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
        return response;
    }
	
	@ResponseBody
	@RequestMapping(value="/recarregarDados", method=RequestMethod.GET)
    public ResponseEntity<String> recarregarDados()  {
		ResponseEntity<String> response;
		try {
			String retornoSaveItens = produtoService.carregarDados(true);
			response = new ResponseEntity<String>(retornoSaveItens, HttpStatus.OK);
		} catch (Exception e) {
			Constantes.CARGA_DADOS_INICIADA = false;
			response = new ResponseEntity<String>("Erro - Os dados não foram carregados!", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
        return response;
    }
	
}
