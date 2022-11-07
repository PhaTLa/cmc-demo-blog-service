package com.demo.blog_management.feign;

import com.demo.blog_management.feign.dto.UserIdAndNameRespDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-management-service")
public interface UserFeignService {

    @GetMapping("/api/get-id/{username}")
    UserIdAndNameRespDto getIdByUserName(@PathVariable("username") String username, @RequestHeader("api_key") String apiKey);
}
