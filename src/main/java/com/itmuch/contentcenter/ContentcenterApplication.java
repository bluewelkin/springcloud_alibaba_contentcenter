package com.itmuch.contentcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.itmuch")
@EnableFeignClients
@EnableDiscoveryClient
public class ContentcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentcenterApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }

}
