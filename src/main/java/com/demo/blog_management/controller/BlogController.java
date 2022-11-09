package com.demo.blog_management.controller;

import com.demo.blog_management.common.ResponseVo;
import com.demo.blog_management.dto.BlogDto;
import com.demo.blog_management.model.BlogVo;
import com.demo.blog_management.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/")
public class BlogController {
	
	@Autowired
    BlogService blogService;

    @GetMapping(value = "/blogs")
    public ResponseEntity<ResponseVo> getAllBlogsUser(@RequestParam(value = "page") int currentPage,
                                                      @RequestParam(value = "size") int pageSize, BlogDto blogDto){
    	ResponseVo resVo = new ResponseVo("OK");
    	resVo.setVoList(blogService.getAllBlog(blogDto, currentPage, pageSize));
    	resVo.setTotal(blogService.countAll(blogDto));
        return new ResponseEntity<>(resVo,HttpStatus.OK);
    }
    
    @GetMapping(value = "/admin/blogs")
    public ResponseEntity<ResponseVo> getAllBlogsAdmin(@RequestParam(value = "page") int currentPage,
                                             @RequestParam(value = "size") int pageSize, BlogDto blogDto){
    	ResponseVo resVo = new ResponseVo("OK");
    	resVo.setVoList(blogService.getAllBlog(blogDto, currentPage, pageSize));
    	resVo.setTotal(blogService.countAll(blogDto));
        return new ResponseEntity<>(resVo,HttpStatus.OK);
    }
    
    @GetMapping(value = "/blog/{id}")
    public ResponseEntity<BlogVo> getAllProductsUser(@PathVariable(value = "id") String blogId){
        return new ResponseEntity<>(blogService.getBlogById(blogId),HttpStatus.OK);
    }
    
    @PostMapping(value="/admin/blog/add")
    public ResponseEntity<ResponseVo> insertBlog(@RequestParam(value = "files", required = false) MultipartFile[] multipartFile,
            String jsonFile, HttpServletRequest request) throws JsonMappingException, JsonProcessingException{
    	blogService.insertBlog(multipartFile, jsonFile, request.getHeader("username"));
    	return new ResponseEntity<>(new ResponseVo("Created"), HttpStatus.CREATED);
    }
}
