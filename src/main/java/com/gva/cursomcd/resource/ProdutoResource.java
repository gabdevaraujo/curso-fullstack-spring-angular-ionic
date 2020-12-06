package com.gva.cursomcd.resource;

import java.util.List;

import com.gva.cursomcd.domain.Produto;
import com.gva.cursomcd.dto.ProdutoDTO;
import com.gva.cursomcd.resource.utils.URL;
import com.gva.cursomcd.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    /* @GetMapping
    public List<Produto> produtos(){
        return produtoService.findAll();
    } */

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id){
        Produto produto = produtoService.findById(id);
        if(produto != null) return ResponseEntity.ok().body(produto);
        else return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
        @RequestParam(value="nome", defaultValue = "") String nome,
        @RequestParam(value="categoria", defaultValue = "") String categoria, 
        @RequestParam(value="page", defaultValue = "0") Integer page, 
        @RequestParam(value="linesPerPage", defaultValue = "24") Integer linePerPage, 
        @RequestParam(value="direction", defaultValue = "ASC") String direction, 
        @RequestParam(value="orderBy", defaultValue = "nome") String orderBy){

        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categoria);
        Page<Produto> produtos = produtoService.search(nomeDecoded, ids, page, linePerPage, direction, orderBy);
        Page<ProdutoDTO> produtosDTO = produtos.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(produtosDTO);
    }

    
}
