package top.kxyu.springboot.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.kxyu.springboot.mapper.VideoMapper;
import top.kxyu.springboot.model.Video;
import top.kxyu.springboot.service.VideoService;

/**
 * @author KXY
 * @date Created in 2020/1/7 19:30
 */
//@org.springframework.stereotype.Service
@Component
@Service
public class VideoServiceImpl implements VideoService {


	@Autowired
	private VideoMapper videoMapper;

	@Override
	public void sayHi(String name) {
		System.out.println("Hi " + name);
	}

	@Override
	public Video getVideo(String id) {
		return videoMapper.selectByPrimaryKey(id);
	}
}
