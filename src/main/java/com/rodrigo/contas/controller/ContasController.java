package com.rodrigo.contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rodrigo.contas.model.Contas;
import com.rodrigo.contas.service.ContasService;

@Controller
@RequestMapping("/contas")
public class ContasController {

    @Autowired
    private ContasService contasService;

    @RequestMapping("/listar")
    public String index(ModelMap model) {
        List<Contas> listaContas = contasService.listAll();
        model.addAttribute("listaContas", listaContas);
        return "index";//aqui vou retornar a view 
    }

    @GetMapping("/adicionar")
    public ModelAndView add(Contas contas) {
        ModelAndView mv = new ModelAndView("adicionar"); // O nome da view deve corresponder ao arquivo HTML no diretório templates
        mv.addObject("contas", contas);
        return mv;
    }

}
