package com.example.pullpointdev.wallet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;


public enum TransactionType {
    INPUT ("INPUT"),
    OUTPUT ("OUTPUT"),
    TRANSFER ("TRANSFER");

    @Getter
    @Setter
    private String type;

    TransactionType(String type){
        this.type = type;
    }
}
