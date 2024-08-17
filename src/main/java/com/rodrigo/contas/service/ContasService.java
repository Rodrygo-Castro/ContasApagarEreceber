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
