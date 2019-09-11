package io.can.studentcrud.service;

import java.util.List;

import io.can.studentcrud.dto.StudentDto;

public interface StudentService {

	StudentDto findStudentById(String studentId);
	
	List<StudentDto> findAllStudents(int pageNo, int pageSize, String sortBy);
	
	List<StudentDto> findStudentsByLastName(String lastName);
	
	StudentDto saveStudent(StudentDto studentDto);
	
	StudentDto updateStudent(String studentId, StudentDto studentDto);
	
	void deleteStudent(String studentId);
	
}
