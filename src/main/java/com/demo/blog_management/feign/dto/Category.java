package com.demo.blog_management.feign.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private Long id;

    private String name;

    private String url;

    private String desc;

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;

    public Category(String name) {
        this.name = name;
    }
}