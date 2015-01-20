//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.Employee;

public class EmployeeDAO extends GenericDAO<Employee> {
	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Employee.class, tableName, cp);
	}

	public Employee[] getEmployees() throws RollbackException {
		Employee[] employees = match();
		// We want them sorted by last and first names (as per
		// employee.compareTo());
		return employees;
	}



	public void setPassword(String employeeName, String password)
			throws RollbackException {
		try {
			
			Employee dbemployee = read(employeeName);
			Transaction.begin();
			if (dbemployee == null) {
				throw new RollbackException("employee " + employeeName
						+ " no longer exists");
			}

			dbemployee.setPassword(password);

			update(dbemployee);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
