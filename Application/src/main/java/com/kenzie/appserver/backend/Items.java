package com.kenzie.appserver.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Items {
    @JsonProperty("searchInfo")
    public SearchInfo searchInfo;
    @JsonProperty("accessInfo")
    public AccessInfo accessInfo;
    @JsonProperty("saleInfo")
    public SaleInfo saleInfo;
    @JsonProperty("volumeInfo")
    public VolumeInfo volumeInfo;
    @JsonProperty("selfLink")
    public String selfLink;
    @JsonProperty("etag")
    public String etag;
    @JsonProperty("id")
    public String id;
    @JsonProperty("kind")
    public String kind;
}
