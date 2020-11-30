package com.gva.cursomcd.service;

import java.util.Optional;

import com.gva.cursomcd.domain.Categoria;
import com.gva.cursomcd.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        
        return obj.orElse(null);
        //return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + ", Tipo: " + Categoria.class.getName()));
    }

	public Categoria insert(Categoria obj) {
        obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
        findById(obj.getId());
		return categoriaRepository.save(obj);
	}

}
