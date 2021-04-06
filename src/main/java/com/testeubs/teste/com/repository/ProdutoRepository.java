package com.testeubs.teste.com.repository;

import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.testeubs.teste.com.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findBySigla(String lastname);

	default Optional<Produto> findBySigla(Produto produto) {
		return findBySigla(produto.getSigla());
	}

	@Query("select p from Produto p")
	Stream<Produto> streamAllProduts();
	
}