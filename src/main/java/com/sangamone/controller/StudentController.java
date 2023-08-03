
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

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Student API")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping("/")
	public String index() {
		return "Welcome to Spring boot";
	}
	
	@PostMapping("/saveStudent")
	public Student saveData(@RequestBody Student student) {
		Student student1 = studentRepo.save(student);
		return student1;
	}
	
	@GetMapping("/getStudent/{rollNo}")
	public ResponseEntity<Student> getStudentById(@PathVariable int rollNo) {
		Optional<Student> student = studentRepo.findById(rollNo);
		if (student.isPresent()) {
			return new ResponseEntity<>(student.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getAllStudent")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> studentList = studentRepo.findAll();
		return new ResponseEntity<>(studentList, HttpStatus.OK);
	}
	
	@DeleteMapping("/DeleteData/{rollNo}")
	public String deleteData(@PathVariable int rollNo) {
		Student student = studentRepo.findById(rollNo).orElse(null);
		if (student != null) {
			studentRepo.delete(student);
			return "Deleted Successfully";
		} else {
			return "Student not found";
		}
	}
	
	@PutMapping("/updateData")
	public Student updateStudentData(@RequestBody Student student) {
	    studentRepo.save(student);
		return student;
	}
}
