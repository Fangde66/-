package com.itfd.utils;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.models.PutObjectResult;
import com.aliyun.sdk.service.oss2.transport.BinaryData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 将文件上传到阿里云
 */
//@Component // 交给IOC容器管理，以后可以通过注解自动注入
public class AliyunOSSOperator {
    // 方法一
//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint; // 华北2（北京）
//    @Value("${aliyun.oss.region}")
//    private String region;        // 你的区域
//    @Value("${aliyun.oss.bucket}")
//    private String bucket;       // 你的Bucket

    // 方法二：
//    @Autowired
//    AliyunOSSPoperties aliyunOSSPoperties;

    AliyunOSSPoperties aliyunOSSPoperties;
    public AliyunOSSOperator(AliyunOSSPoperties aliyunOSSPoperties) {
        this.aliyunOSSPoperties = aliyunOSSPoperties;
    }

    /**
     *
     * @param content 文件内容的字节数组
     * @param originalFileName 文件的原始名称
     * @return 存入到阿里云后可以下载的url地址（需要当作请求体data数据给前端）
         */
    public String upload(byte[] content, String originalFileName) throws Exception{
        String bucket = aliyunOSSPoperties.getBucket();
        String endpoint = aliyunOSSPoperties.getEndpoint();
        String region = aliyunOSSPoperties.getRegion();
        // 1.构建新的文件名字
        // 获取当前系统日期的字符串，格式为 yyyy/MM
        // 目的：在阿里云中根据日期创建目录结构
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        // 生成一个不重复的文件名（UUID）
        String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(region);

        if (endpoint != null) {
            clientBuilder.endpoint(endpoint);
        }

        try (OSSClient client = clientBuilder.build()) {

            PutObjectResult result = client.putObject(PutObjectRequest.newBuilder()
                    .bucket(bucket)
                    .key(objectName)
                    .body(BinaryData.fromBytes(content))
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

        // 拼接所得就是阿里云生成的rul地址
        return endpoint.split("//")[0] + "//" + bucket + "." + endpoint.split("//")[1] + "/" + objectName;
    }
}
