package top.kxyu.springboot.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.kxyu.springboot.service.VideoService;

/**
 * @author KXY
 * @date Created in 2020/1/7 17:18
 */
@RestController
public class VideoController {

	//<dubbo:reference id="" interface="" version="">
	@Reference
	private VideoService videoService;

	@RequestMapping("/boot/Video")
	public Object getVideo(@RequestParam("id") String id) {
		videoService.sayHi("TESTER ");
		return videoService.getVideo(id);
	}
}
