package com.gva.cursomcd.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gva.cursomcd.domain.Cidade;
import com.gva.cursomcd.domain.Cliente;
import com.gva.cursomcd.domain.Endereco;
import com.gva.cursomcd.dto.ClienteDTO;
import com.gva.cursomcd.dto.ClienteNewDTO;
import com.gva.cursomcd.enums.TipoCliente;
import com.gva.cursomcd.repository.ClienteRepository;
import com.gva.cursomcd.repository.EnderecoRepository;
import com.gva.cursomcd.service.exceptions.DataIntegrityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        
        return obj.orElse(null);
        //return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + ", Tipo: " + cliente.class.getName()));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

	public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

    //Atualiza um cliente
	public Cliente update(Cliente obj) {
        //Busca um cliente no BD e atribui a newObj
        Cliente newObj = findById(obj.getId());
        //Chama updateData passando os dois objetos, os dados que vieram do front continuam em obj e o objeto newObj está com os dados do BD
        updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

    public void delete(Integer id) {
        findById(id);
        try{
            clienteRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados.");
        }
        
    }
    
    public Page<Cliente> findPage(Integer page, Integer linePerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    public Cliente fromDTO(@Valid ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, new Cidade(objDto.getCidadeId(), null, null));
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2() != null) cli.getTelefones().add(objDto.getTelefone2());
        if(objDto.getTelefone3() != null) cli.getTelefones().add(objDto.getTelefone3());
        return cli;
    }
    
    // Atualiza newObj com os dados vindos apenas do front que estão em obj
	private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

	
    
}
