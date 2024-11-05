package com.study.lease.web.admin.service.impl;

import com.study.lease.common.minio.MinioProperties;
import com.study.lease.web.admin.service.FileService;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @Author Ryan Yan
 * @Since 2024/11/4 17:19
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioProperties minioProperties;

    @Autowired
    private MinioClient minioClient;


    @Override
    public String upload(MultipartFile file) {
        try {
            //获取bucket
            String bucketName = minioProperties.getBucketName();
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            //创建bucket
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                //设置bucket权限
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName)
                        .config(createBucketPolicyConfig(bucketName))
                        .build());
            }
            //file保存到minio
            String fileName = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                    + "/"
                    + UUID.randomUUID() + "-" + file.getOriginalFilename();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperties.getBucketName())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .object(fileName)
                            .contentType(file.getContentType())
                            .build()
            );
            //返回上传文件的url
            return String.join("/", minioProperties.getEndpoint(), bucketName, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    private String createBucketPolicyConfig(String bucketName) {
        return """
                { 
                    "Statement" : [ {
                     "Action" : "s3:GetObject",
                      "Effect" : "Allow", 
                      "Principal" : "*",
                      "Resource" : "arn:aws:s3:::%s/*" 
                     } ], 
                     "Version" : "2012-10-17"
                }
                """.formatted(bucketName);

    }

}
