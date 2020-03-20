package com.yc.index.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexAction {
	@Resource
	private RestTemplate restTemplate;
	
	
	@GetMapping("user/way")
	public String index(HttpServletRequest request) {
		return "user:"+request.getServerPort();
	}
}
