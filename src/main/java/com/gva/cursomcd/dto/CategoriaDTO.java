package com.gva.cursomcd.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.gva.cursomcd.domain.Categoria;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "Preenchimento Obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
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