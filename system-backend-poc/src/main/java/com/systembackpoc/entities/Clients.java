package com.systembackpoc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.systembackpoc.enums.PayType;
import com.systembackpoc.enums.SendStatusBalance;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_clients")
public class Clients implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Boolean active;
    private Double creditValue;
    private PayType payType;
    private SendStatusBalance sendStatusBalance;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Texts> listTexts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Payments> listPayments = new ArrayList<>();

    public Clients() { }

    public Clients(Long id,
                   String name,
                   String email,
                   String phone,
                   Boolean active,
                   Double creditValue,
                   PayType payType,
                   SendStatusBalance sendStatusBalance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.creditValue = creditValue;
        this.payType = payType;
        this.sendStatusBalance = sendStatusBalance;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getCreditValue(){ return this.creditValue; }
    public void setCreditValue(Double creditValue){ this.creditValue = creditValue; }

    public PayType getPayType() {
        return payType;
    }
    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public SendStatusBalance getSendStatusBalance() {
        return sendStatusBalance;
    }
    public void setSendStatusBalance(SendStatusBalance sendStatusBalance) { this.sendStatusBalance = sendStatusBalance; }

    public List<Texts> getListTexts(){ return this.listTexts; }

    public List<Payments> getListPayments(){ return this.listPayments; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clients)) return false;
        Clients clients = (Clients) o;
        return getId().equals(clients.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
