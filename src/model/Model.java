//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private FundDAO fundDAO;
	private EmployeeDAO employeeDAO;
	private CustomerDAO accountDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
		fundDAO  = new FundDAO(pool, "xuzhao_user");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public FundDAO getFundDAO()  { return fundDAO; }
	public EmployeeDAO getEmployeeDAO()  { return employeeDAO; }
	public CustomerDAO getCustomerDAO()  { return accountDAO; }
	public TransactionDAO getTransactionDAO()  { return transactionDAO; }
	public PositionDAO getPositionDAO()   { return positionDAO; }

	
}
