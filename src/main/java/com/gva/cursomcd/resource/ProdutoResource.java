package com.gva.cursomcd.resource;

import java.util.List;

import com.gva.cursomcd.domain.Produto;
import com.gva.cursomcd.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id){
        Produto produto = produtoService.findById(id);
        if(produto != null) return ResponseEntity.ok().body(produto);
        else return ResponseEntity.notFound().build();
    }
    
}
