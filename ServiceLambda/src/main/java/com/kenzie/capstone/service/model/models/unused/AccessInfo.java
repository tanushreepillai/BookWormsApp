package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessInfo {
    @JsonProperty("quoteSharingAllowed")
    public boolean quoteSharingAllowed;
    @JsonProperty("accessViewStatus")
    public String accessViewStatus;
    @JsonProperty("webReaderLink")
    public String webReaderLink;
    @JsonProperty("pdf")
    public Pdf pdf;
    @JsonProperty("epub")
    public Epub epub;
    @JsonProperty("textToSpeechPermission")
    public String textToSpeechPermission;
    @JsonProperty("publicDomain")
    public boolean publicDomain;
    @JsonProperty("embeddable")
    public boolean embeddable;
    @JsonProperty("viewability")
    public String viewability;
    @JsonProperty("country")
    public String country;
}
