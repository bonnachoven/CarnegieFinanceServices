//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
//github test
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.User;
import formbean.RegisterForm;

/*
 * Processes the parameters from the form in register.jsp.
 * If successful:
 *   (1) creates a new User bean
 *   (2) sets the "user" session attribute to the new User bean
 *   (3) redirects to view the originally requested photo.
 * If there was no photo originally requested to be viewed
 * (as specified by the "redirect" hidden form value),
 * just redirect to manage.do to allow the user to add some
 * photos.
 */
public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);

	private UserDAO userDAO;
	private FavoriteDAO favoriteDAO;

	
	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
		favoriteDAO = model.getFavoriteDAO();

	}

	public String getName() { return "register.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
			request.setAttribute("userList",userDAO.getUsers());

	        
	
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "register.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "register.jsp";
	        }
	        
	
	        // Create the user bean
	        User user = new User();
	        user.setUserEmail(form.getUserEmail());
	        user.setUserFirstName(form.getUserFirstName());
	        user.setUserLastName(form.getUserLastName());
	        user.setPassword(form.getPassword());
	       User[] users = userDAO.match(MatchArg.equals("userEmail",form.getUserEmail()));
			if (users.length > 0) {
				errors.add("User existed!");
				return "register.jsp";
			} else {
				userDAO.createAutoIncrement(user);			}


			// Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user",user);
	        
			return "add.do";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        } catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "register.jsp";
		}
		
        
    }
}
