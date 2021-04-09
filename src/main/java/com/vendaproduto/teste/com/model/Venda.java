package com.vendaproduto.teste.com.model;

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
	
	private Long quantidadeTotal;
	
	private float valorFinanceiro;
	
	private float precoMedio;
	
	
	
	public void calcularTotais() {
		if(itens != null) {
			quantidadeTotal = new Long(0);
			valorFinanceiro = 0;
			itens.forEach(item -> {
				quantidadeTotal = quantidadeTotal + item.getQuantity();
				valorFinanceiro = valorFinanceiro + (item.getQuantity() * item.getPrice());
			});
			precoMedio = valorFinanceiro / quantidadeTotal;
		}
	}
	
}
