package com.systembackpoc.resources;

import com.systembackpoc.entities.DTO.SegmentsDTO;
import com.systembackpoc.entities.Segments;
import com.systembackpoc.services.SegmentsService;
import com.systembackpoc.utils.FormattUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/segments")
public class SegmentsResource {

    @Autowired
    private SegmentsService service;

    @GetMapping
    public ResponseEntity<List<Segments>> findAll(){
        List<Segments> listSegments = service.findAll();
        return ResponseEntity.ok().body(listSegments);
    }

    @GetMapping(value="/full")
    public ResponseEntity<List<SegmentsDTO>> findAllSegmentsFull(){
        List<Segments> listSegments = service.findAll();
        return ResponseEntity.ok().body(listSegments.stream().map(obj ->
                new SegmentsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Segments> findById(@PathVariable Long id) {
        Segments segment = service.findById(id);
        return ResponseEntity.ok().body(segment);
    }

    @RequestMapping(value="/description", method= RequestMethod.GET)
    public ResponseEntity<List<SegmentsDTO>> findByDescription(
            @RequestParam(value = "desc", defaultValue = "") String desc){
        desc = FormattUtils.decodeParam(desc);
        List<Segments> segmentsList = service.findByDescription(desc);
        return ResponseEntity.ok().body(segmentsList.stream().map(obj ->
                new SegmentsDTO(obj)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Segments segment){
        segment = service.insert(segment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(segment.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Segments segment, @PathVariable Long id){
        segment.setId(id);
        service.update(segment);
        return ResponseEntity.noContent().build();
    }
}
