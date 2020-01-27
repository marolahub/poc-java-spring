package com.systembackpoc.resources;

import com.systembackpoc.entities.DTO.PaymentsDTO;
import com.systembackpoc.entities.Payments;
import com.systembackpoc.services.ClientsService;
import com.systembackpoc.services.PaymentsService;
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
@RequestMapping(value = "/payments")
public class PaymentsResources {

    @Autowired
    private PaymentsService service;
    @Autowired
    private ClientsService clientsService;

    @GetMapping
    public ResponseEntity<List<Payments>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/full")
    public ResponseEntity<List<PaymentsDTO>> findAllPaymentsFull(){
        return ResponseEntity.ok().body(service.findAll().stream().map(obj -> new PaymentsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Payments> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @RequestMapping(value="/date", method= RequestMethod.GET)
    public ResponseEntity<List<PaymentsDTO>> findByPayDate(
            @RequestParam(value = "beginDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String finalDate){
        Date sDate = FormattUtils.convertDate(startDate, new Date(0L));
        Date fDate = FormattUtils.adjustsDateEnd(FormattUtils.convertDate(finalDate, new Date()));
        return ResponseEntity.ok().body(service.findByPayDate(sDate, fDate).stream().map(obj ->
                new PaymentsDTO(obj)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentsDTO>> findByClient(@RequestParam(value = "id") Long id){
        return ResponseEntity.ok().body(clientsService.findById(id).getListPayments().stream().map(obj ->
                new PaymentsDTO(obj)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Payments payments){
        payments = service.insert(payments);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payments.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
