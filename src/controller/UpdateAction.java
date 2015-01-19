//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;

import databean.Favorite;
import databean.User;

public class UpdateAction extends Action {

	private FavoriteDAO favoriteDAO;
	private UserDAO userDAO;

	public UpdateAction(Model model) {
		favoriteDAO = model.getFavoriteDAO();
		userDAO = model.getUserDAO();
	}

	public String getName() {
		return "update.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			request.setAttribute("userList", userDAO.getUsers());

			int favoriteid = Integer.parseInt(request.getParameter("id"));

			Favorite favorite_to_change = favoriteDAO.read(favoriteid);

			favorite_to_change
					.setClickCount(favorite_to_change.getClickCount() + 1);

			favoriteDAO.update(favorite_to_change);

			int userid = favorite_to_change.getUserId();
			Favorite[] favoriteList = favoriteDAO.getFavoriteList(userid);

			if (request.getSession(false).getAttribute("user") == null) {
				request.setAttribute("favoriteList", favoriteList);
				return "list.jsp";
			} else {
				User userbean = (User) request.getSession(false).getAttribute(
						"user");
				if (userbean.getUserId() == userid) {
					return "add.do";
				} else {

					request.setAttribute("favoriteList", favoriteList);
					return "list.jsp";
				}
			}

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}

	}

}