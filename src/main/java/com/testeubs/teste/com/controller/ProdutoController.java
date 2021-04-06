package com.testeubs.teste.com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
	@RequestMapping(value="/calcularVenda", method=RequestMethod.GET)
    public ResponseEntity<List<Venda>> calcularVenda(@RequestParam("produtoSigla") String produtoSigla,
    		                                         @RequestParam("qtdeLogistas") int qtdeLogistas) {
		List<Venda> lista = produtoService.calcularVenda(produtoSigla, qtdeLogistas);
		ResponseEntity<List<Venda>> response = new ResponseEntity<List<Venda>>(lista, HttpStatus.OK);
        return response;
    }
	
	@Transactional
	@ResponseBody
	@RequestMapping(value="/carregarDados", method=RequestMethod.GET)
    public ResponseEntity<String> carregarDados()  {
		ResponseEntity<String> response;
		try {
			produtoService.carregarDados();
			response = new ResponseEntity<String>("Dados Carregados com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<String>("Os dados não foram carregados! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
        return response;
    }
	
}
