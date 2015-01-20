

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

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
	        
	        CustomerAccount acnt= new CustomerAccount();
	        
	        
	        
	      
	     /*  Favorite fav = new Favorite();
	        fav.setUrl(form.getUrl());
	        fav.setComment(form.getComment());
       		fav.setClickCount(0);
       		fav.setUserId(user.getUserId());
       		
       		
       		favoriteDAO.add(fav);*/

       	
       		
       		//request.setAttribute("favorites", favoriteDAO.getUserFavorites(user.getUserId()));
       		
			//return "add.do";
	        request.setAttribute("message","Thank You! Your request will be processed after the next transition day. ");
	        return "success.jsp";

        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
