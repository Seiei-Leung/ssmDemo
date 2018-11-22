package cn.seiei.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.seiei.bean.Student;
import cn.seiei.dao.StudentMapper;

@Service("studentService")
public class StudentService {
	@Resource
	private StudentMapper studentMapper;

	// 使用 json 返回
	public JSONArray getAll() {
		JSONArray jsonArray = new JSONArray();
		List<Student> students = studentMapper.getAll();
		for (Student student : students) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", student.getName());
			jsonObject.put("age", student.getAge());
			jsonObject.put("sex", student.getSex());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	// 直接返回 bean 对象
	public List<Student> getAll2() {
		List<Student> students = studentMapper.getAll();
		return students;
	}

	// 性别筛选信息
	public List<Student> getMsgBySex(String sex) {
		List<Student> students = new ArrayList<Student>();
		if (sex.equals("boy") || sex.equals("girl")) {
			students = studentMapper.getMsgBySex(sex);
		}
		return students;
	}

	// 插入信息
	public int insertMsg(Student student) {
		studentMapper.insert(student);
		if (student.getId() != null && student.getId() > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	// 默认配置下，spring只有在抛出的异常为运行时unchecked异常时才回滚该事务，也就是抛出的异常为RuntimeException的子类(Errors也会导致事务回滚)，而抛出checked异常则不会导致事务回滚。
	// 以下的异常不是 RuntimeException
	@Transactional(rollbackFor=Exception.class)
	public void insertForTransactionalTEST() throws Exception {
		Student student = new Student();
		student.setAge(19);
		student.setName("Maga");
		student.setSex("girl");
		studentMapper.insert(student);
		errorForTransactionalTEST();
	}
	
	@Transactional
	public void errorForTransactionalTEST() throws Exception {
		throw new Exception("炸裂！！！");
	}
}
