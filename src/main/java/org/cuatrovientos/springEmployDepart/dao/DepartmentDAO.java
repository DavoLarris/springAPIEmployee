package org.cuatrovientos.springEmployDepart.dao;

import java.util.List;

import org.cuatrovientos.springEmployDepart.models.Department;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

public class DepartmentDAO extends GenericDAO<Department> {

	/**
	 * Selects all product types by name
	 * 
	 * @return List of products
	 */
	@Transactional(readOnly = true)
	public List<Department> selectByName(String name) {

		List<Department> departments = null;

		Query query = getSession().createQuery("from Department d where d.name LIKE :nam");
		query.setParameter("nam", "%" + name + "%");

		// We get a generic object list
		departments = query.list();

		return departments;
	}

	@Transactional(readOnly = true)
	public List<Department> takeEmployees(String name) {

		List<Department> departments = null;

		Query query = getSession().createQuery("select d.employees from Department d where d.name LIKE :nam");
		query.setParameter("nam", "%" + name + "%");

		// We get a generic object list
		departments = query.list();

		return departments;
	}
}
