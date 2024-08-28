package com.rodrigo.contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Página Inicial");
        return "contas/home"; // Nome da view para a página inicial
    }

    @GetMapping("/listar")
    public String index(@RequestParam(value = "search", required = false) String q, ModelMap model) {
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
        return "contas/index"; // Nome da view para a listagem de contas
    }

    @GetMapping("/adicionar")
    public ModelAndView addForm(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mv = new ModelAndView("contas/adicionar"); // Nome da view para adicionar/editar
        Contas contas = id != null ? contasService.get(id) : new Contas();
        mv.addObject("contas", contas);

        List<Categoria> categoria = categoriaService.listAll();
        mv.addObject("categoria_list", categoria);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("contas") Contas contas, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("contas/adicionar"); // Retorna à página de adição/edição em caso de erro
        }

        if (contas.getCategoria() == null || !categoriaService.existsById(contas.getCategoria().getId())) {
            result.rejectValue("categoria", "error.categoria", "Categoria não encontrada.");
            return new ModelAndView("contas/adicionar"); // Retorna à página de adição/edição em caso de erro
        }

        contasService.save(contas);
        return new ModelAndView("redirect:/contas/listar"); // Redireciona para evitar resubmissão
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        contasService.delete(id);
        return new ModelAndView("redirect:/contas/listar");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        return addForm(id); // Reutiliza o método de adicionar/editar para carregar dados
    }
}
