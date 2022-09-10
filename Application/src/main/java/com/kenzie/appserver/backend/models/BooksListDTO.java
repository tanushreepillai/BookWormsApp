package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BooksListDTO {

    @JsonProperty("items")
    private List<Items> items;
    @JsonProperty("totalItems")
    private int totalItems;
    @JsonProperty("kind")
    private String kind;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
