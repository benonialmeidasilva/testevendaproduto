package com.vendaproduto.teste.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class VendaItem {
	
	private String produto_sigla;
	private Long quantity;
	private float price;
	private String price_unit;
	private String type;
	private String industry;
	private String origin;
	
}
