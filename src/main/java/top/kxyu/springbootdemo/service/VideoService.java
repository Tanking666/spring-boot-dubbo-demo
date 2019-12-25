package top.kxyu.springbootdemo.service;

import top.kxyu.springbootdemo.pojo.Video;

import java.util.List;

/**
 * @author KXY
 * @date Created in 2019/12/23 19:57
 */
public interface VideoService {

	public List<Video> getAllVideo();

	public int update();

	public Video getVideoById(String id);
}
