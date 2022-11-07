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
public class Image {
    private Long id;

    private Long productId;

    private String url;

    private String desc;

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;
    
    private String blogId;

    public Image(Long productId, String url, String blogId) {
        this.productId = productId;
        this.url = url;
        this.blogId = blogId;
    }
}