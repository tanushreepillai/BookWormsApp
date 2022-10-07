package com.kenzie.appserver.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadingModes {
    @JsonProperty("image")
    public boolean image;
    @JsonProperty("text")
    public boolean text;
}
