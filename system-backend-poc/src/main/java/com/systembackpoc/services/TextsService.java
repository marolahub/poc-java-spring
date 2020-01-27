package com.systembackpoc.services;

import com.systembackpoc.entities.Texts;
import com.systembackpoc.exceptions.ExceptionsPoc;
import com.systembackpoc.repositories.ClientsRepository;
import com.systembackpoc.repositories.SegmentsRepository;
import com.systembackpoc.repositories.TextsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TextsService {

    @Autowired
    private TextsRepository repository;
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private SegmentsRepository segmentsRepository;

    public Texts findById(Long id){
        Optional<Texts> text = repository.findById(id);
        return text.orElseThrow(() -> new ExceptionsPoc("Texto não encontrado!"));
    }
    public Texts findBySlug(String slug){
        return repository.findBySlug(slug.toUpperCase());
    }
    public List<Texts> findAll(){ return repository.findAll(); }

    public Texts insert(Texts text){ return repository.save(text); }
    public void deleteById(Long id){
        findById(id);
        repository.deleteById(id);
    }
    public Texts update(Texts text){
        Texts newText = findById(text.getId());
        newText.setSlug(text.getSlug());
        newText.setDescription(text.getDescription());
        newText.setPrice(text.getPrice());
        newText.setPathText(text.getPathText());
        newText.setClient(text.getClient());
        newText.setSegment(text.getSegment());
        return repository.save(newText);
    }
}
