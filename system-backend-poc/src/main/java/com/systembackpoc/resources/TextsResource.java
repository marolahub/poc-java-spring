package com.systembackpoc.resources;

import com.systembackpoc.entities.DTO.TextsDTO;
import com.systembackpoc.entities.Texts;
import com.systembackpoc.services.ClientsService;
import com.systembackpoc.services.SegmentsService;
import com.systembackpoc.services.TextsService;
import com.systembackpoc.utils.FormattUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/texts")
public class TextsResource {

    @Autowired
    private TextsService service;
    @Autowired
    private ClientsService clientsService;
    @Autowired
    private SegmentsService segmentsService;

    @GetMapping
    public ResponseEntity<List<Texts>> findAll(){
        List<Texts> textList = service.findAll();
        return ResponseEntity.ok().body(textList);
    }

    @GetMapping(value = "/full")
    public ResponseEntity<List<TextsDTO>> findAllTextsFull(){
        List<Texts> textList = service.findAll();
        return ResponseEntity.ok().body(textList.stream().map(obj ->
                new TextsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Texts> findById(@PathVariable Long id){
        Texts text = service.findById(id);
        return ResponseEntity.ok().body(text);
    }

    @RequestMapping(value = "/text", method= RequestMethod.GET)
    public ResponseEntity<Texts> findBySlug(
            @RequestParam(value = "slug", defaultValue = "") String slug){
        Texts text = service.findBySlug(FormattUtils.decodeParam(slug));
        return ResponseEntity.ok().body(text);
    }

    @RequestMapping(value = "/client", method= RequestMethod.GET)
    public ResponseEntity<List<TextsDTO>> findByClient(
            @RequestParam(value = "id", defaultValue = "") Long client){
        return ResponseEntity.ok().body(clientsService.findById(client).getListTexts().stream().map(obj ->
                new TextsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/segment", method= RequestMethod.GET)
    public ResponseEntity<List<TextsDTO>> findBySegment(
            @RequestParam(value = "id", defaultValue = "") Long segment){
        return ResponseEntity.ok().body(segmentsService.findById(segment).getListTexts().stream().map(obj ->
                new TextsDTO(obj)).collect(Collectors.toList()));
    }
    // ===========================================================================
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Texts text){
        text = service.insert(text);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(text.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Texts text, @PathVariable Long id){
        text.setId(id);
        service.update(text);
        return ResponseEntity.noContent().build();
    }
}
