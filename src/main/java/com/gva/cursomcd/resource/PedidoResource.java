package com.gva.cursomcd.resource;

import java.util.List;

import com.gva.cursomcd.domain.Pedido;
import com.gva.cursomcd.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> pedidos(){
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id){
        Pedido pedido = pedidoService.findById(id);
        if(pedido != null) return ResponseEntity.ok().body(pedido);
        else return ResponseEntity.notFound().build();
    }
    
}
