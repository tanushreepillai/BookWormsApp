package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndustryIdentifiers {
    @JsonProperty("identifier")
    public String identifier;
    @JsonProperty("type")
    public String type;
}
