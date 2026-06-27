package com.itfd;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
import com.aliyun.sdk.service.oss2.models.*;
import com.aliyun.sdk.service.oss2.transport.BinaryData;

import java.io.File;
import java.nio.file.Files;

/**
 * 阿里云简单文件上传
 */
public class PutObject {

    private static void execute() {
        String endpoint = "http://oss-cn-beijing.aliyuncs.com"; // 华北2（北京）
        String region = "cn-beijing";        // 你的区域
        String bucket = "java-5-21";       // 你的Bucket
        String key = "001.png";       // 上传后的路径(上传后的文件名字)

        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(region);

        if (endpoint != null) {
            clientBuilder.endpoint(endpoint);
        }

        try (OSSClient client = clientBuilder.build()) {

            File file = new File("D:\\image\\dfe600ec-2707-44f5-acd2-6833aa95cd24.png");
            byte[] bytes = Files.readAllBytes(file.toPath());

            PutObjectResult result = client.putObject(PutObjectRequest.newBuilder()
                    .bucket(bucket)
                    .key(key)
                    .body(BinaryData.fromBytes(bytes))
                    .build());

            System.out.printf("status code:%d, request id:%s, eTag:%s\n",
                    result.statusCode(), result.requestId(), result.eTag());

        } catch (Exception e) {
            //If the exception is caused by ServiceException, detailed information can be obtained in this way.
            // ServiceException se = ServiceException.asCause(e);
            // if (se != null) {
            //    System.out.printf("ServiceException: requestId:%s, errorCode:%s\n", se.requestId(), se.errorCode());
            //}
            System.out.printf("error:\n%s", e);
        }
    }

    public static void main(String[] args) {
        execute();
    }
}
