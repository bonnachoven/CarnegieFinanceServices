package formbean;

import java.util.ArrayList;
import java.util.List;

public class SellFundForm {
	private String fundId;
	private String action;
	private String shares;
	
	public String getFundId ()			{return fundId; }
	public String getAction ()			{ return action;}
	public String getShares ()			{return shares; }
	
	public void setFundId(String s)				{ fundId = s; }
	public void setAction(String s) 		    { action  = s;}
	public void setShares(String s)				{ shares = s; }
	
	public List<String> getValidationErrors () {
		List<String> errors = new ArrayList<String>();

		if (shares == null || shares.length() == 0)
			errors.add("Shares to sell is required");
		if (action == null) errors.add("Button is required");

		if (errors.size() > 0) 	return errors;
        if (!action.equals("sell")) errors.add("Invalid button");
        
    	try {
        	double d = Double.parseDouble(shares);
        	if (d <= 0 || d > Integer.MAX_VALUE) {
        		throw new Exception();
        	}
        } catch (Exception e) {
        	 errors.add("Shares should not be a negative number");
        }
    
		return errors;
	}	
}
