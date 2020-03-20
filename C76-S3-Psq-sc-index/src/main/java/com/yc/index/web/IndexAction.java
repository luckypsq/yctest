package com.yc.index.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class IndexAction {
	@Resource
	private RestTemplate restTemplate;

	/*
	 * @GetMapping("user/way") public String index(HttpServletRequest request) {
	 * return "user:"+request.getServerPort(); }
	 */

	@Resource
	private IUserAction iua;
	@GetMapping("fuser/way") public String fuser() {
		return iua.user(); 
	}
	 

	@GetMapping("user/way")
	@HystrixCommand(fallbackMethod = "wayHystirx")
	public String user() {
		String url = "http://sc-user/user/way";
		String ret = restTemplate.getForObject(url, String.class);
		return ret;
	}

	public String wayHystirx() {
		return "服务器熔断，服务降级了";
	}
}
