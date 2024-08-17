package com.rodrigo.contas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.contas.model.Contas;
@Repository
public interface ContasRepository extends JpaRepository<Contas, Long>{
    
}
