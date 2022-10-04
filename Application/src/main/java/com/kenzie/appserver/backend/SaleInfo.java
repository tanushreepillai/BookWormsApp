package com.kenzie.appserver.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleInfo {
    @JsonProperty("isEbook")
    public boolean isEbook;
    @JsonProperty("saleability")
    public String saleability;
    @JsonProperty("country")
    public String country;
}
