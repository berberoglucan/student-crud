package io.can.studentcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.can.studentcrud.dto.StudentDto;
import io.can.studentcrud.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/{studentId}")
	public ResponseEntity<StudentDto> findStudentById(@PathVariable("studentId") final String studentId) {
		StudentDto student = studentService.findStudentById(studentId);
		return ResponseEntity.ok(student);
	}

	@GetMapping("/all")
	public ResponseEntity<List<StudentDto>> getAllStudents(
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "sortBy", defaultValue = "studentId") String sortBy) {

		List<StudentDto> allStudents = studentService.findAllStudents(pageNo, pageSize, sortBy);
		return ResponseEntity.ok(allStudents);

	}
	
	@GetMapping
	public ResponseEntity<List<StudentDto>> findStudentsByLastName(@RequestParam("lastName") final String lastName) {
		List<StudentDto> students = studentService.findStudentsByLastName(lastName);
		return ResponseEntity.ok(students);
	}

	@PostMapping
	public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto student) {
		StudentDto savedStudent = studentService.saveStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
	}

	@PutMapping(value = "/{studentId}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable("studentId") String studentId,
			@RequestBody StudentDto studentDto) {
		StudentDto updatedStudent = studentService.updateStudent(studentId, studentDto);
		return ResponseEntity.ok(updatedStudent);
	}

	@DeleteMapping(value = "/{studentId}")
	public ResponseEntity<?> deleteStudent(@PathVariable("studentId") String studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.ok().build();
	}

}
