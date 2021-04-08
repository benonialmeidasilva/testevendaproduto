package com.testeubs.teste.com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.testeubs.teste.com.model.Venda;
import com.testeubs.teste.com.service.impl.ProdutoServiceImpl;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoServiceImpl produtoService;
	
	@ResponseBody
	@GetMapping(value="calcularVenda/{produto}/{qtde}")
    public ResponseEntity<List<Venda>> calcularVenda(@PathVariable String produto, @PathVariable int qtde) {
		List<Venda> lista = produtoService.calcularVenda(produto, qtde);
		ResponseEntity<List<Venda>> response = new ResponseEntity<List<Venda>>(lista, HttpStatus.OK);
        return response;
    }
	
	@ResponseBody
	@RequestMapping(value="/carregarDados", method=RequestMethod.GET)
    public ResponseEntity<String> carregarDados()  {
		ResponseEntity<String> response;
		try {
			String retornoSaveItens = produtoService.carregarDados();
			response = new ResponseEntity<String>(retornoSaveItens, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<String>("Erro - Os dados não foram carregados! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
        return response;
    }
	
}
