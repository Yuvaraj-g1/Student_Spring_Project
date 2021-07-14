package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.domain.Student;

public interface StudentService {
	
	public List<Student> getAll();
	public Student getById(int id);
	public List<Student> getByName(String name);
	
	public Student addStudent(Student tobeAdded);
	
	public Student updateStudent(Student tobeUpdated,int id);
	
	public void deleteById(int id);
	
	public int getTotalCount();

}
