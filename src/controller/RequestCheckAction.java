

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Customer;
import databean.CustomerAccount;
import databean.Favorite;
import databean.User;
import formbean.FavoriteForm;
import formbean.RequestCheckForm;
import model.CustomerAccountDAO;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

public class RequestCheckAction extends Action {
	private FormBeanFactory<RequestCheckForm>  RequestCheckFormFactory  = FormBeanFactory.getInstance(RequestCheckForm.class);
	
	private CustomerAccountDAO accountDAO;

	public RequestCheckAction(Model model) {
		accountDAO = model.getCustomerAccountDAO();
		//userDAO = model.getUserDAO();

	}

	public String getName() { return "requestCheck.do"; }
    
    public String perform(HttpServletRequest request) {
    	 HttpSession session = request.getSession();
         User user = (User) session.getAttribute("user");
         List<String> errors = new ArrayList<String>();
         request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		
	        RequestCheckForm form = RequestCheckFormFactory.create(request);
	        
			//request.setAttribute("userList",userDAO.getUsers());
			//request.setAttribute("favorites", favoriteDAO.getUserFavorites(user.getUserId()));
	        
	        if (!form.isPresent()) {
        
        		return "customerRequestCheck.jsp" ;
	        }
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
             
	        	return"customerRequestCheck.jsp";
	        }
	        
	        Customer acnt= new Customer();
	        /*
	        if after transaction day : balance is not negative then:
	          Double amt= form.getCheckAmountAsDouble();
	        Double bal= acnt.getCash();

	        if(bal-amt>0){
	        bal = bal-amt;
	        acnt.setCash(bal);
	        
	 //and then update in  the db
	        }else{
	        	errors.add("Your account balance is too low to withdraw a check");
	        	   if after transaction day : balance is  negative then:
	        	   errors.add("You dont have sufficient balance to withdraw a check");
	        }*/

	        	       
	        
	        
	      
	     
	        request.setAttribute("message","Thank You! Your request will be processed after the next transition day. ");
	        return "success.jsp";

        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
