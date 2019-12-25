package top.kxyu.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kxyu.springbootdemo.service.VideoService;

/**
 * @author KXY
 * @date Created in 2019/12/25 12:58
 */
@RestController
public class RESTFullController {

	@Autowired
	VideoService videoService;

	@RequestMapping("/rest/{id}/{id2}")
	public Object user(@PathVariable("id") String id, @PathVariable("id2") String id2) {
		if (videoService.getVideoById(id) == null) {
			return videoService.getVideoById(id2);
		} else {
			return videoService.getVideoById(id);
		}
	}

}
