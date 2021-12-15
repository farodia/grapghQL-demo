package com.graphql.graphqldemo.dto;

public class Show {
    private final String title;
    private final Integer releaseYear;

    public Show(String title, Integer releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }
}
