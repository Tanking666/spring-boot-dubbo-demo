package top.kxyu.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kxyu.springbootdemo.service.VideoService;

/**
 * @author KXY
 * @date Created in 2019/12/23 19:56
 */
@RestController
public class MyBatisController {

	@Autowired
	private VideoService videoService;

	@GetMapping("/mybatis")
	public Object getAllVideo() {
		return videoService.getAllVideo();
	}

	@GetMapping("/mybatisTran")
	public Object updateWithEx() {
		return videoService.update();
	}
}
