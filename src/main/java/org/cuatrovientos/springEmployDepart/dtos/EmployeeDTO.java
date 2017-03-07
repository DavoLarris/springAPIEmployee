package org.cuatrovientos.springEmployDepart.dtos;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


public class EmployeeDTO {

	@Min(1)
	private Integer id;
	
	@NotNull(message = "Please enter your name.")
	@Pattern(regexp = "[A-Za-z]+", message = "Must only contain chars")
	@NotEmpty(message = "Field should not be empty.")
	private String name;
	
	//@NotNull(message = "Please enter your birth date")
	//@DateTimeFormat(pattern="dd-MM-yyyy")
	//@Past(message="Only the past is valid")
	private Date birthDate;
	
	@NotNull(message = "Please enter your phone number")
	@Pattern(regexp = "[0-9]+", message = "Must only contain numbers")
	@Size(max=9, message="Only 9 characters max allowed")
	private String telephone;
	
	//Adding new fields
	private int idDepartment;
	
	/**
	 * default constructor
	 */
	public EmployeeDTO () {
		
	}
	
	public EmployeeDTO(Integer id, String name, Date date, String telephone, int idDepartment) {
		this.id = id;
		this.name = name;
		this.birthDate = date;
		this.telephone = telephone;
		this.idDepartment = idDepartment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(int idDepartment) {
		this.idDepartment = idDepartment;
	}

}
