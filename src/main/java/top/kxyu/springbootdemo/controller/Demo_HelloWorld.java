package top.kxyu.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author KXY
 * @date Created in 2019/12/22 16:40
 */
@Controller
public class Demo_HelloWorld {
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Spring boot.";
	}

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "Hello Spring boot.";
	}
}
