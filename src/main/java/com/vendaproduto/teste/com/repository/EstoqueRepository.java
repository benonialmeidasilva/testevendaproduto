package com.vendaproduto.teste.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vendaproduto.teste.com.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> { }