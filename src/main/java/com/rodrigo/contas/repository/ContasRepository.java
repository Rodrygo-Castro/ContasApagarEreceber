package com.rodrigo.contas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.contas.model.Contas;
@Repository
public interface ContasRepository extends JpaRepository<Contas, Long>{
    
    List<Contas> findByDescricao(String descricao);

    List<Contas> findByDescricaoContaining(String descricao);

    List<Contas> findByValor(float valor);
}
