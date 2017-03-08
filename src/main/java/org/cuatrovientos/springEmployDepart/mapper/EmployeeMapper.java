package org.cuatrovientos.springEmployDepart.mapper;

import java.text.ParseException;

import org.cuatrovientos.springEmployDepart.dtos.EmployeeDTO;
import org.cuatrovientos.springEmployDepart.models.Department;
import org.cuatrovientos.springEmployDepart.models.Employee;

public class EmployeeMapper {
	private static java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static java.util.Date date = null;
	
	public static Employee toEmployee(EmployeeDTO employeeDTO, Department department) {
		Employee employee = new Employee();
		employee.setId(employeeDTO.getId());
		employee.setName(employeeDTO.getName());
		
		try {
			date = sdf.parse(employeeDTO.getBirthDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employee.setBirthDate(sdf.format(date));
		
		employee.setTelephone(employeeDTO.getTelephone());
		employee.setDepartment(department);
		return employee;
	}

	public static EmployeeDTO toDTO(Employee employee) {
		EmployeeDTO employeeDTO;
		try {
			date = sdf.parse(employee.getBirthDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (employee.getDepartment() == null) {
			employeeDTO = new EmployeeDTO(employee.getId(), employee.getName(), sdf.format(date), employee.getTelephone(), 0);
		} else {
			employeeDTO = new EmployeeDTO(employee.getId(), employee.getName(), sdf.format(date), employee.getTelephone(), employee.getDepartment().getId());
		}
		return employeeDTO;
	}

}
