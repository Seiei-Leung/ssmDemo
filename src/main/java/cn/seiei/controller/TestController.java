package cn.seiei.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.seiei.bean.Student;
import cn.seiei.service.StudentService;

@Controller
public class TestController {
	@Resource
	private StudentService studentService;
	
	@RequestMapping("/getall")
	@ResponseBody
	public JSONArray getall() {
		JSONArray jsonArray = studentService.getAll();
		return jsonArray;
	}
	
	@RequestMapping("/getall2")
	@ResponseBody
	public List<Student> getall2() {
		return studentService.getAll2();
	}
	
	@RequestMapping("/index")
	public String index() {
		return "helloworld";
	}
	
	// GET 方法
	@RequestMapping("/getmsgbysex")
	@ResponseBody
	public List<Student> getMsgBySex(HttpServletRequest request) {
		String sex;
		sex = request.getParameter("sex");
		List<Student> students = studentService.getMsgBySex(sex);
		return students;
	}
	
	// POST 方法
	@RequestMapping(value="/insertmsg", method=RequestMethod.POST)
	@ResponseBody
	public int insertMsg(@RequestBody Student student) {
		int result = studentService.insertMsg(student);
		return result;
	}
}
