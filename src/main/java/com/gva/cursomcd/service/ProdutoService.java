package com.gva.cursomcd.service;

import java.util.List;
import java.util.Optional;

import com.gva.cursomcd.domain.Categoria;
import com.gva.cursomcd.domain.Produto;
import com.gva.cursomcd.repository.CategoriaRepository;
import com.gva.cursomcd.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElse(null);
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linePerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}