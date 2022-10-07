package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadingModes {
    @JsonProperty("image")
    public boolean image;
    @JsonProperty("text")
    public boolean text;
}
