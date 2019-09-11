package io.can.studentcrud.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import io.can.studentcrud.dto.StudentDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentControllerTests {

	@Autowired
	private RestTemplate restTemplate;
	
	private String url;
	
	@Before
	public void init() {
		url = "http://localhost:8080/student";
	}
	
	@Test
	public void test01_findStudentById() {
		 ResponseEntity<StudentDto> response = restTemplate.getForEntity(url + "/1", StudentDto.class);
		 Assert.assertEquals(200, response.getStatusCodeValue()); // --> Junit
		 // MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200)); // --> Hemcrest
		 Assert.assertEquals("Can", response.getBody().getFirstName());
	}
	
	@SuppressWarnings(value = {"rawtypes", "unchecked"})
	@Test
	public void test02_findStudentsByLastName() {
		ResponseEntity<List> response = restTemplate.getForEntity(url + "?lastName=Berberoglu", List.class);
		List<Map<String, String>> body = response.getBody();
		Assert.assertEquals(200, response.getStatusCodeValue());
		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());
		//Assert.assertEquals(firstNames, Arrays.asList("Can", "Cem"));
		// MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Can", "Cem"));
		// Assert.assertEquals(2, body.size());
	}
	
	@SuppressWarnings(value = {"rawtypes", "unchecked"})
	@Test
	public void test03_findAllStudents() {
		ResponseEntity<List> response = restTemplate.getForEntity(url + "/all", List.class);
		List<Map<String, String>> body = response.getBody();
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals(5, body.size());
		// List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());
		// MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Can", "Cem", "Sinan", "Suleyman", "Kadir"));
	}
	
	@Test
	public void test04_createStudent() {
		StudentDto student = new StudentDto("firstName", "lastName", "email@gmail.com", "12454356");
		ResponseEntity<StudentDto> response = restTemplate.postForEntity(url, student, StudentDto.class);
		Assert.assertEquals(201, response.getStatusCodeValue());
		// Assert.assertEquals(Long.valueOf(6L), response.getBody().getStudentId());
		Assert.assertEquals("firstName", response.getBody().getFirstName());
		Assert.assertEquals("lastName", response.getBody().getLastName());
	}
	
	@Test
	public void test05_updateStudent() {
		StudentDto student = new StudentDto("updatedFirstName", "updatedLastName", "updatedEmail@gmail.com", "updated12454356");
		/*restTemplate.put(url + "/1", student);
		ResponseEntity<StudentDto> response = restTemplate.getForEntity(url + "/1", StudentDto.class);*/
		ResponseEntity<StudentDto> response = restTemplate.exchange(url + "/1", HttpMethod.PUT, new HttpEntity<StudentDto>(student), StudentDto.class);
		Assert.assertEquals("updatedFirstName", response.getBody().getFirstName());
		Assert.assertEquals("updatedLastName", response.getBody().getLastName());
	}
	
	@Test
	public void test06_deleteStudent() {
		ResponseEntity<Void> response = restTemplate.exchange(url + "/1", HttpMethod.DELETE, null, Void.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}
	
}
