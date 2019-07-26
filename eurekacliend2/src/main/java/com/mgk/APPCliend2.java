package com.mgk;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class APPCliend2 {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;
    public static void main(String[] args) {
        SpringApplication.run(APPCliend2.class, args);
    }
    @RequestMapping("/dc")
    public String dc() {
    	return restTemplate.getForObject("http://EUREKACLIENT1/dc", String.class);
    }
    @RequestMapping("/getCliend1/{name}")
    public String getCliend1(@PathVariable String name){
    	String forObject = restTemplate.getForObject("http://EUREKACLIENT1/getCliend1/"+name,String.class);
    	return forObject;
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
    	return new RestTemplate();
    }
}