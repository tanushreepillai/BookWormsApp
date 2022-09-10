package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
//    @JsonProperty("searchInfo")
//    private SearchInfo searchInfo;
//    @JsonProperty("accessInfo")
//    private AccessInfo accessInfo;
//    @JsonProperty("saleInfo")
//    private SaleInfo saleInfo;
    @JsonProperty("volumeInfo")
    private Book book;
    @JsonProperty("selfLink")
    private String selfLink;
//    @JsonProperty("etag")
//    private String etag;
//    @JsonProperty("id")
//    private String id;
//    @JsonProperty("kind")
//    private String kind;

//    public SearchInfo getSearchInfo() {
//        return searchInfo;
//    }
//
//    public void setSearchInfo(SearchInfo searchInfo) {
//        this.searchInfo = searchInfo;
//    }

//    public AccessInfo getAccessInfo() {
//        return accessInfo;
//    }
//
//    public void setAccessInfo(AccessInfo accessInfo) {
//        this.accessInfo = accessInfo;
//    }

//    public SaleInfo getSaleInfo() {
//        return saleInfo;
//    }
//
//    public void setSaleInfo(SaleInfo saleInfo) {
//        this.saleInfo = saleInfo;
//    }

    public Book getVolumeInfo() {
        return book;
    }

    public void setVolumeInfo(Book volumeInfo) {
        this.book = volumeInfo;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

//    public String getEtag() {
//        return etag;
//    }
//
//    public void setEtag(String etag) {
//        this.etag = etag;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getKind() {
//        return kind;
//    }
//
//    public void setKind(String kind) {
//        this.kind = kind;
//    }
}
