package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadingModes {
    @JsonProperty("image")
    private boolean image;
    @JsonProperty("text")
    private boolean text;

    public boolean getImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public boolean getText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }
}
