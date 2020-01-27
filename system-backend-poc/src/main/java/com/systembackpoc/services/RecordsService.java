package com.systembackpoc.services;

import com.systembackpoc.entities.DTO.RecordsDTO;
import com.systembackpoc.entities.Payments;
import com.systembackpoc.entities.Records;
import com.systembackpoc.enums.PayStatus;
import com.systembackpoc.exceptions.ExceptionsPoc;
import com.systembackpoc.repositories.RecordsRepository;
import com.systembackpoc.repositories.TextsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecordsService {

    @Autowired
    private RecordsRepository repository;
    @Autowired
    private TextsRepository textsRepository;

    public List<Records> findAll(){ return repository.findAll(); }

    public Records findById(Long id){
        Optional<Records> record = repository.findById(id);
        return record.orElseThrow(() -> new ExceptionsPoc("Gravação não encontrada!"));
    }
    public List<Records> findByMoment(Date startDate, Date finalDate){
        return repository.findByMoment(startDate, finalDate);
    }
    public List<Records> findByClient(Long id){ return repository.findByClient(id); }

    public List<Records> findByPayStatus(Long status){ return repository.findByPayStatus(status); }

    public List<Records> findByStatusOpenClient(Long clientId){ return  repository.findByStatusOpenClient(clientId); }

    // POST (create)
    public Records insert(Records record){ return repository.save(record); }

    // DELETE
    public void delete(Long id){
        repository.findById(id);
        repository.deleteById(id);
    }
    // PUT (update)
    public Records update(Records record) {
        Records newRecord = findById(record.getId());
        newRecord.setPayStatus(record.getPayStatus());
        newRecord.setMoment(record.getMoment());
        newRecord.setPriceRec(record.getPriceRec());
        newRecord.setText(record.getText());
        newRecord.setUrlRec(record.getUrlRec());
        return repository.save(newRecord);
    }

}
