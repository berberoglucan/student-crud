package io.can.studentcrud.dto;

public class StudentDto {

	private Long studentId;

	private String firstName;

	private String lastName;

	private String email;

	private String studentNumber;

	public StudentDto() {
	}

	public StudentDto(String firstName, String lastName, String email, String studentNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.studentNumber = studentNumber;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	@Override
	public String toString() {
		return "StudentDto [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", studentNumber=" + studentNumber + "]";
	}

}
