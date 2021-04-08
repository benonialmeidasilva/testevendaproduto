package com.testeubs.teste.com.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.testeubs.teste.com.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {

	Optional<Produto> findBySigla(String sigla);

	default Optional<Produto> findBySigla(Produto produto) {
		return findBySigla(produto.getSigla());
	}
	
}