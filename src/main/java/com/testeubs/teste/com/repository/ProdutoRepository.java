package com.testeubs.teste.com.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import com.testeubs.teste.com.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findByName(String lastname);

	default Optional<Produto> findByName(Produto produto) {
		return findByName(produto.getName());
	}

	@Query("select p from Produto p")
	Stream<Produto> streamAllProduts();

	@Async
	CompletableFuture<List<Produto>> readAllBy();
	
}