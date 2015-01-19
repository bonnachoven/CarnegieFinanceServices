
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.FavoriteDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Fund;
import formbean.CreateFundForm;

/*
 * Processes the parameters from the form in createFund.jsp.
 * If successful:
 *   (1) creates a new fund bean
 *   (2) sets the "fund" session attribute to the new fund bean
 *   (3) redirects to view the originally requested photo.
 * If there was no photo originally requested to be viewed
 * (as specified by the "redirect" hidden form value),
 * just redirect to manage.do to allow the fund to add some
 * photos.
 */
public class CreateFundAction extends Action {
	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(CreateFundForm.class);

	private FundDAO fundDAO;

	
	public CreateFundAction(Model model) {
		fundDAO = model.getFundDAO();

	}

	public String getName() { return "createFund.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        CreateFundForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
			request.setAttribute("fund",fundDAO.getFunds());

	        
	
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "createFund.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "createFund.jsp";
	        }
	        
	
	        // Create the fund bean
	        Fund fund = new Fund();
	        fund.setFundName(form.getFundName());
	        fund.setTicker(form.getTicker());
	     
	       Fund[] funds = fundDAO.match(MatchArg.equals("fundName",form.getFundName()));
			if (funds.length > 0) {
				errors.add("fund existed!");
				return "createFund.jsp";
			} else {
				fundDAO.createAutoIncrement(fund);			}


			// Attach (this copy of) the fund bean to the session
	        HttpSession session = request.getSession(false);
	        session.setAttribute("fund",fund);
	        
			return "add.do";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "createFund.jsp";
        } catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "createFund.jsp";
		}
		
        
    }
}
