package com.gva.cursomcd.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.gva.cursomcd.domain.Cliente;
import com.gva.cursomcd.dto.ClienteDTO;
import com.gva.cursomcd.dto.ClienteNewDTO;
import com.gva.cursomcd.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id){

        Cliente cliente = clienteService.findById(id);

        if(cliente != null){
            return ResponseEntity.ok().body(cliente);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clientesDTO = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientesDTO);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value="page", defaultValue = "0") Integer page, 
            @RequestParam(value="linesPerPage", defaultValue = "24") Integer linePerPage, 
            @RequestParam(value="direction", defaultValue = "ASC") String direction, 
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy){

        Page<Cliente> clientes = clienteService.findPage(page, linePerPage, direction, orderBy);
        Page<ClienteDTO> clientesDTO = clientes.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(clientesDTO);
    }

    //Chama o service para inserção de uma nova cliente passando a cliente vinda no body da requisição
    //Para devolver a resposta correta que no caso é 201 CREATED temos que passar a URI da nova cliente criada por iSsso o encadeamento de métodos
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
        Cliente obj = clienteService.fromDTO(objDto);
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(obj.getId())
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
        Cliente obj = clienteService.fromDTO(objDto);
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteById(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}