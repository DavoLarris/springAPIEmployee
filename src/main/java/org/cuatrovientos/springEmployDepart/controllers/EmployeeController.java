package org.cuatrovientos.springEmployDepart.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.cuatrovientos.springEmployDepart.dao.EmployeeDAO;
import org.cuatrovientos.springEmployDepart.dtos.EmployeeDTO;
import org.cuatrovientos.springEmployDepart.mapper.EmployeeMapper;
import org.cuatrovientos.springEmployDepart.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeDAO employeeDAO;


	/**
	 * handles default /employees
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/", "/employees"})
	 */

	@RequestMapping(method = RequestMethod.GET, value = { "/", "/employees" })
	public String showDepartments(Map<String, Object> model) {
		logger.info("Show employees. ");

		
		List<Employee> employees = employeeDAO.selectAll(Employee.class);
		model.put("employees", employees);
		model.put("employeeS", new Employee());

		return "employee/employees";
	}

	/**
	 * handles default /employees/id
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/employees/{id}"})
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/employees/{id}" })
	public String employeeDetail(@PathVariable(value = "id") Integer id,
			Map<String, Object> model) {
		logger.info("Employee detail");

		Employee employee = employeeDAO.selectById(id, Employee.class);
		//The employee gets his own collection of tasks load
		model.put("employee", employee);
		model.put("employeeS", new Employee());
		

		return "employee/employeeDetail";
	}
	
	
	/**
	 * handles /employees/new by GET
	 * 
	 * @return the name of the view to show RequestMapping({"/employees/new"})
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/employees/new" })
	public String newEmployee(Map<String, Object> model) {
		logger.info("Showing custom view GET ");

		
		model.put("employee", new EmployeeDTO());
		model.put("department", employeeDAO.getDepartmentsId());
		model.put("employeeS", new Employee());
		
		return "employee/newEmployee";
	}

	/**
	 * handles /employees/new by POST
	 * 
	 * @return the name of the view to show RequestMapping({"/employees/new"})
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/employees/new" })
	public ModelAndView createEmployee(@ModelAttribute("employee") @Valid EmployeeDTO employeeDTO, BindingResult bindingResult) {
		logger.info("Saveview POST " + employeeDTO.getId());
		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			logger.info(bindingResult.getAllErrors().toString());
			modelAndView.setViewName("employee/newEmployee");
			modelAndView.addObject("employee", employeeDTO);
			modelAndView.addObject("department", employeeDAO.getDepartmentsId());
			modelAndView.addObject("employeeS", new Employee());
			return modelAndView;
		}
		
		Employee employee = EmployeeMapper.toEmployee(employeeDTO, employeeDAO.getDepartment(employeeDTO.getIdDepartment()));


		if (employeeDAO.insert(employee)) {
			// We return view name
			modelAndView.setViewName("employee/created");
			modelAndView.addObject("employee", employee);
			modelAndView.addObject("employeeS", new Employee());
		} else {
			modelAndView.setViewName("error");
			modelAndView
					.addObject("error",
							"An error ocurred while trying to create a new employee. Please, try again");
		}
		return modelAndView;
	}

	
	/**
	 * Delete the specific employee
	 */
	@RequestMapping(value = "/employees/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") Integer employeeId, Model model) {
		logger.info("Employee delete");
		
		
		employeeDAO.delete(employeeDAO.selectById(employeeId, Employee.class));
		model.addAttribute("employeeId", employeeId);
		model.addAttribute("employeeS", new Employee());

		return "employee/deleted";
	}
	
	
	/**
	 * Delete the specific employee
	 */
	@RequestMapping(value = "/employees/deleteall", method = RequestMethod.GET)
	public String deleteAll(Map<String, Object> model) {
		logger.info("Employee deleteAll");
		employeeDAO.deleteAll();

		model.put("employeeS", new Employee());

		return "employee/employees";
	}
	
	
	/**
	 * Simply selects the employee view
	 */
	@RequestMapping(value = "/employees/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable(value = "id") Integer employeeId, Model model) {
		logger.info("Showing update view GET ");

		// We find the employee through DAO and load into model
		model.addAttribute("employee", EmployeeMapper.toDTO(employeeDAO.selectById(employeeId, Employee.class)));
		model.addAttribute("department", employeeDAO.getDepartmentsId());
		model.addAttribute("employeeS", new Employee());

		return "employee/update";
	}
	
	
	/**
	 * Handles the POST from the Custom.jsp page to update the employee.
	 */
	@RequestMapping(value = "/employees/saveUpdated", method = RequestMethod.POST)
	public ModelAndView saveUpdate(EmployeeDTO employeeDTO) {
		logger.info("Save employee " + employeeDTO.getId());
		Employee employee = EmployeeMapper.toEmployee(employeeDTO, employeeDAO.getDepartment(employeeDTO.getIdDepartment()));
		
		employeeDAO.update(employee);

		ModelAndView modelAndView = new ModelAndView();

		// We pass the employee received through this object
		modelAndView.addObject("employee", employee);
		modelAndView.addObject("employeeS", new Employee());

		// The same as return "employee/saveUpdate"
		modelAndView.setViewName("employee/saveUpdated");
		return modelAndView;
	}

	/**
	 * search employees by name, then return to employees
	 */
	@RequestMapping(value = "/employees/search", method = RequestMethod.POST)
	public String searchProductTypes (Employee employee, Model model) {
		logger.info("Searching employees");
		
		List<Employee> employees = employeeDAO.selectByName(employee.getName());
		model.addAttribute("employees", employees);
		model.addAttribute("employeeS", new Employee());
		
		return "employee/employees";
	}
}
