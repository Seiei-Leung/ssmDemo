package cn.seiei.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.seiei.bean.Student;
import cn.seiei.service.StudentService;

@Controller
public class CommonController {

	@Resource
	private StudentService studentService;

	/**
	 * 使用 JSON 对象返回
	 * 
	 * @return
	 */
	@RequestMapping("/getall_json")
	@ResponseBody
	public JSONArray getall() {
		JSONArray jsonArray = studentService.getAll();
		return jsonArray;
	}

	/**
	 * 直接让 spring 自动封装 JSON 对象
	 * 
	 * @return
	 */
	@RequestMapping("/getall")
	@ResponseBody
	public List<Student> getall2() {
		return studentService.getAll2();
	}

	/**
	 * 返回首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "helloworld";
	}

	/**
	 * 普通 GET方法
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getmsgbysex")
	@ResponseBody
	public List<Student> getMsgBySex(HttpServletRequest request) {
		String sex;
		sex = request.getParameter("sex");
		List<Student> students = studentService.getMsgBySex(sex);
		return students;
	}

	/**
	 * 普通 POST 方法
	 * 
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/insertmsg", method = RequestMethod.POST)
	@ResponseBody
	public int insertMsg(Student student) {
		int result = studentService.insertMsg(student);
		return result;
	}

	/**
	 * 用于测试事务
	 */
	@RequestMapping("/testForTransactionalTEST")
	@ResponseBody
	public void testForTransactionalTEST() {
		try {
			studentService.insertForTransactionalTEST();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
