package com.gva.cursomcd.resource;

import java.util.List;

import com.gva.cursomcd.domain.Produto;
import com.gva.cursomcd.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> produtos(){
        return produtoService.findAll();
    }
    
}
