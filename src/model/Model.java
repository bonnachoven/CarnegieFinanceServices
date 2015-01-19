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
	private FavoriteDAO favoriteDAO;
	private UserDAO userDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			userDAO  = new UserDAO(pool, "xuzhao_user");
			favoriteDAO = new FavoriteDAO(pool, "xuzhao_favorite");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public FavoriteDAO getFavoriteDAO()  { return favoriteDAO; }
	public UserDAO getUserDAO()  { return userDAO; }
}
