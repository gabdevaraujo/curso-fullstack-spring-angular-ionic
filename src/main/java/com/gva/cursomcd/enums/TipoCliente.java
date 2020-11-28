package com.gva.cursomcd.enums;

import lombok.Builder;
import lombok.Getter;

public enum TipoCliente {
    
    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    @Getter
    private int cod;

    @Getter
    private String descricao;

    private TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }
    
    public static TipoCliente toEnum(Integer cod){
        if(cod == null) return null;
        for(TipoCliente tipo: TipoCliente.values()){
            if(tipo.getCod() == cod) return tipo;
        }
        throw new IllegalArgumentException("Tipo não encontrado para o ID: " + cod);
    }
}