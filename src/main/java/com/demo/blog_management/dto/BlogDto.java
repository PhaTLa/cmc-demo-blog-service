package com.demo.blog_management.dto;

import com.demo.blog_management.model.BlogVo;
import lombok.Data;

import java.util.List;

@Data
public class BlogDto{
	private String title;
	private String description;
	private List<BlogVo> blogVos;
}
