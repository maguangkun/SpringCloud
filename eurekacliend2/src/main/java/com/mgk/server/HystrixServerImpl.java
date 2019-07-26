package com.mgk.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HystrixServerImpl {
	@Autowired
	RestTemplate restTemplate;
	@HystrixCommand(fallbackMethod = "hystrixError")//如果服务出错回调hystrixError方法 ps:（必须返回参数，参数数量一致）
	public String getHi(){
		return restTemplate.getForObject("http://HYSTRIXSERVER/dc", String.class);
	}
	public String hystrixError() {
		 
		return "sorryServerError";
	}

}
