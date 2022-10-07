package com.kenzie.appserver.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndustryIdentifiers {
    @JsonProperty("identifier")
    public String identifier;
    @JsonProperty("type")
    public String type;
}
