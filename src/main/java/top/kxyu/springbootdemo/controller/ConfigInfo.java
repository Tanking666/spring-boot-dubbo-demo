package top.kxyu.springbootdemo.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author KXY
 * @date Created in 2019/12/22 18:46
 */
//读配置类
@Component
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true,encoding = "UTF-8" )
@ConfigurationProperties(prefix = "cust")
public class ConfigInfo {

//	@Value("name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
