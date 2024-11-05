package com.study.lease.web.admin.controller.apartment;

import com.study.lease.common.result.Result;
import com.study.lease.web.admin.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Ryan Yan
 * @Since 2024/11/1 17:42
 */
@Tag(name = "文件管理")
@RestController
@RequestMapping("/admin/file")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @Operation(summary = "上传文件")
    @PostMapping("upload")
    public Result<String> upload(@RequestParam MultipartFile multipartFile){
        String url = fileService.upload(multipartFile);
        return Result.ok(url);
    }



}
