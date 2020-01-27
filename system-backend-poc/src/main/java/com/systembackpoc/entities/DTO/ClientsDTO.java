package com.systembackpoc.entities.DTO;

import com.systembackpoc.entities.Clients;
import com.systembackpoc.entities.Payments;
import com.systembackpoc.entities.Records;
import com.systembackpoc.entities.Texts;
import com.systembackpoc.enums.PayType;
import com.systembackpoc.enums.SendStatusBalance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Boolean active;
    private Double creditValue;
    private List<Texts> listTexts = new ArrayList<>();
    private List<Payments> listPayment = new ArrayList<>();
    private PayType payType;
    private SendStatusBalance sendStatusBalance;
    private List<Records> listRecords = new ArrayList<>();

    public ClientsDTO(){}

    public ClientsDTO(Clients client){
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.listTexts = client.getListTexts();
        this.listPayment = client.getListPayments();
        this.phone = client.getPhone();
        this.active = client.getActive();
        this.creditValue = client.getCreditValue();
        this.payType = client.getPayType();
        this.sendStatusBalance = client.getSendStatusBalance();
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

    public String getEmail(){ return this.email; }
    public void setEmail(String email){ this.email = email; }

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

    public PayType getPayType(){ return this.payType; }
    public void setPayType(PayType payType){ this.payType = payType; }
    public String getPayTypeDesc(){ return this.payType.getPaymentDesc(); }

    public SendStatusBalance getSendStatusBalance(){ return this.sendStatusBalance; }
    public void setSendStatusBalance(SendStatusBalance sendStatusBalance){ this.sendStatusBalance = sendStatusBalance; }
    public String getSendStatusBalanceDesc() { return this.sendStatusBalance.getSendStatusBalanceDescription(); }

    public List<Records> getListRecords(){
        return this.listRecords;
    }
    public void setListRecords(List<Records> listRecords){ this.listRecords = listRecords; }

    public List<Texts> getListTexts() {
        return listTexts;
    }
    public void setListTexts(List<Texts> listTexts) {
        this.listTexts = listTexts;
    }

    public List<Payments> getListPayment() {
        return listPayment;
    }
    public void setListPayment(List<Payments> listPayment) {
        this.listPayment = listPayment;
    }

}
