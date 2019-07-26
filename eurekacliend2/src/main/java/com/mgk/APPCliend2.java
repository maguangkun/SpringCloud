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
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mgk.server.HystrixServerImpl;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableEurekaClient
@SpringBootApplication
@EnableHystrix//开启服务降级和熔断
@RestController
public class APPCliend2 {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private HystrixServerImpl hystrixServerImpl;
    public static void main(String[] args) {
        SpringApplication.run(APPCliend2.class, args);
    }

    @RequestMapping(value = "/dc/{name}",method = RequestMethod.GET)
    public String dc(@PathVariable String name) {
//    	return restTemplate.getForObject("http://EUREKACLIENT1/dc", String.class);
    	return hystrixServerImpl.getHi();
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