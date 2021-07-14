package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Student;
import com.rakuten.training.service.StudentService;

@CrossOrigin
@RestController
public class StudentController {
	
   @Autowired	
   StudentService service;
	
	@GetMapping("/students")
	public List<Student> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/students/count")
	public String totalCount() {
		return ("<h1>Total count is </h1> </br> : "+service.getTotalCount());
		}
		

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getById(@PathVariable("id") int id) {
		Student s = service.getById(id);
		if (s == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Student>(s, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/students/name/{name}")
	public ResponseEntity getByName(@PathVariable("name") String name){
		List<Student> l=service.getByName(name);
	   	return new ResponseEntity<List<Student>>(l, HttpStatus.OK);
	   	
	}
	
	@PostMapping("/students")
	public ResponseEntity addStudent(@RequestBody Student tobeAdded) {
		try {
			Student id = service.addStudent(tobeAdded);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/students/" + id));
			return new ResponseEntity(tobeAdded, headers, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity updateStudent(@RequestBody Student toBeUpdated, @PathVariable("id") int id) {
		Student idr = service.updateStudent(toBeUpdated,id);
		return new ResponseEntity( HttpStatus.OK);
	}

	
	@DeleteMapping("/students/{id}")
	public ResponseEntity deleteById(@PathVariable("id") int id) {
		try
		{
			Student s=service.getById(id);
			if(s==null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				service.deleteById(id);
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		}catch(IllegalArgumentException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		    }
	}
	
	
				

}
