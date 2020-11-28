package com.gva.cursomcd.service;

import java.util.Optional;

import com.gva.cursomcd.domain.Cidade;
import com.gva.cursomcd.repository.CidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CidadeService {

    @Autowired
    private CidadeRepository repository;

	public Cidade findCidade(Integer id) {
        Optional<Cidade> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public Cidade save(Cidade cidade) {
		return repository.save(cidade);
	}
    
}