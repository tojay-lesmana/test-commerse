package org.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class DefaultController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping
	@ResponseBody
	public String index(){
		return null;
	}
	
}
