package com.systembackpoc.entities.DTO;

import com.systembackpoc.entities.Clients;
import com.systembackpoc.entities.Payments;
import com.systembackpoc.entities.Records;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PaymentsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date payDate;
    private Double payValue;
    private Clients clients;
    private Set<Records> recordsSet;

    public PaymentsDTO(){}
    public PaymentsDTO(Payments payments){
        this.id = payments.getId();
        this.payDate = payments.getPayDate();
        this.payValue = payments.getPayValue();
        this.clients = payments.getClient();
        this.recordsSet = payments.getRecordsSet();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Double getPayValue() {
        return payValue;
    }

    public void setPayValue(Double payValue) {
        this.payValue = payValue;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public Set<Records> getRecordsSet() {
        return recordsSet;
    }
}
