package com.gyf.shopping.controller;

import com.gyf.shopping.pojo.ResBean;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Api(tags = "UploadController", description = "文件上传服务接口")
public class UploadController {

    /**
     * # MinIO对象存储相关配置
     * minio:
     *   endpoint:  http://192.168.1.5:9000 #MinIO服务所在地址
     *   bucketName: mall #存储桶名称
     *   accessKey: minioadmin #访问的key
     *   secretKey: minioadmin #访问的秘钥
     */
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @PostMapping("/uploadFile")
    public ResBean uploadFile(@ApiParam("文件上传对象file") MultipartFile file) {
        System.out.println("进入文件上传");
        try {
            //先创建minio的文件上传客户端.
            
            MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
            //判断是否存在桶
            boolean isExist = minioClient.bucketExists(bucketName);
            if (!isExist) {
                minioClient.makeBucket(bucketName);
                minioClient.setBucketPolicy(bucketName, "*", PolicyType.READ_ONLY);
            }

            //设置桶下存储对象的名称
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String fileName = file.getOriginalFilename();
            String objName = sdf.format(new Date()) + "/" + fileName;

            //把存储对象对象上传至存储桶
            minioClient.putObject(bucketName, objName, file.getInputStream(), file.getContentType());
            System.out.println("文件上传成功");

            //文件的访问地址
            String objUrl = minioClient.getObjectUrl(bucketName, objName);
            System.out.println(objUrl);

            return ResBean.ok(objUrl);


        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("文件上传失败!");
        }


    }
}
