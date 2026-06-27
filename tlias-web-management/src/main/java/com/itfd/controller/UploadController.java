package com.itfd.controller;

import com.itfd.pojo.Result;
import com.itfd.utils.AliyunOSSOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    /**
     * 文件在本地存储
     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("接收参数：{},{},{}",name, age, file);
//        // 获取原始文件名
//        String originalFilename = file.getOriginalFilename();
//
//        // 创建新的文件名存储(防止文件名重复)
//        // 最后一个.后即为文件名
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        // UUID能生成随机的字符串
//        String newFileName = UUID.randomUUID().toString() + extension;
//
//        // 保存文件
//        file.transferTo(new File("D:/image/" + newFileName));
//        return Result.success();
//    }

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    /**
     * 文件在阿里云中存储
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception { // MultipartFile接收前端传递的文件
        log.info("文件上传：{}", file.getOriginalFilename());

        // 将文件交给OSS存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS，url：{}", url);

        // url作为data传递给前端
        return Result.success(url);
    }
}
