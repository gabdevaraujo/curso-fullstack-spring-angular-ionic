package com.gva.cursomcd.repository;

import javax.persistence.EntityManager;

import com.gva.cursomcd.domain.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

    
}
