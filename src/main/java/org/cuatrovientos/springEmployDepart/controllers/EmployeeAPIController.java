package org.cuatrovientos.springEmployDepart.controllers;

import java.util.List;

import javax.validation.Valid;

import org.cuatrovientos.springEmployDepart.dao.EmployeeDAO;
import org.cuatrovientos.springEmployDepart.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for employees.
 * 
 * @author David Larrea
 *
 */
@RestController
@RequestMapping("/employees/api")
public class EmployeeAPIController {
	@Autowired
	private EmployeeDAO employeeDAO;


	/**
	 * Returns the list of employees. handles /employees/api
	 * 
	 * @return the list of all series
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> getAll() {
		return employeeDAO.selectAll(Employee.class);
	}

	/**
	 * Returns a TV show by its ID. handles /employees/api/id
	 * 
	 * @param id
	 * @return the employee by ID
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}" })
	public Employee getById(@PathVariable(value = "id") Integer id) {
		return employeeDAO.selectById(id, Employee.class);
	}

	/**
	 * Creates a new employee. handles /employees/api by POST
	 * 
	 * @return true if it is created successfully
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void insert(@RequestBody Employee series) {
		employeeDAO.insert(series);
	}

	/**
	 * Updates a employee by its ID. handles /employees/api/{id} by PUT
	 * 
	 * @param id
	 * @param series
	 * @return true if it is updated successfully
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody @Valid Employee employee, @PathVariable Integer id) {
		employee.setId(id);
		employeeDAO.update(employee);
	}

	/**
	 * Deletes a employee by its ID. handles /employees/api/{id} by DELETE
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		employeeDAO.delete(employeeDAO.selectById(id, Employee.class));
	}

	/**
	 * Get the DAO
	 * @return the Generic DAO of employees
	 */
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	/**
	 * Set the DAO
	 * @param employeeDAO
	 */
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	

}
