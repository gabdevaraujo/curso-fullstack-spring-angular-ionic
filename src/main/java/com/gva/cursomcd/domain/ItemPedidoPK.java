package com.gva.cursomcd.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class ItemPedidoPK implements Serializable{

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;
}
