package com.rodrigo.contas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.contas.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
    List<Categoria> findByNome(String nome);

    List<Categoria> findByNomeContaining(String nome);

}
