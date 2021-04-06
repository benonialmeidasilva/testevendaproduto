package com.testeubs.teste.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Venda {

	private int codigo;
	
	private String logista;
	
	private List<Estoque> estoques;
	
	private long quantidadeTotal;
	
	private float valorFinanceiro;
	
	private float precoMedio;
	
}
