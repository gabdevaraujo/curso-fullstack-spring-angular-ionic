package com.gva.cursomcd.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.gva.cursomcd.domain.Cliente;
import com.gva.cursomcd.service.validation.ClienteUpdate;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@ClienteUpdate
public class ClienteDTO {

    @Getter
    @Setter
    private Integer id;
    
    @Getter
    @Setter
    @NotBlank(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "Tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    @Getter
    @Setter
    @NotBlank(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public ClienteDTO(Cliente obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }

    
}