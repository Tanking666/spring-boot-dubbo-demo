package top.kxyu.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author KXY
 * @date Created in 2019/12/22 18:35
 */


@Controller
//所有方法自带@ResponseBody
//@RestController
public class ConfigInfoController {

	//自定义变量
	@Value("${cust.name}")
	private String name;
	@Autowired
	ConfigInfo configInfo;

	//等价@RequestMapping(value = "/config", method = RequestMethod.GET)
//	@GetMapping(value = "/config")
//	@PostMapping
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		System.out.println(configInfo.getName());
		return name + "class:" + configInfo.getName();
	}

	@GetMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("msg", "this is msg哈哈");
		return "index";
	}
}

