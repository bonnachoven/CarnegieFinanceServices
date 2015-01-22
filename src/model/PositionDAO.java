package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.Position;

public class PositionDAO extends GenericDAO<Position> {
	
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Position.class, tableName, cp);
	}
	
	public Position[] getPosition() throws RollbackException {
		Position[]position = match();
		// We want them sorted by last and first names (as per
		// User.compareTo());
		return position;
	}
	
	public Position[] getFunds(int id) throws RollbackException {
		Position[]funds = match(MatchArg.equals("customerId", id));
		// We want them sorted by last and first names (as per
		// User.compareTo());
		return funds;
	}
	
	
}
