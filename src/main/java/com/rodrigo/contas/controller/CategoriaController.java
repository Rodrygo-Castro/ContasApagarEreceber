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
import com.rodrigo.contas.service.CategoriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping("/listar")
    public String index(@RequestParam(value="search", required = false) String q, ModelMap model) {
        
        List<Categoria> listCategorias;

        if ((q != null) && (q.length() > 0)) {
            listCategorias = categoriaService.listAllLikeNome(q);
            model.addAttribute("search", q);
        } else {
            listCategorias = categoriaService.listAll();
            model.addAttribute("search", "");
        }
        
        model.addAttribute("listCategorias", listCategorias);
        return "categorias/index";//aqui vou retornar a view 
    }

    @GetMapping("/adicionar")
    public ModelAndView add(Categoria categoria) {
        ModelAndView mv = new ModelAndView("categorias/adicionar"); // O nome da view deve corresponder ao arquivo HTML no diretório templates
        mv.addObject("categoria", categoria);

        List<Categoria> categorias = categoriaService.listAll();
        mv.addObject("categoria_list", categorias);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            return add(categoria);
        }
        categoriaService.save(categoria);

        ModelAndView mv = new ModelAndView("redirect:/categorias/listar");// Redireciona para evitar resubmissão
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        categoriaService.delete(id);
        return new ModelAndView("redirect:/categorias/listar");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        return add(categoriaService.get(id));
    }
}
