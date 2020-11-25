package com.gva.cursomcd.resource;

import com.gva.cursomcd.domain.Categoria;
import com.gva.cursomcd.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){

        Categoria categoria = categoriaService.findById(id);

        if(categoria != null){
            return ResponseEntity.ok().body(categoria);
        }
        else return ResponseEntity.notFound().build();
    }

}