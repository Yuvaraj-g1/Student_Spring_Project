package com.rakuten.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.training.dal.StudentDAO;
import com.rakuten.training.dal.StudentRepositoryQuery;
import com.rakuten.training.domain.Student;

@Service
//@Transactional
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentDAO dao;
	
	
	@Autowired
	StudentRepositoryQuery rdao;
	
	@Override
	public int getTotalCount() {
		return rdao.totalCount();
	}
	

	@Override
	public List<Student> getAll() {
		return dao.findAll();
	}

	@Override
	public Student getById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Student> getByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public Student addStudent(Student tobeAdded) {
		Student saved=dao.addStudent(tobeAdded);
		return saved;
	}

	@Override
	public Student updateStudent(Student tobeUpdated,int id) {

		Student s=dao.findById(id);
		if(s==null)
		{
			Student st=dao.addStudent(tobeUpdated);
			return st;
		}
		else
		{
			s.setBranch(tobeUpdated.getBranch());
			s.setName(tobeUpdated.getName());
			s.setCity(tobeUpdated.getCity());
			s.setPhone(tobeUpdated.getPhone());
			
			Student st=dao.addStudent(s);
			return st;
			
		}
	
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
		
	}

	


}
