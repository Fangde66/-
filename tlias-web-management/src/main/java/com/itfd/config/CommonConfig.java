package com.itfd.config;

import com.itfd.utils.AliyunOSSOperator;
import com.itfd.utils.AliyunOSSPoperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSPoperties aliyunOSSPoperties) {
        return new AliyunOSSOperator(aliyunOSSPoperties);
    }
}
