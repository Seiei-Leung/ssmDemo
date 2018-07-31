package cn.seiei.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seiei.service.StudentService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Resource
	private StudentService studentService;
	
	@RequestMapping("/getall")
	public String getall() {
		System.out.println("成功");
		studentService.getAll();
		return "成功";
	}
}
