package com.gva.cursomcd.service;

import java.util.List;
import java.util.Optional;

import com.gva.cursomcd.domain.Produto;
import com.gva.cursomcd.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElse(null);
    }
}