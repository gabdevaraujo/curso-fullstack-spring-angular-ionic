package com.gva.cursomcd.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido implements Serializable{
 
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido" )
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ENDEREÇO_DE_ENTREGA_ID")
    private Endereco enderecoDeEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    @Builder
	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
    }
    
    public double getTotal(){
        double soma = 0;
        for(ItemPedido ip : itens){
            soma += ip.getSubTotal();
        }
        return soma;
    }


    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido Número: ");
        builder.append(getId());
        builder.append("\n");
        builder.append("Instante: ");
        builder.append(sdf.format(getInstante()));
        builder.append("\n");
        builder.append("Cliente: ");
        builder.append(getCliente().getNome());
        builder.append("\n");
        builder.append("Situação do Pagamento: ");
        builder.append(getPagamento().getEstado().getDescricao());
        builder.append("\n");
        for(ItemPedido ip : getItens()){
            builder.append("Item: ");
            builder.append(ip.toString());
            builder.append("\n");
        }
        builder.append("Valor Total: ");
        builder.append(nf.format(getTotal()));
        return builder.toString();
    }

}