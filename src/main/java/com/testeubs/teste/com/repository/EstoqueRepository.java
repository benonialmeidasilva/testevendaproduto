package com.testeubs.teste.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.testeubs.teste.com.model.Produto;

@Repository
public interface EstoqueRepository extends JpaRepository<Produto, Long> { }