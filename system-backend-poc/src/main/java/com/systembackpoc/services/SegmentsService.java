package com.systembackpoc.services;

import com.systembackpoc.exceptions.ExceptionsPoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systembackpoc.entities.Segments;
import com.systembackpoc.repositories.SegmentsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SegmentsService {

    @Autowired
    private SegmentsRepository repository;

    public List<Segments> findAll() { return repository.findAll(); }

    public Segments findById(Long id) {
        Optional<Segments> segment = repository.findById(id);
        return segment.orElseThrow(() -> new ExceptionsPoc("Segmento não encontrado!"));
    }
    public List<Segments> findByDescription(String description){ return repository.findByDescription(description); }

    public Segments insert(Segments segment){ return repository.save(segment); }

    public void delete(Long id){
        findById(id);
        repository.deleteById(id);
    }

    public Segments update(Segments segment) {
        Segments newSegment = findById(segment.getId());
        updateData(newSegment, segment);
        return repository.save(newSegment);
    }

    private void updateData(Segments newSegment, Segments segment) {
        newSegment.setDescription(segment.getDescription());
        newSegment.setPrice(segment.getPrice());
    }
}
