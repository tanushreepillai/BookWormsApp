package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksData {
    // @JsonProperty("sampleId");
    private final String imageLink;
    //  @JsonProperty("imageLinkId");
    private final String description;
    //  @JsonProperty("imageLinkId");
    private final String author;
    //  @JsonProperty("imageLinkId");
    private final String title;

    public BooksData(String imageLink,
                     String description,
                     String author,
                     String title) {
        this.imageLink = imageLink;
        this.description = description;
        this.author = author;
        this.title = title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

}
