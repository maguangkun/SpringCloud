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
public class APPCliend1 {
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	RestTemplate restTemplate;
    public static void main(String[] args) {
        SpringApplication.run(APPCliend1.class, args);
    }
    @RequestMapping("/dc")
    public String dc() {
    	
    	return "APPCliend1---001";
    }
    @RequestMapping("/getCliend1/{name}")
    public String getCliend1(@PathVariable String name){
    	//restTemplate.getForObject("http://EUREKACLIENT2/getCliend1/"+name,String.class);
    	return "APPCliend1服务--》"+name;
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
    	return new RestTemplate();
    }
}