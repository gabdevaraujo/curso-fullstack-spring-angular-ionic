package com.gva.cursomcd.service;

import java.util.List;
import java.util.Optional;

import com.gva.cursomcd.domain.Categoria;
import com.gva.cursomcd.repository.CategoriaRepository;
import com.gva.cursomcd.service.exceptions.DataIntegrityException;
import com.gva.cursomcd.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll(){
        List<Categoria> categorias = categoriaRepository.findAll();
        if(categorias.size() > 0){
            return categorias;
        }
        else return null;
    }

    public Categoria findById(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        
        //return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

	public Categoria insert(Categoria obj) {
        obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
        findById(obj.getId());
		return categoriaRepository.save(obj);
	}

	public void delete(Integer id) {
        findById(id);
        try{
            categoriaRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }
        
	}

}
