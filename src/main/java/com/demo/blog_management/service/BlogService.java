/**
* @mbg.generated
* generator on Mon Aug 29 08:55:05 GMT+07:00 2022
*/
package com.demo.blog_management.service;

import com.demo.blog_management.dto.BlogDto;
import com.demo.blog_management.model.BlogVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
	
	int insertBlog(MultipartFile[] multipartFile, String jsonFile, String userName) throws JsonMappingException, JsonProcessingException;
	List<BlogVo> getAllBlog(BlogDto input, int page, int size);
	int countAll(BlogDto input);
	BlogVo	getBlogById(String blogId);
	int deleteBlogById(String blogId);
	int updateBlogById(BlogDto input);
}