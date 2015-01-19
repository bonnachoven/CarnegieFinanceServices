//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import formbean.IdForm;
import model.FavoriteDAO;
import model.Model;

public class DeleteAction extends Action {
	private FormBeanFactory<IdForm> idFormFactory = FormBeanFactory.getInstance(IdForm.class);
	
	private FavoriteDAO favoriteDAO;

	public DeleteAction(Model model) {
		favoriteDAO = model.getFavoriteDAO();
	}

	public String getName() { return "delete.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();

        request.setAttribute("errors",errors);
        
        
        try {
	        IdForm form = idFormFactory.create(request);
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	return "error.jsp";
	        }
	        
	    	favoriteDAO.delete(form.getIdAsInt());
       		request.setAttribute("favorite", favoriteDAO.getUserFavorites(form.getIdAsInt()));
	
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
