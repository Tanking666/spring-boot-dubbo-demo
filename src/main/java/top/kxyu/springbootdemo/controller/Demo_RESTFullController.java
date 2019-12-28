package top.kxyu.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kxyu.springbootdemo.service.VideoService;

/**
 * @author KXY
 * @date Created in 2019/12/25 12:58
 */
@RestController
public class Demo_RESTFullController {

	@Autowired
	VideoService videoService;

	@RequestMapping("/rest/{id}/{id2}")
//	@RequestMapping("/rest/{id2}/{id}")
	public Object user(@PathVariable("id") String id, @PathVariable("id2") String id2) {
		System.out.println("Double ID");
		if (videoService.getVideoById(id) == null) {
			return videoService.getVideoById(id2);
		} else {
			return videoService.getVideoById(id);
		}
	}
	//若存在路由重叠将优先匹配此类注解
	//注意歧义问题
	@RequestMapping(value = "/rest/{id2}",method = RequestMethod.POST)
	public Object user(@PathVariable("id2") String id2) {
		System.out.println("ONE ID");
		return videoService.getVideoById(id2);
	}
	//@RequestMapping()不限制方法 万用

	//@DeleteMapping("/rest/abc/{id2}")
	//@RequestMapping(value = "/rest/{id2}",method = RequestMethod.DELETE)
	//用于删除 常用Get代替
	//@PutMapping("/rest/abc/{id2}")
	//@RequestMapping(value = "/rest/{id2}",method = RequestMethod.PUT)
	//常用修改
	//@GetMapping("/rest/abc/{id2}")
	//@RequestMapping(value = "/rest/{id2}",method = RequestMethod.GET)
	//get请求
	//@PostMapping("/rest/abc/{id2}")
	//@RequestMapping(value = "/rest/{id2}",method = RequestMethod.POST)
	//post请求

}
