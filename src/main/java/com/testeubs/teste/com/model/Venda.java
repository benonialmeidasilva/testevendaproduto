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
	
	private String logista;
	
	private List<VendaItem> itens;
	
	private long quantidadeTotal;
	
	private float valorFinanceiro;
	
	private float precoMedio;
	
}
