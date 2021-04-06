package com.testeubs.teste.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Produto {
	
	@JsonAlias("product")
	private String name;
	
	private long quantity;
	
	private float price;
	
	private String type;
	
	private String industry;
	
	private String origin;
	
}
