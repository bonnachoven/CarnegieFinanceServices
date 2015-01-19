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

import databean.User;

public class UserDAO extends GenericDAO<User> {
	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(User.class, tableName, cp);
	}

	public User[] getUsers() throws RollbackException {
		User[] users = match();
		// We want them sorted by last and first names (as per
		// User.compareTo());
		return users;
	}

	public User read_from_email(String email) throws RollbackException {
		try {
			Transaction.begin();

			if (match(MatchArg.equals("userEmail", email)).length == 0) {
				return null;
			}
			Transaction.commit();
			return match(MatchArg.equals("userEmail", email))[0];
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public void setPassword(String userName, String password)
			throws RollbackException {
		try {
			
			User dbUser = read_from_email(userName);
			Transaction.begin();
			if (dbUser == null) {
				throw new RollbackException("User " + userName
						+ " no longer exists");
			}

			dbUser.setPassword(password);

			update(dbUser);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
