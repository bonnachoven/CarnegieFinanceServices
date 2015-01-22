package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import databean.Customer;
import databean.FundDisplay;
import databean.Position;
import databean.TransactionBean;
import formbean.SellFundForm;

public class SellFundAction  extends Action {
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory.getInstance(SellFundForm.class);

	private FundDAO fundDAO;
	private CustomerDAO  customerDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	
	public SellFundAction(Model model) {
		fundDAO = model.getFundDAO();
    	customerDAO  = model.getCustomerDAO();
    	positionDAO=model.getPositionDAO();
    	transactionDAO=model.getTransactionDAO();
	}

	public String getName() { return "sellFund.do"; }

    public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			SellFundForm form = formBeanFactory.create(request);
			if (session.getAttribute("user") != null	&& session.getAttribute("user") instanceof Customer) {
				
				//CHECK FOR THE NUMBER OF SHARES
				Customer customer = (Customer) request.getSession(false).getAttribute("user");

				DecimalFormat df3 = new DecimalFormat("#,##0.000");

				FundDisplay[] fundList = null;
				Position[] positionList = positionDAO.getFunds(customer.getCustomerId());
				
				if (positionList != null && positionList.length > 0) {
					fundList = new FundDisplay[positionList.length];

					for (int i = 0; i < positionList.length; i++) {
						fundList[i].setFundId(positionList[i].getFundId());
						fundList[i].setFundName(fundDAO.read(positionList[i].getFundId()).getFundName());
						fundList[i].setTicker(fundDAO.read(positionList[i].getFundId()).getTicker());
						fundList[i].setShares(df3.format(transactionDAO.getValidShares(customer.getCustomerId() , positionList[i].getShares() / 1000.0)));
					}
				}
				
				request.setAttribute("fundList",fundList);
				
				if (!form.isPresent()) {
					return "sellFund.jsp";
				}
				
				errors.addAll(form.getValidationErrors());
			      
		        if (errors.size() > 0) return "sellFund.jsp";
	        
		        Double sharesToSell = Double.parseDouble(form.getShares());
		        Double sharesHeld =  transactionDAO.getValidShares(customer.getCustomerId() , 
		        		positionDAO.read(customer.getUsername(), Integer.parseInt(form.getFundId())).getShares() / 1000.0);
		        		
			
		        if (sharesToSell > sharesHeld) {
		        	errors.add("The shares to sell exceeds the shares held!");
		        }
		      			      
		        if (errors.size() > 0) return "sellFund.jsp";	        	
		        
		        TransactionBean transbean= new TransactionBean();
		        transbean.setCustomerId(customer.getCustomerId());
		        transbean.setFundId(Integer.parseInt(form.getFundId()));
		        transbean.setShares((long)(sharesToSell * 1000.0));
		        transbean.setTransactionType(3);
		        transbean.setExecuteDate(null);
		        transactionDAO.create(transbean);

		        return "sellFund.jsp";	
			} else {
				// logout and re-login
				if (session.getAttribute("user") != null)
					session.removeAttribute("user");

				return "login.do";
			}

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
    }
    
    
}
