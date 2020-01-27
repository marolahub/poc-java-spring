package com.systembackpoc.enums;

public enum SendStatusBalance {

    W("waiting"), S("sent"), N("needless");

    private String sendStatusBalanceDescription;

    SendStatusBalance(String sendStatus){
        this.sendStatusBalanceDescription = sendStatus;
    }

    public String getSendStatusBalanceDescription(){
        return this.sendStatusBalanceDescription;
    }
}
