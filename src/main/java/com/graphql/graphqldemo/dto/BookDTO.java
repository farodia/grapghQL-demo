package com.graphql.graphqldemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookDTO {
    private String isn;
    private String title;
    private String summary;
}
