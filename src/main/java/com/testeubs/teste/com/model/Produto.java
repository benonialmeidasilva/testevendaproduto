package com.testeubs.teste.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="produto")
public class Produto {
	
	@Id
	@Column(unique=true, nullable=false)
	private String sigla;
	
	@OrderBy("price desc, quantity desc")
	@OneToMany(mappedBy = "produto")
	private List<Estoque> estoques;
	
	
	public Produto() {
	}
	
	public Produto(String sigla) {
		this.sigla = sigla;
	}
	
}
