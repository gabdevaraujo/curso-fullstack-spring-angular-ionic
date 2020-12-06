package com.gva.cursomcd.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gva.cursomcd.domain.ItemPedido;
import com.gva.cursomcd.domain.PagamentoComBoleto;
import com.gva.cursomcd.domain.Pedido;
import com.gva.cursomcd.enums.EstadoPagamento;
import com.gva.cursomcd.repository.PagamentoRepository;
import com.gva.cursomcd.repository.PedidoRepository;
import com.gva.cursomcd.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElse(null);
    }

	public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.00);
            ip.setPreco(produtoRepository.findById(ip.getProduto().getId()).get().getPreco());
            ip.setPedido(obj);
        }
        return obj;
	}
}