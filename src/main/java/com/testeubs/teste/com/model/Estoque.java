package com.testeubs.teste.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="produto")
public class Estoque {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.MERGE)
	@JoinColumn(name="sigla")
	private Produto produto;
	
	private long quantity;
	private float price;
	private String price_unit;
	private String type;
	private String industry;
	private String origin;
	
	
	
	public Estoque(Produto produto, long quantity, float price, String price_unit, String type, String industry, String origin) {
		this.produto = produto;
		this.quantity = quantity;
		this.price = price;
		this.price_unit = price_unit;
		this.type = type;
		this.industry = industry;
		this.origin = origin;
	}
	
}
