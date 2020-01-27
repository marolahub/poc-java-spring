package com.systembackpoc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "t_payments")
public class Payments implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date payDate;
    private Double payValue;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;

    @ManyToMany
    @JoinTable(name = "t_records_payments",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "record_id"))
    private Set<Records> recordsSet = new HashSet<>();

    public Payments(){}
    public Payments(Long id, Date payDate, Double payValue, Clients client) {
        this.id = id;
        this.payDate = payDate;
        this.payValue = payValue;
        this.client = client;
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

    public Clients getClient() {
        return this.client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Set<Records> getRecordsSet(){ return recordsSet; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payments)) return false;
        Payments payments = (Payments) o;
        return getId().equals(payments.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
