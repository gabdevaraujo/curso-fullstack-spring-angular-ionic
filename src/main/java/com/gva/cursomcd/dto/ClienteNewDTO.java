package com.gva.cursomcd.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.gva.cursomcd.service.validation.ClienteInsert;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@ClienteInsert
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "Tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    @NotBlank(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    private String cpfOuCnpj;

    private Integer tipo;

    @NotBlank(message = "Preenchimento obrigatório")
    private String logradouro;

    @NotBlank(message = "Preenchimento obrigatório")
    private String numero;

    private String complemento;

    private String bairro;

    @NotBlank(message = "Preenchimento obrigatório")
    private String cep;

    @NotBlank(message = "Preenchimento obrigatório")
    private String telefone1;

    private String telefone2;

    private String telefone3;

    private Integer cidadeId;
}