package com.systembackpoc.enums;

public enum PayType {

    C("in cash"), W("weekly"), M("monthly");

    private String paymentDesc;

    PayType(String paymentDesc){
        this.paymentDesc = paymentDesc;
    }

    public String getPaymentDesc(){
        return this.paymentDesc;
    }
}
