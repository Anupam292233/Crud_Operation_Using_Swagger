package com.sangamone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sangamone.entity.Student;
import com.sangamone.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping("/")
	public String index() {
		return "Welcome to Spring boot";
	}
	
	//Handle for creating new record in DB
	@PostMapping("/saveStudent")
	public String saveData(@RequestBody Student student) {
		 Student student1 = studentRepo.save(student);
		return "student1";
	}
	
	//handle for fetch a single record
	@GetMapping("/getStudent/{rollNo}")
	public ResponseEntity<Student> getStudentById(@PathVariable int rollNo) {
		Optional<Student> student = studentRepo.findById(rollNo);
		if(student.isPresent()) {
			return new ResponseEntity<Student>(student.get(),HttpStatus.FOUND);
		}  else {
			return new ResponseEntity<Student>(student.get(),HttpStatus.NOT_FOUND);
		}

	}
	
	
	
	
	//Handle for fetch all Data from DB
	@GetMapping("/getAllStudent")
	public ResponseEntity<List<Student>> getAllStudent(){
		List<Student> studentList = studentRepo.findAll();
		return new ResponseEntity<List<Student>>(studentList,HttpStatus.OK);
		
	}
	
	//Handle for delete a particular record from DB
	
	@DeleteMapping("/DeleteData/{rollNo}")
	public String deleteData(@PathVariable int rollNo) {
		Student student = studentRepo.findById(rollNo).get();
		if(student!=null);
		   studentRepo.delete(student);
		return "Deleted Successfully";
		
	}
	//Handle for updating a particular record
	@PutMapping("/updateData")
	public Student updateStudentData(@RequestBody Student student) {
	            studentRepo.save(student);
		return student;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
