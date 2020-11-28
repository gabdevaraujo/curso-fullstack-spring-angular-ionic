package com.gva.cursomcd.service;

import java.util.Optional;

import com.gva.cursomcd.domain.Cliente;
import com.gva.cursomcd.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        
        return obj.orElse(null);
        //return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + ", Tipo: " + cliente.class.getName()));
    }

}
