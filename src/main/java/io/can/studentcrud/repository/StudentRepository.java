package io.can.studentcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.can.studentcrud.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
