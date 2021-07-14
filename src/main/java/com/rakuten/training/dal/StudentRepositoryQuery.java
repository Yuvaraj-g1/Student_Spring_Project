package com.rakuten.training.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rakuten.training.domain.Student;

public interface StudentRepositoryQuery extends CrudRepository<Student, Integer>{
	
	@Query(value = "select count(w.id) from Student w")
	public int totalCount();

}