package com.systembackpoc.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.systembackpoc.enums.PayStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "t_records")
public class Records implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date moment;
    private String urlRec;
    private Double priceRec;
    private Double paidValue;
    private PayStatus payStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "text_id", referencedColumnName = "id")
    private Texts text;
    @JsonIgnore
    @ManyToMany(mappedBy = "recordsSet")
    private Set<Payments> paymentsSet = new HashSet<>();

    public Records(){}

    public Records(Long id,
                   Date moment,
                   String urlRec,
                   Double paidValue,
                   PayStatus payStatus,
                   Texts text) {
        this.id = id;
        this.moment = moment;
        this.urlRec = urlRec;
        this.paidValue = paidValue;
        this.payStatus = payStatus;
        this.text = text;
        this.priceRec = text.getPrice();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getMoment() {
        return moment;
    }
    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public String getUrlRec() {
        return urlRec;
    }
    public void setUrlRec(String urlRec) {
        this.urlRec = urlRec;
    }

    public Double getPriceRec() {
        return priceRec;
    }
    public void setPriceRec(Double priceRec) {
        this.priceRec = priceRec;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public Double getPaidValue() { return paidValue; }
    public void setPaidValue(Double paidValue) { this.paidValue = paidValue; }

    public Texts getText() {
        return text;
    }
    public void setText(Texts text) { this.text = text; }

    public Set<Payments> getPaymentsSet(){ return this.paymentsSet; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Records)) return false;
        Records records = (Records) o;
        return Objects.equals(getId(), records.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
