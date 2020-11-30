package com.gva.cursomcd.enums;

import lombok.Getter;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    @Getter
    private int cod;

    @Getter
    private String descricao;

    private EstadoPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }
    
    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null) return null;
        for(EstadoPagamento tipo: EstadoPagamento.values()){
            if(tipo.getCod() == cod) return tipo;
        }
        throw new IllegalArgumentException("Tipo não encontrado para o ID: " + cod);
    }
}