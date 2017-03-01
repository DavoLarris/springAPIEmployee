package org.cuatrovientos.springEmployDepart.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.cuatrovientos.springEmployDepart.dtos.EmployeeDTO;
import org.cuatrovientos.springEmployDepart.models.Department;
import org.cuatrovientos.springEmployDepart.models.Employee;

public class EmployeeMapper {
	private static SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy"); 

	public static Employee toEmployee(EmployeeDTO employeeDTO, Department department) throws ParseException {
		Employee employee = new Employee();
		employee.setId(employeeDTO.getId());
		employee.setName(employeeDTO.getName());
		employee.setBirthDate(dt.parse(employeeDTO.getBirthDate()));
		employee.setTelephone(employeeDTO.getTelephone());
		employee.setDepartment(department);
		return employee;
	}

	public static EmployeeDTO toDTO(Employee employee) {
		EmployeeDTO employeeDTO;
		if (employee.getDepartment() == null) {
			employeeDTO = new EmployeeDTO(employee.getId(), employee.getName(), dt.format(employee.getBirthDate()),
					employee.getTelephone(), 0);
		} else {
			employeeDTO = new EmployeeDTO(employee.getId(), employee.getName(), dt.format(employee.getBirthDate()),
					employee.getTelephone(), employee.getDepartment().getId());
		}
		return employeeDTO;
	}

}
