package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.rakuten.training.domain.Student;

@Repository
@Transactional
public class StudentDAOImpl implements StudentDAO{
	
	@Autowired
	EntityManager em;

	@Override
	public List<Student> findAll() {
		Query q = em.createQuery("select s from Student as s");
		return q.getResultList();
	}

	@Override
	public Student findById(int id) {
		return em.find(Student.class, id);
	}

	@Override
	public List<Student> findByName(String name) {
		Query q = em.createQuery("select s from Student s where s.name like :n");
		q.setParameter("n", "%"+name+"%");
		return q.getResultList();
	}

	@Override
	public Student addStudent(Student tobeAdded) {
		em.persist(tobeAdded);
		return tobeAdded;
	}

	@Override
	public Student updateStudent(Student tobeUpdated,int id) {
		em.merge(tobeUpdated);
		return tobeUpdated;
	}

	@Override
	public void deleteById(int id) {
		Student r = em.find(Student.class, id);
		em.remove(r);
	}
	

}
