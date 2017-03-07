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
@RequestMapping("/api")
public class EmployeeAPIController {
	@Autowired
	private EmployeeDAO employeeDAO;


	/**
	 * Returns the list of employees. handles /api
	 * 
	 * @return the list of all series
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> getAll() {
		return employeeDAO.selectAll(Employee.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/last/{id}" })
	public List<Employee> getFromLast(@PathVariable(value = "id") Integer id) {
		return employeeDAO.selectLast(id);
	}
	/**
	 * Returns a TV show by its ID. handles /api/id
	 * 
	 * @param id
	 * @return the employee by ID
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}" })
	public Employee getById(@PathVariable(value = "id") Integer id) {
		return employeeDAO.selectById(id, Employee.class);
	}

	/**
	 * Creates a new employee. handles /api/new by POST
	 * 
	 * @return true if it is created successfully
	 */
	@RequestMapping(method = RequestMethod.POST, value= { "/new" })
	public boolean insert(@RequestBody Employee series) {
		 return employeeDAO.insert(series);
	}

	/**
	 * Updates a employee by its ID. handles /api/{id} by PUT
	 * 
	 * @param id
	 * @param series
	 * @return true if it is updated successfully
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Integer id,
			@RequestBody @Valid Employee employee) {
		employee.setId(id);
		employeeDAO.update(employee);
	}

	/**
	 * Deletes a employee by its ID. handles /api/{id} by DELETE
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
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
