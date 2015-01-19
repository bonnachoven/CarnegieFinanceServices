//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.Model;
import model.UserDAO;



/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {
	private UserDAO userDAO;

	public LogoutAction(Model model) { 		userDAO = model.getUserDAO();}

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
		try {
			request.setAttribute("userList",userDAO.getUsers());
			request.setAttribute("message","You are now logged out");

	        session.setAttribute("user",null);

	        return "login.jsp";
		} catch (RollbackException e) {
			e.printStackTrace();
		}

		return "login.jsp";
	
    }
}
