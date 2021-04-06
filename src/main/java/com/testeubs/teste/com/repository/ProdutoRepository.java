package com.testeubs.teste.com.repository;

import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.testeubs.teste.com.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findAllByName(String lastname);

	default Optional<Produto> findAllByName(Produto produto) {
		return findAllByName(produto.getName());
	}

	@Query("select p from Produto p")
	Stream<Produto> streamAllProduts();
	
}