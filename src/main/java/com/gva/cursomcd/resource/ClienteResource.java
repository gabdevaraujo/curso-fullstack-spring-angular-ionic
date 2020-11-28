package com.gva.cursomcd.resource;

import com.gva.cursomcd.domain.Categoria;
import com.gva.cursomcd.domain.Cliente;
import com.gva.cursomcd.service.CategoriaService;
import com.gva.cursomcd.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){

        Cliente cliente = clienteService.findById(id);

        if(cliente != null){
            return ResponseEntity.ok().body(cliente);
        }
        else return ResponseEntity.notFound().build();
    }

}