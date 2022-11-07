package com.demo.blog_management.service.impl;

import com.demo.blog_management.common.AppConstants;
import com.demo.blog_management.dto.BlogDto;
import com.demo.blog_management.feign.CommonFeignService;
import com.demo.blog_management.feign.ProductFeignService;
import com.demo.blog_management.feign.UserFeignService;
import com.demo.blog_management.feign.dto.Category;
import com.demo.blog_management.feign.dto.Image;
import com.demo.blog_management.feign.dto.UploadFileResponse;
import com.demo.blog_management.feign.dto.UserIdAndNameRespDto;
import com.demo.blog_management.mapper.BlogMapper;
import com.demo.blog_management.model.BlogVo;
import com.demo.blog_management.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {

    @Value("${user-service.api-key}")
    String userServiceApiKey;

    @Autowired
    BlogMapper blogMapper;

//    @Autowired
//    private FileStorageService fileStorageService;

    @Autowired
    private CommonFeignService commonFeignService;
//    @Autowired
//    ImageService imageService;
    @Autowired
    private ProductFeignService productFeignService;
//    @Autowired
//    ProductCategoryService productCategoryService;

    @Autowired
    UserFeignService userFeignService;
//    UserMapper userMapper;

    @Override
    public int insertBlog(MultipartFile[] multipartFile, String jsonFile, String userName) throws JsonMappingException, JsonProcessingException {
        // TODO Auto-generated method stub
        List<String> listAddedImg = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            UploadFileResponse fileResponse = commonFeignService.uploadFile(file);
            listAddedImg.add(fileResponse.getFileName());
//            String name =    fileStorageService.storeFile(file);
//               listAddedImg.add(name);
        }
        String blogID = "BLOG_" + UUID.randomUUID().toString();
        BlogVo blogVo = new ObjectMapper().readValue(jsonFile, BlogVo.class);
        String apiKeyHeaderValue = AppConstants.API_KEY_PLACEHOLDER+userServiceApiKey;
        UserIdAndNameRespDto resp = userFeignService.getIdByUserName(userName,apiKeyHeaderValue);
        blogVo.setCreatedId(resp.getId());
        blogVo.setId(blogID);
        blogMapper.insertBlog(blogVo);
        for (String name : listAddedImg) {
            Image newImg = new Image(null, name, blogID);
            Image image = productFeignService.saveImage(newImg);
            if(ObjectUtils.isEmpty(image)){
                return -1;
            }
        }
        if (blogVo.getCategories() != null && blogVo.getCategories().size() > 0) {
            for (Category cate : blogVo.getCategories()) {
                Category category = productFeignService.saveCategory(cate);
                if(ObjectUtils.isEmpty(category)){
                    return -1;
                }
            }
        }

        return 0;
    }

    @Override
    public List<BlogVo> getAllBlog(BlogDto input, int currentPage, int page_size) {
        // TODO Auto-generated method stub
        int offset = (currentPage - 1) * page_size;
        page_size = offset + page_size;
        List<BlogVo> blogs = blogMapper.getBlogs(input, offset, page_size);
        for (BlogVo blogVo : blogs) {
            //TODO: use feign
            blogVo.setImages(productFeignService.getImageByBlogId(blogVo.getId()));
            blogVo.setCategories(productFeignService.getCategoriesByBlogId(blogVo.getId()));
        }
        return blogs;
    }

    @Override
    public BlogVo getBlogById(String blogId) {
        // TODO Auto-generated method stub
        return blogMapper.getBlogById(blogId);
    }

    @Override
    public int deleteBlogById(String blogId) {
        // TODO Auto-generated method stub
        return blogMapper.deleteBlogById(blogId);
    }

    @Override
    public int updateBlogById(BlogDto input) {
        // TODO Auto-generated method stub
        return blogMapper.updateBlogById(input);
    }

    @Override
    public int countAll(BlogDto input) {
        // TODO Auto-generated method stub
        return blogMapper.countAllBlogs(input);
    }

}
