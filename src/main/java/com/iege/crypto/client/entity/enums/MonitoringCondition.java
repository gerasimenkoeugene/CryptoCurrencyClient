package com.iege.crypto.client.entity.enums;

public enum MonitoringCondition {
    MORE_THEN_USD("More then defined price value in USD"),
    LESS_THEN_USD("Less then defined price value in USD");

    String description;

    MonitoringCondition(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
