package cn.seiei.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.seiei.bean.Student;
import cn.seiei.dao.StudentMapper;

@Service("studentService")
public class StudentService {
	@Resource
	private StudentMapper studentMapper;
	public List<Student> getAll() {
		System.out.println("数据获取");
		return studentMapper.getAll();
	}
}
