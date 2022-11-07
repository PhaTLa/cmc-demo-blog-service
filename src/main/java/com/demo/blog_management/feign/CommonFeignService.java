package com.demo.blog_management.feign;

import com.demo.blog_management.feign.dto.UploadFileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("common-service")
public interface CommonFeignService {

//    @RequestLine("POST /api/image/uploadFile")
    @PostMapping(value = "/api/image/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadFileResponse uploadFile(@RequestPart("file") MultipartFile file);
}
