package com.systembackpoc.resources;
import com.systembackpoc.entities.Clients;
import com.systembackpoc.entities.DTO.ClientsDTO;
import com.systembackpoc.services.ClientsService;
import com.systembackpoc.utils.FormattUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientsResource {

    @Autowired
    private ClientsService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Clients> findById(@PathVariable Long id) {
        Clients client = service.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @GetMapping
    public ResponseEntity<List<Clients>> findAll(){
        List<Clients> listClient = service.findAll();
        return ResponseEntity.ok().body(listClient);
    }

    @GetMapping(value="/full")
    public ResponseEntity<List<ClientsDTO>> findAllClientsFull() {
        List<Clients> listClients = service.findAll();
        return ResponseEntity.ok().body(listClients.stream().map(obj ->
                new ClientsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value="/name")
    public ResponseEntity<List<ClientsDTO>> findByName(
            @RequestParam(value = "name", defaultValue = "") String name){
        name = FormattUtils.decodeParam(name);
        List<Clients> listClients = service.findByName(name);
        return ResponseEntity.ok().body(listClients.stream().map(obj ->
                new ClientsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value="/status")
    public ResponseEntity<List<ClientsDTO>> findByStatus(
            @RequestParam(value = "active", defaultValue = "") Boolean status){
        List<Clients> listClients = service.findByStatus(status);
        return ResponseEntity.ok().body(listClients.stream().map(obj ->
                new ClientsDTO(obj)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Clients client){
        client = service.insert(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Clients client, @PathVariable Long id){
        client.setId(id);
        service.update(client);
        return ResponseEntity.noContent().build();
    }
}
