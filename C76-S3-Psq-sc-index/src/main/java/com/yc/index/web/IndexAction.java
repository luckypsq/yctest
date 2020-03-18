package com.yc.index.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexAction {
	
	@GetMapping("index/way")
	public String index() {
		return "index";
	}
}
