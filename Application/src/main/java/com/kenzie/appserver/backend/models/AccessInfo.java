package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessInfo {
    @JsonProperty("quoteSharingAllowed")
    private boolean quoteSharingAllowed;
    @JsonProperty("accessViewStatus")
    private String accessViewStatus;
    @JsonProperty("webReaderLink")
    private String webReaderLink;
    @JsonProperty("pdf")
    private Pdf pdf;
    @JsonProperty("epub")
    private Epub epub;
    @JsonProperty("textToSpeechPermission")
    private String textToSpeechPermission;
    @JsonProperty("publicDomain")
    private boolean publicDomain;
    @JsonProperty("embeddable")
    private boolean embeddable;
    @JsonProperty("viewability")
    private String viewability;
    @JsonProperty("country")
    private String country;

    public boolean getQuoteSharingAllowed() {
        return quoteSharingAllowed;
    }

    public void setQuoteSharingAllowed(boolean quoteSharingAllowed) {
        this.quoteSharingAllowed = quoteSharingAllowed;
    }

    public String getAccessViewStatus() {
        return accessViewStatus;
    }

    public void setAccessViewStatus(String accessViewStatus) {
        this.accessViewStatus = accessViewStatus;
    }

    public String getWebReaderLink() {
        return webReaderLink;
    }

    public void setWebReaderLink(String webReaderLink) {
        this.webReaderLink = webReaderLink;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

    public Epub getEpub() {
        return epub;
    }

    public void setEpub(Epub epub) {
        this.epub = epub;
    }

    public String getTextToSpeechPermission() {
        return textToSpeechPermission;
    }

    public void setTextToSpeechPermission(String textToSpeechPermission) {
        this.textToSpeechPermission = textToSpeechPermission;
    }

    public boolean getPublicDomain() {
        return publicDomain;
    }

    public void setPublicDomain(boolean publicDomain) {
        this.publicDomain = publicDomain;
    }

    public boolean getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(boolean embeddable) {
        this.embeddable = embeddable;
    }

    public String getViewability() {
        return viewability;
    }

    public void setViewability(String viewability) {
        this.viewability = viewability;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
