package io.can.studentcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.can.studentcrud.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query("SELECT s FROM Student s WHERE LOWER(s.lastName) LIKE %:lastName%")
	List<Student> findStudentsByLastName(@Param("lastName") String lastName);
	
}
