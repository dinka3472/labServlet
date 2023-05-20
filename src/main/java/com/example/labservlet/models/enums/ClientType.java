package com.example.labservlet.models.enums;

public enum ClientType {
    JURIDICAL("Юридическое лицо"),
    PHYSICAL("Физическое лицо");

    private String displayName;

    ClientType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
