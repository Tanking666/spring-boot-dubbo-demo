package top.kxyu.springbootdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	//只有这两种合法范型书写方式
	//	private RedisTemplate<String, String> redisTemplate;

	/*redis非读写分离*/
	@Override
	public List<Video> getAllVideo() {

		/*注意RedisTemplate无法直接读写分离 读写为master*/
		//序列化工具 字符串实现
		RedisSerializer redisSerializer = new StringRedisSerializer();
		//键序列化方式  字符串
		redisTemplate.setKeySerializer(redisSerializer);


		//双重检测/双重检测锁 假设N人同时访问方法 且redis此时未缓存
		//高并发此处容易翻车(缓存穿透) 1.方法加锁 synchronized 2.细化锁块
		List<Video> list = (List<Video>) redisTemplate.opsForValue().get("AllVideo");
		//防止并发穿透此判断 直接冲击数据库
		if (null == list) {
			//同步
			synchronized (this.getClass()) {
				//redis 缓存取数据
				list = (List<Video>) redisTemplate.opsForValue().get("AllVideo");
				//只有第一个访问符合判断 查询数据库  其他访问将直接从缓存取得数据 并跳过下面方法
				if (null == list) {
					//mysql->
					System.out.println("数据库查询");
					list = videoMapper.getAllVideo();
					//->redis
					redisTemplate.opsForValue().set("AllVideo", list);
				} else {
					System.out.println("redis查询");
				}
			}
		} else {
			System.out.println("查询缓存");
		}
		return list;
	}

	/*redis非读写分离*/
	public List<Video> getAllVideoByPool() {

		/*注意RedisTemplate无法直接读写分离 读写为master*/
		//序列化工具 字符串实现
		RedisSerializer redisSerializer = new StringRedisSerializer();
		//键序列化方式  字符串
		redisTemplate.setKeySerializer(redisSerializer);


		//双重检测/双重检测锁 假设N人同时访问方法 且redis此时未缓存
		//高并发此处容易翻车(缓存穿透) 1.方法加锁 synchronized 2.细化锁块
		List<Video> list = (List<Video>) redisTemplate.opsForValue().get("AllVideo");
		//防止并发穿透此判断 直接冲击数据库
		if (null == list) {
			//同步
			synchronized (this.getClass()) {
				//redis 缓存取数据
				list = (List<Video>) redisTemplate.opsForValue().get("AllVideo");
				//只有第一个访问符合判断 查询数据库  其他访问将直接从缓存取得数据 并跳过下面方法
				if (null == list) {
					//mysql->
					System.out.println("数据库查询");
					list = videoMapper.getAllVideo();
					//->redis
					redisTemplate.opsForValue().set("AllVideo", list);
				} else {
					System.out.println("redis查询");
				}
			}
		} else {
			System.out.println("查询缓存");
		}
		return list;
	}

	//事务 捕获异常 所有操作回滚
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
