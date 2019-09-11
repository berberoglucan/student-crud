package io.can.studentcrud.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.can.studentcrud.dto.StudentDto;
import io.can.studentcrud.exception.InvalidPageRequestParameterException;
import io.can.studentcrud.exception.StudentNotFoundException;
import io.can.studentcrud.model.Student;
import io.can.studentcrud.repository.StudentRepository;
import io.can.studentcrud.service.StudentService;
import io.can.studentcrud.util.ReflectUtil;
import io.can.studentcrud.util.ServiceUtil;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ReflectUtil reflectUtil;
	
	@Autowired
	private ServiceUtil serviceUtil;

	@Override
	public StudentDto findStudentById(String studentId) {
		Long id = serviceUtil.getValidLongId(studentId);
		Optional<Student> optStudent = studentRepository.findById(id);
		Student student = optStudent.orElseThrow(() -> new StudentNotFoundException("Student is not found with the given student id: " + id));
		return serviceUtil.map(student, StudentDto.class);
	}

	@Override
	public List<StudentDto> findAllStudents(int pageNo, int pageSize, String sortBy) {

		if (pageNo < 1) {
			throw new InvalidPageRequestParameterException("pageNo parameter is not less 1");
		}

		if (!reflectUtil.isClassFieldGivenType(sortBy, Student.class)) {
			throw new InvalidPageRequestParameterException(
					sortBy + " no found for type " + reflectUtil.getClassNameForGivenType(Student.class));
		}

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));

		Page<Student> pagedStudents = studentRepository.findAll(pageable);

		if (pagedStudents.hasContent()) {
			return serviceUtil.mapAll(pagedStudents.getContent(), StudentDto.class);
		}

		return new ArrayList<>();

	}

	@Override
	public StudentDto saveStudent(StudentDto studentDto) {
		if(studentDto.getStudentId() != null) {
			studentDto.setStudentId(null);
		}
		Student student = serviceUtil.map(studentDto, Student.class);
		Student savedStudent = studentRepository.save(student);
		return serviceUtil.map(savedStudent, StudentDto.class);
	}

	@Override
	public StudentDto updateStudent(String studentId, StudentDto studentDto) {
		if(studentDto.getStudentId() != null) {
			studentDto.setStudentId(null);
		}
		Long id = serviceUtil.getValidLongId(studentId);
		if(!studentRepository.existsById(id)) {
			throw new StudentNotFoundException("Student is not found with the given student id: " + id);
		}
		Student student = serviceUtil.map(studentDto, Student.class);
		student.setStudentId(id);
		Student updatedStudent = studentRepository.save(student);
		return serviceUtil.map(updatedStudent, StudentDto.class);
	}

	@Override
	public void deleteStudent(String studentId) {
		Long id = serviceUtil.getValidLongId(studentId);
		if(!studentRepository.existsById(id)) {
			throw new StudentNotFoundException("Student is not found with the given student id: " + id);
		}
		studentRepository.deleteById(id);
	}

	@Override
	public List<StudentDto> findStudentsByLastName(String lastName) {
		if(StringUtils.isEmpty(lastName)) {
			throw new IllegalArgumentException("lastName parameter is not null or not empty");
		}
		lastName = lastName.trim().toLowerCase(new Locale("tr", "TR"));
		List<Student> students = studentRepository.findStudentsByLastName(lastName);
		return serviceUtil.mapAll(students, StudentDto.class);
	}

}
