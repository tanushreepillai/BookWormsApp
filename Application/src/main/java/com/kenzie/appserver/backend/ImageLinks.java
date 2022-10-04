package com.kenzie.appserver.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageLinks {
    @JsonProperty("thumbnail")
    public String thumbnail;
    @JsonProperty("smallThumbnail")
    public String smallThumbnail;
}
