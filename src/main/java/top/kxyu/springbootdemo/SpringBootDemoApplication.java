package top.kxyu.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//事务支持
@EnableTransactionManagement
//主类标识
@SpringBootApplication
//mapper扫描
//@MapperScan("top.kxyu.springbootdemo.mapper")
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		//启动容器  tomcat
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}
