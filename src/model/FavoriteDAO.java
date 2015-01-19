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

import databean.Favorite;
import databean.User;
import databean.Favorite;

public class FavoriteDAO extends GenericDAO<Favorite> {
	public FavoriteDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Favorite.class, tableName, cp);
	}

	public Favorite[] getUserFavorites(int userId) throws RollbackException {

		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans
		try {
			Transaction.begin();
			Favorite[] favs = match(MatchArg.equals("userId", userId));
			Transaction.commit();
			return favs;

		}

		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public Favorite[] getFavoriteList(int userId) throws RollbackException {
		try {
			Transaction.begin();
			Favorite[] list = match(MatchArg.equals("userId", userId));
			Transaction.commit();
			return list;

		}

		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}

	public Favorite read_from_url(String url) throws RollbackException {
		try {
			Transaction.begin();
			Favorite[] bean = match(MatchArg.equals("url", url));
			Transaction.commit();
			return bean[0];
			
		}

		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}

	public void add(Favorite fav) throws RollbackException {
		try {
			Transaction.begin();

			createAutoIncrement(fav);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	/*
	 * public void delete(int id) throws RollbackException { try {
	 * Transaction.begin(); //Favorite p = read(id);
	 * 
	 * 
	 * delete(id); Transaction.commit(); } finally { if (Transaction.isActive())
	 * Transaction.rollback(); } }
	 */
	public void delete(Favorite fav) throws RollbackException {
		try {
			Transaction.begin();
			delete(fav.getId());
			Transaction.commit();
		}

		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

}
