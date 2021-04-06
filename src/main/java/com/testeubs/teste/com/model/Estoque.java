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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Estoque {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	
	@JsonAlias("product")
	@ManyToOne
	@JoinColumn(name="sigla")
	private Produto produto;
	
	private long quantity;
	
	private float price;
	
	private String type;
	
	private String industry;
	
	private String origin;
	
}
