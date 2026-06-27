package com.itfd.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 阿里云配置参数实体类
 */
@Component // 交给IOC容器，成为bean对象，名字默认是首字母小写
@ConfigurationProperties(prefix = "aliyun.oss") // 将yaml文件中aliyun.oss的参数信息给=赋值给以下参数
public class AliyunOSSPoperties {
    private String endpoint;
    private String region;
    private String bucket;

    @Override
    public String toString() {
        return "AliyunOSSPoperties{" +
                "endpoint='" + endpoint + '\'' +
                ", region='" + region + '\'' +
                ", bucket='" + bucket + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AliyunOSSPoperties that = (AliyunOSSPoperties) o;
        return Objects.equals(endpoint, that.endpoint) && Objects.equals(region, that.region) && Objects.equals(bucket, that.bucket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endpoint, region, bucket);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
