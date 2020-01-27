package com.systembackpoc.enums;

public enum PayStatus {

    P("paid"), U("unpaid"), O("opened");
    private String descStatus;

    PayStatus(String descStatus){
        this.descStatus = descStatus;
    }
    public String getDescPayStatus(){
        return this.descStatus;
    }
}
