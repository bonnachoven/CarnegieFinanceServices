package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.Customer;
import databean.TransactionBean;


public class TransactionDAO extends GenericDAO<TransactionBean> {
	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(TransactionBean.class, tableName, cp);
	}
	
	public double getValidBalance (String customerId, double amount) throws RollbackException {
		TransactionBean[] tbs = null;
		try {
			Transaction.begin();
			
			// How to execute select * from table where transactionType IS NULL
			tbs =  match(MatchArg.equals("executeDate", null), MatchArg.equals("customerId", customerId));
			
			if (tbs != null) {
				for (TransactionBean t : tbs) {
					switch(t.getTransactionType()) {
					case 4:
						break;
					case 3:
						amount -= t.getAmount() / 100.00;
						break;
					case 2:
						amount -= t.getAmount() / 100.00;
						break;
					case 1:
						amount += t.getAmount() / 100.00;
						break;	
					default:
						break;
					}
				}
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		return amount;
	}
	
	
	public double getValidShares (int customerId, double shares) throws RollbackException {
		TransactionBean[] tbs = null;
		try {
			Transaction.begin();
			
			// How to execute select * from table where transactionType IS NULL
			tbs =  match(MatchArg.equals("executeDate", null), MatchArg.equals("customerId", customerId));
			
			if (tbs != null) {
				for (TransactionBean t : tbs) {
					switch(t.getTransactionType()) {
					case 3:
						shares -= t.getShares() / 1000.0;
						break;
					default:
						break;
					}
				}
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		return shares;
	}
	
	public TransactionBean[] getAllPendingTrans () throws RollbackException {
		TransactionBean[] tbs = null;
		try {
			Transaction.begin();
			
			// How to execute select * from table where transactionType IS NULL
			tbs =  match(MatchArg.equals("executeDate", null));
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		return tbs;
	}
	
	public String getLastDate(Customer c) throws RollbackException{
		TransactionBean[] transaction = match(MatchArg.notEquals("executeDate", null), MatchArg.equals("customerId", c.getCustomerId()));
		if(transaction.length == 0) return null;
		Arrays.sort(transaction);
		return transaction[transaction.length-1].getExecuteDate();
	}
	
	public Date getLatestDate () throws RollbackException, ParseException {
		Date date = null;
		try {
			Transaction.begin();
		
			TransactionBean[] t =  match(MatchArg.notEquals("executeDate", null));
			if (t != null && t.length != 0) {
				Arrays.sort(t);
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setLenient(false);
				date = dateFormat.parse(t[t.length - 1].getExecuteDate());
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		return date;
	}

	public TransactionBean[] getTransactions(String customerId) throws RollbackException {
		TransactionBean[] list = match(MatchArg.equals("customerId", customerId));
		// Arrays.sort(list);
		return list;
		
	}
}