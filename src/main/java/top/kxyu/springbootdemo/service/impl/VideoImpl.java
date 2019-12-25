package top.kxyu.springbootdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.kxyu.springbootdemo.mapper.VideoMapper;
import top.kxyu.springbootdemo.pojo.Video;
import top.kxyu.springbootdemo.service.VideoService;

import java.util.List;

/**
 * @author KXY
 * @date Created in 2019/12/23 19:59
 */

@Service
public class VideoImpl implements VideoService {

	@Autowired
	private VideoMapper videoMapper;

	@Override
	public List<Video> getAllVideo() {
		return videoMapper.getAllVideo();
	}

	//事务
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int update() {
		Video v = new Video();

		v.setId("08668f48-2270-11ea-9078-00e07b680c17");
		v.setSubGroup("ABCDEFG");
		int updateCnt = videoMapper.updateByPrimaryKey(v);
		System.out.println(updateCnt);
		int a = 1 / 0;

		return updateCnt;
	}

	@Override
	public Video getVideoById(String id) {
		return videoMapper.selectByPrimaryKey(id);
	}
}
