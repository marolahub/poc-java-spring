package com.systembackpoc.entities.DTO;
import com.systembackpoc.entities.Payments;
import com.systembackpoc.entities.Records;
import com.systembackpoc.entities.Texts;
import com.systembackpoc.enums.PayStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class RecordsDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date moment;
    private String urlRec;
    private Double priceRec;
    private Double paidValue;
    private PayStatus payStatus;
    private Texts text;
    private Set<Payments> paymentsSet;

    public RecordsDTO(){}
    public RecordsDTO(Records records){
        this.id = records.getId();
        this.moment = records.getMoment();
        this.urlRec = records.getUrlRec();
        this.priceRec = records.getPriceRec();
        this.paidValue = records.getPaidValue();
        this.payStatus = records.getPayStatus();
        this.text = records.getText();
        this.paymentsSet = records.getPaymentsSet();
    }

    public Long getId() { return id; }
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

    public Double getPaidValue() {
        return paidValue;
    }
    public void setPaidValue(Double paidValue) {
        this.paidValue = paidValue;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public Texts getText() {
        return text;
    }
    public void setText(Texts text) {
        this.text = text;
    }

    public Set<Payments> getPaymentsSet() {
        return paymentsSet;
    }
}
