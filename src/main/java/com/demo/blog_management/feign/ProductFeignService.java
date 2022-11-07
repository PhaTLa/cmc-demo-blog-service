package com.demo.blog_management.feign;

import com.demo.blog_management.feign.dto.Category;
import com.demo.blog_management.feign.dto.Image;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("product-management")
public interface ProductFeignService {

//    @GetMapping("/api/product/{id}")
//    Product getProductById(@PathVariable("id") Long id);

    @PostMapping("/api/save-image")
    Image saveImage(@RequestBody Image image);

    @PostMapping("/api/save-category")
    Category saveCategory(@RequestBody Category cat);

    @GetMapping("/api/get-image")
    List<Image> getImageByBlogId(@RequestParam("blogId") String blogId);

    @GetMapping("/api/get-categories")
    List<Category> getCategoriesByBlogId(@RequestParam("blogId") String blogId);
}
