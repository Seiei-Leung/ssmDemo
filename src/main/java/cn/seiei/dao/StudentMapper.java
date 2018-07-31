package cn.seiei.dao;

import java.util.List;

import cn.seiei.bean.Student;

public interface StudentMapper {
    int insert(Student record);

    int insertSelective(Student record);
    
    List<Student> getAll();
}