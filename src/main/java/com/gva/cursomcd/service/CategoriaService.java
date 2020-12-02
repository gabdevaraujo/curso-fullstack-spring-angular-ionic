package com.gva.cursomcd.service;

import java.util.List;
import java.util.Optional;

import com.gva.cursomcd.domain.Categoria;
import com.gva.cursomcd.dto.CategoriaDTO;
import com.gva.cursomcd.repository.CategoriaRepository;
import com.gva.cursomcd.service.exceptions.DataIntegrityException;
import com.gva.cursomcd.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
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
        //Busca uma Categoria no BD e atribui a newObj
        Categoria newObj = findById(obj.getId());
        //Chama updateData passando os dois objetos, os dados que vieram do front continuam em obj e o objeto newObj está com os dados do BD
        updateData(newObj, obj);
		return categoriaRepository.save(newObj);
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
    
    public Page<Categoria> findPage(Integer page, Integer linePerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getNome());
    }

    // Atualiza newObj com os dados vindos apenas do front que estão em obj
	private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }

}
