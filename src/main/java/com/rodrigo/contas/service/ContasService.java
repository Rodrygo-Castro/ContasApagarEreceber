package com.rodrigo.contas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.contas.model.Contas;
import com.rodrigo.contas.repository.ContasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContasService {
    
    @Autowired
    private ContasRepository contasRepository;

    public List<Contas> listAll() {
        return contasRepository.findAll();
    }

    public List<Contas> listAllByDescricao(String descricao) {
        return contasRepository.findByDescricao(descricao);
    }

    public List<Contas> listAllLikeDescricao(String descricao) {
        return contasRepository.findByDescricaoContaining(descricao);
    }

    public List<Contas> listAllFindbyValor(float valor) {
        return contasRepository.findByValor(valor);
    }

    public void save(Contas contas) {
        contasRepository.save(contas);
    }

    public Contas get(Long id) {
        return contasRepository.findById(id).get();
    }

    public void delete(Long id) {
        contasRepository.deleteById(id);
    }
}
