package io.can.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.can.studentcrud.dto.StudentDto;
import io.can.studentcrud.exception.InvalidPageRequestParameterException;
import io.can.studentcrud.exception.StudentNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentServiceTests {

	@Autowired
	private StudentService studentService;

	@Test
	public void test01_findStudentById() {
		StudentDto student = studentService.findStudentById("2");
		Assert.assertEquals("Cem", student.getFirstName());
		Assert.assertEquals("Berberoglu", student.getLastName());
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test02_findStudentById() {
		// id should be numeric
		StudentDto student = studentService.findStudentById("can");
	}

	@SuppressWarnings("unused")
	@Test(expected = StudentNotFoundException.class)
	public void test03_findStudentById() {
		// simulate the StundetNotFoundException
		StudentDto student = studentService.findStudentById("55");
	}

	@Test
	public void test04_findAllStudents() {
		List<StudentDto> students = studentService.findAllStudents(1, 5, "studentId");
		Assert.assertEquals(5, students.size());
	}

	@SuppressWarnings("unused")
	@Test(expected = InvalidPageRequestParameterException.class)
	public void test05_findAllStudents() {
		// pageNo not less than equal 1
		List<StudentDto> students = studentService.findAllStudents(0, 5, "studentId");
	}

	@SuppressWarnings("unused")
	@Test(expected = InvalidPageRequestParameterException.class)
	public void test06_findAllStudents() {
		// deneme is not field of Student class
		List<StudentDto> students = studentService.findAllStudents(1, 5, "deneme");
	}

	@Test
	public void test07_saveStudent() {
		StudentDto student = new StudentDto("savedFirstName", "savedLastName", "savedEmail", "savedStudentNumber");
		student.setStudentId(1L);
		StudentDto savedStudent = studentService.saveStudent(student);
		Assert.assertNotEquals(Long.valueOf(1L), savedStudent.getStudentId());
		Assert.assertEquals("savedFirstName", savedStudent.getFirstName());
		Assert.assertEquals("savedLastName", savedStudent.getLastName());
	}

	@Test
	public void test08_updateStudent() {
		StudentDto student = new StudentDto("updatedFirstName", "updatedLastName", "updatedEmail",
				"updatedStudentNumber");
		StudentDto updatedStudent = studentService.updateStudent("2", student);
		Assert.assertEquals(Long.valueOf(2L), updatedStudent.getStudentId());
		Assert.assertEquals("updatedFirstName", updatedStudent.getFirstName());
		Assert.assertEquals("updatedLastName", updatedStudent.getLastName());
	}

	@SuppressWarnings("unused")
	@Test(expected = StudentNotFoundException.class)
	public void test09_deleteStudent() {
		studentService.deleteStudent("2");
		StudentDto deletedStudent = studentService.findStudentById("2");
	}

	@Test
	public void test10_findStudentsByLastName() {
		List<StudentDto> students = studentService.findStudentsByLastName("Zengin");
		List<String> firstNames = students.stream().map(e -> e.getFirstName()).collect(Collectors.toList());
		MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Suleyman"));
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test11_findStudentsByLastName() {
		// lastName parameter is not empty string
		List<StudentDto> students = studentService.findStudentsByLastName("");

	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test12_findStudentsByLastName() {
		// lastName parameter is not null
		List<StudentDto> students = studentService.findStudentsByLastName(null);

	}

}
