package com.demo.blog_management.model;

import com.demo.blog_management.common.BaseVo;
import com.demo.blog_management.feign.dto.Category;
import com.demo.blog_management.feign.dto.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class BlogVo extends BaseVo {
	private String id;
	private String userName;
	private String title;
	private String url;
	private String description;
	private String delYn;
	private Date createdDtm;
	private Long createdId;
	private Date updatedDtm;
	private Long updatedId;
	private List<Image> images;
	private List<Category> categories;
}
