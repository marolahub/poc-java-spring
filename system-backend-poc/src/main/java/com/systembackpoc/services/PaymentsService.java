package com.systembackpoc.services;

import com.systembackpoc.entities.Clients;
import com.systembackpoc.entities.Payments;
import com.systembackpoc.entities.Records;
import com.systembackpoc.enums.PayStatus;
import com.systembackpoc.exceptions.ExceptionsPoc;
import com.systembackpoc.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentsService {

    @Autowired
    private PaymentsRepository repository;

    @Autowired
    private RecordsService recordsService;

    @Autowired
    private ClientsService clientsService;

    public List<Payments> findAll(){ return repository.findAll(); }
    public Payments findById(Long id){
        Optional<Payments> client = repository.findById(id);
        return client.orElseThrow(() -> new ExceptionsPoc("Pagamento não encontrado!"));
    }
    public List<Payments> findByPayDate(Date startDate, Date finalDate){
        return repository.findByPayDate(startDate, finalDate);
    }

    // POST (create)
    public Payments insert(Payments payment){
        Payments newPayment = repository.save(payment);
        setPaymentsRecords(newPayment);
        return newPayment;
    }

    // DELETE
    public void delete(Long id){
        repository.findById(id);
        repository.deleteById(id);
    }
    // PUT (update)
    public Payments update(Payments payment) {
        Payments newPayment = findById(payment.getId());
        newPayment.setPayValue(payment.getPayValue());
        newPayment.setPayDate(payment.getPayDate());
        newPayment.setClient(payment.getClient());
        return repository.save(newPayment);
    }

    private void setPaymentsRecords(Payments payments){
        List<Records> listRecords = recordsService.findByStatusOpenClient(payments.getClient().getId());
        // se possui registros para o pagamento
        if(listRecords.size()>0) {
            // valor pago + crédito do cliente
            Clients client = clientsService.findById(payments.getClient().getId());

            double openValue = 0.0;
            double payValue = payments.getPayValue();
            payValue += client.getCreditValue();
            Boolean updateCreditClient = false;

            for (Records records : listRecords) {
                if (payValue > 0) {
                    updateCreditClient = true;
                    openValue = records.getPriceRec() - (records.getPaidValue());
                    if(payValue>openValue){ updatePayStatusRecords(records, PayStatus.P, records.getPriceRec()); }
                    else { updatePayStatusRecords(records, PayStatus.O, (openValue<0?0:openValue)); }
                    payments.getRecordsSet().add(records);
                    payValue -= openValue;
                } else { break; }
            }
            if(updateCreditClient) {
                updateCreditClient(client, payValue);
                update(payments);
            }
        }
    }

    private void updatePayStatusRecords(Records record, PayStatus payStatus, Double payValue){
        record.setPayStatus(payStatus);
        record.setPaidValue(payValue);
        recordsService.update(record);
    }

    private void updateCreditClient(Clients client, Double credit){
        client.setCreditValue((credit<0)? 0: credit);
        clientsService.update(client);
    }
}
