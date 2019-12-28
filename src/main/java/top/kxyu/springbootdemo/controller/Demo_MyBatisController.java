package top.kxyu.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kxyu.springbootdemo.service.VideoService;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author KXY
 * @date Created in 2019/12/23 19:56
 */
@RestController
public class Demo_MyBatisController {

	@Autowired
	private VideoService videoService;

	@GetMapping("/mybatis/getAll")
	public Object gtAllVideo() {

		//多线程测试
		//此写法不被推荐
//		Executors.newFixedThreadPool(40);

		//预定义线程池
		//https://www.jianshu.com/p/f030aa5d7a28
		//参数对应(处理线程数量,最大数量,空闲任务存活时间.时间单位,工作队列=>无界(无限大)阻塞队列)
		//FixedThreadPool
		//可用于Web服务瞬时削峰，但需注意长时间持续高峰情况造成的队列阻塞。
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(40, 40, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		Runnable runnable = () -> {
			videoService.getAllVideo();
		};
		for (int i = 0; i < 10000; i++) {
			threadPoolExecutor.submit(runnable);

//			threadPoolExecutor.submit(() -> {
//				videoService.getAllVideo();
//			});
			//土方法 同上
//			threadPoolExecutor.submit(new Runnable() {
//				@Override
//				public void run() {
//					videoService.getAllVideo();
//				}
//			});
		}
		//CachedThreadPool
		//快速处理大量耗时较短的任务，如Netty的NIO接受请求时，可使用
		//workQueue 为 SynchronousQueue 同步队列，这个队列类似于一个接力棒，入队出队必须同时传递，因为CachedThreadPool线程创建无限制
//		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


		return videoService.getAllVideo();
	}

	@GetMapping("/mybatis/Tran")
	public Object updateWithEx() {
		return videoService.update();
	}
}
