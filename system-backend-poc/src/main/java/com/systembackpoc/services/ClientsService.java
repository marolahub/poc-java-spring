package com.systembackpoc.services;

import com.systembackpoc.entities.Clients;
import com.systembackpoc.exceptions.ExceptionsPoc;
import com.systembackpoc.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository repository;

    // GET (read)
    public Clients findById(Long id){
        Optional<Clients> client = repository.findById(id);
        return client.orElseThrow(() -> new ExceptionsPoc("Cliente não encontrado!"));
    }
    public List<Clients> findAll(){ return repository.findAll(); }
    public List<Clients> findByName(String name) { return repository.findByName(name); }
    public List<Clients> findByStatus(Boolean status) { return repository.findByStatus(status); }

    // POST (create)
    public Clients insert(Clients client){ return repository.save(client); }

    // DELETE
    public void delete(Long id){
        repository.findById(id);
        repository.deleteById(id);
    }
    // PUT (update)
    public Clients update(Clients client) {
        Clients newClient = findById(client.getId());
        updateData(newClient, client);
        return repository.save(newClient);
    }

    private void updateData(Clients newClient, Clients client) {
        newClient.setName(client.getName());
        newClient.setPhone(client.getPhone());
        newClient.setActive(client.getActive());
        newClient.setPayType(client.getPayType());
        newClient.setSendStatusBalance(client.getSendStatusBalance());
    }
}
