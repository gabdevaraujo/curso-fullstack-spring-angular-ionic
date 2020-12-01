package com.gva.cursomcd.dto;

import java.io.Serializable;

import com.gva.cursomcd.domain.Categoria;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    
    public CategoriaDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }

    
}