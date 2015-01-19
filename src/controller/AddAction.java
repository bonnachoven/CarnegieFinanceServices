//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Favorite;
import databean.User;
import formbean.FavoriteForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

public class AddAction extends Action {
	private FormBeanFactory<FavoriteForm>  favoriteFormFactory  = FormBeanFactory.getInstance(FavoriteForm.class);
	
	private FavoriteDAO favoriteDAO;
	private UserDAO userDAO;

	public AddAction(Model model) {
		favoriteDAO = model.getFavoriteDAO();
		userDAO = model.getUserDAO();

	}

	public String getName() { return "add.do"; }
    
    public String perform(HttpServletRequest request) {
    	 HttpSession session = request.getSession();
         User user = (User) session.getAttribute("user");
         List<String> errors = new ArrayList<String>();
         request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		
	        FavoriteForm form = favoriteFormFactory.create(request);
	        
			request.setAttribute("userList",userDAO.getUsers());
			request.setAttribute("favorites", favoriteDAO.getUserFavorites(user.getUserId()));
	        
	        if (!form.isPresent()) {
        
        		return "favorite.jsp" ;
	        }
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
             
	        	return"favorite.jsp";
	        }
	        
	        Favorite fav = new Favorite();
	        fav.setUrl(form.getUrl());
	        fav.setComment(form.getComment());
       		fav.setClickCount(0);
       		fav.setUserId(user.getUserId());
       		
       		
       		favoriteDAO.add(fav);

       	
       		
       		request.setAttribute("favorites", favoriteDAO.getUserFavorites(user.getUserId()));
       		
			return "add.do";

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
