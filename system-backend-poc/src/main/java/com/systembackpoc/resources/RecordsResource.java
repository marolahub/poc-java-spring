package com.systembackpoc.resources;

import com.systembackpoc.entities.DTO.RecordsDTO;
import com.systembackpoc.entities.Records;
import com.systembackpoc.services.ClientsService;
import com.systembackpoc.services.RecordsService;
import com.systembackpoc.services.TextsService;
import com.systembackpoc.utils.FormattUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/records")
public class RecordsResource {

    @Autowired
    private RecordsService service;
    @Autowired
    private TextsService textsService;
    @Autowired
    private ClientsService clientsService;

    @GetMapping
    public List<Records> findAll(){ return service.findAll(); }

    @GetMapping(value = "/full")
    public ResponseEntity<List<RecordsDTO>> findAllRecordsFull(){
        List<Records> listRecords = findAll();
        return ResponseEntity.ok().body(listRecords.stream().map(obj ->
                new RecordsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Records> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @RequestMapping(value="/moment", method= RequestMethod.GET)
    public ResponseEntity<List<RecordsDTO>> findByMoment(
            @RequestParam(value = "beginDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String finalDate){
        Date sDate = FormattUtils.convertDate(startDate, new Date(0L));
        Date fDate = FormattUtils.adjustsDateEnd(FormattUtils.convertDate(finalDate, new Date()));
        List<Records> listRecords = service.findByMoment(sDate, fDate);
        return ResponseEntity.ok().body(listRecords.stream().map(obj ->
                new RecordsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public ResponseEntity<List<RecordsDTO>> findByText(@RequestParam(value = "slug", defaultValue = "") String slug){
        return ResponseEntity.ok().body(textsService.findBySlug(FormattUtils.decodeParam(slug)).getRecords().stream().map(obj ->
                new RecordsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ResponseEntity<List<RecordsDTO>> findByClient(
            @RequestParam(value = "id", defaultValue = "") Long id){
        return ResponseEntity.ok().body(service.findByClient(id).stream().map(obj ->
                new RecordsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/pay_status", method = RequestMethod.GET)
    public ResponseEntity<List<RecordsDTO>> findByPayStatus(
            @RequestParam(value = "status", defaultValue = "") Long status){
        return ResponseEntity.ok().body(service.findByPayStatus(status).stream().map(obj ->
                new RecordsDTO(obj)).collect(Collectors.toList()));
    }

//======================================================================================================================
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Records record){
        record = service.insert(record);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(record.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Records record, @PathVariable Long id){
        record.setId(id);
        service.update(record);
        return ResponseEntity.noContent().build();
    }
}
