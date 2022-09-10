package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pdf {
    @JsonProperty("acsTokenLink")
    private String acsTokenLink;
    @JsonProperty("isAvailable")
    private boolean isAvailable;

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
