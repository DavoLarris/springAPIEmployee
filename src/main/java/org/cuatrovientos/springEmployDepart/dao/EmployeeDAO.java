package org.cuatrovientos.springEmployDepart.dao;

import java.util.List;
import java.util.Random;

import org.cuatrovientos.springEmployDepart.models.Department;
import org.cuatrovientos.springEmployDepart.models.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * implementation of ProductDAO
 * 
 * @author Eugenia Pérez Martínez
 * @email eugenia_perez@cuatrovientos.org
 *
 */

public class EmployeeDAO extends GenericDAO<Employee> {

	/**
	 * Selects all product types by name
	 * 
	 * @return List of products
	 */
	@Transactional(readOnly = true)
	public List<Employee> selectByName(String name) {

		List<Employee> employees = null;

		Query query = getSession().createQuery("from Employee e where e.name LIKE :nam");
		query.setParameter("nam", "%" + name + "%");

		// We get a generic object list
		employees = query.list();

		return employees;
	}

	@Transactional(readOnly = true)
	public List<Integer> getDepartmentsId() {

		List<Integer> departments = null;

		Query query = getSession().createQuery("SELECT id FROM Department");

		// We get a generic object list
		departments = query.list();

		return departments;
	}

	@Transactional(readOnly = true)
	public Department getDepartment(Integer id) {
		Department depart = null;
		depart = (Department) getSession().createQuery("FROM Department WHERE id = :id").setParameter("id", id).uniqueResult();

		return depart;
	}
	
	@Transactional
	public void deleteAll(){
		String stringQuery = "DELETE FROM Employee";
		Query query = getSession().createQuery(stringQuery);
		query.executeUpdate();
	}
}
