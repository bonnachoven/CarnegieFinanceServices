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

import databean.Customer;

public class CustomerDAO extends GenericDAO<Customer> {
	public CustomerDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Customer.class, tableName, cp);
	}

	public Customer[] getCustomers() throws RollbackException {
		Customer[] customers = match();
		// We want them sorted by last and first names (as per
		// Customer.compareTo());
		return customers;
	}

	public Customer readUsers(String username) throws RollbackException {
		Customer[] cb = match(MatchArg.equals("userName", username));
		if (cb == null || cb.length == 0) return null;
		return cb[0];
	}


	public void setPassword(String customerName, String password)
			throws RollbackException {
		try {
			
			Customer dbcustomer = read(customerName);
			Transaction.begin();
			if (dbcustomer == null) {
				throw new RollbackException("Customer " + customerName
						+ " no longer exists");
			}

			dbcustomer.setPassword(password);

			update(dbcustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
