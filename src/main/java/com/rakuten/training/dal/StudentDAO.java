package com.rakuten.training.dal;

import java.util.List;

import com.rakuten.training.domain.Student;

public interface StudentDAO {
	
	public List<Student> findAll();
	public Student findById(int id);
	public List<Student> findByName(String name);
	
	public Student addStudent(Student tobeAdded);
	
	public Student updateStudent(Student tobeAdded,int id);
	
	public void deleteById(int id);

}
