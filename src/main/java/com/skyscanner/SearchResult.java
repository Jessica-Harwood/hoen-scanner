package com.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {

    private String city;
    private String kind;
    private String title;

    public SearchResult() {
    }

    public SearchResult(String city, String kind, String title) {
        this.city = city;
        this.kind = kind;
        this.title = title;
    }

    @JsonProperty
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}