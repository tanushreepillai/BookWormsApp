package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageLinks {
    @JsonProperty("thumbnail")
    public String thumbnail;
    @JsonProperty("smallThumbnail")
    public String smallThumbnail;
}
