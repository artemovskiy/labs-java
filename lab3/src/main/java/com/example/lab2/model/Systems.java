package com.example.lab2.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Systems {

    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Management System");

    private final String fullName;

    Systems(String fullName) {
        this.fullName = fullName;
    }
    @JsonValue
    public String getShortName() {
        return this.name();
    }
}