package com.rodrigo.contas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.contas.model.Categoria;
import com.rodrigo.contas.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listAll() {
        return categoriaRepository.findAll();
    }

    public List<Categoria> listAllByNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    public List<Categoria> listAllLikeNome(String nome) {
        return categoriaRepository.findByNomeContaining(nome);
    }

    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public boolean existsById(Long id) {
        return categoriaRepository.existsById(id);
    }

    public Categoria get(Long id) {
        return categoriaRepository.findById(id).get();
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}
