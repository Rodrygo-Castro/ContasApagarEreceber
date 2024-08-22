package com.rodrigo.contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rodrigo.contas.model.Categoria;
import com.rodrigo.contas.model.Contas;
import com.rodrigo.contas.service.CategoriaService;
import com.rodrigo.contas.service.ContasService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contas")
public class ContasController {

    @Autowired
    private ContasService contasService;
    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping("/listar")
    public String index(@RequestParam(value="search", required = false) String q, ModelMap model) {
        List<Contas> listContas;

        if ((q != null) && (q.length() > 0) && (!q.matches("[-+]?[0-9]*\\.?[0-9]+"))) {
            listContas = contasService.listAllLikeDescricao(q);
            model.addAttribute("search", q);
        } else if ((q != null) && (q.length() > 0) && (q.matches("[-+]?[0-9]*\\.?[0-9]+"))) {
            listContas = contasService.listAllFindbyValor(Float.parseFloat(q));
            model.addAttribute("search", q);
        } else {
            listContas = contasService.listAll();
            model.addAttribute("search", q);
        }
        
        model.addAttribute("listContas", listContas);
        return "index";//aqui vou retornar a view 
    }

    @GetMapping("/adicionar")
    public ModelAndView add(Contas contas) {
        ModelAndView mv = new ModelAndView("adicionar"); // O nome da view deve corresponder ao arquivo HTML no diretório templates
        mv.addObject("contas", contas);

        List<Categoria> categoria = categoriaService.listAll();
        mv.addObject("categoria_list", categoria);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("contas") Contas contas, BindingResult result) {
        if (result.hasErrors()) {
            return add(contas);
        }
        contasService.save(contas);

        ModelAndView mv = new ModelAndView("redirect:/contas/listar");// Redireciona para evitar resubmissão
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        contasService.delete(id);
        return new ModelAndView("redirect:/contas/listar");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        return add(contasService.get(id));
    }

}
