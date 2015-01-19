//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.FavoriteDAO;
import model.Model;
import model.UserDAO;
import databean.Favorite;
import databean.User;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());

		try {
			createSampleUser(model);
		} catch (RollbackException e) {
			e.printStackTrace();
		}

		Action.add(new ChangePwdAction(model));
		Action.add(new LoginAction(model));
		Action.add(new LogoutAction(model));
		Action.add(new RegisterAction(model));
		Action.add(new ListAction(model));
		Action.add(new AddAction(model));
		Action.add(new UpdateAction(model));
		Action.add(new DeleteAction(model));

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		User user = (User) session.getAttribute("user");
		String action = getActionName(servletPath);

		// System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

		if (action.equals("register.do") || action.equals("login.do")
				|| action.equals("list.do") || action.equals("update.do")) {
			// Allow these actions without logging in
			return Action.perform(action, request);
		}

		if (user == null) {
			// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do", request);
		}

		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
					+ nextPage);
			d.forward(request, response);
			return;
		}

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}

	private void createSampleUser(Model model) throws RollbackException {
		UserDAO userDAO;
		FavoriteDAO favoriteDAO;

		userDAO = model.getUserDAO();
		favoriteDAO = model.getFavoriteDAO();

		if (userDAO.getCount() == 0) {
			// create the users and favorites
			for (int i = 1; i < 4; i++) {
				User u1 = new User();
				u1.setUserEmail("User" + i + "@cmu.edu");
				u1.setUserFirstName("User");
				u1.setUserLastName("" + i + "");
				u1.setPassword("password");
				userDAO.createAutoIncrement(u1);
				for (int x = 1; x < 5; x++) {
					Favorite f1 = new Favorite();
					f1.setUrl("http://www.cmu.edu");
					f1.setComment("Sample url" + x + " My school");
					f1.setUserId(u1.getUserId());
					favoriteDAO.createAutoIncrement(f1);
				}
			}
		}

	}

}
