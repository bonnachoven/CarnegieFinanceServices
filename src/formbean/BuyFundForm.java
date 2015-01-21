package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean {
	private String amountAsString;
	private String action;
	private int customerId;
	private long amount;
	private int fundId;
	private Long shares;

	public long getAmount() {
		return amount;
	}

	public String getAction() {
		return action;
	}
	
	public int getFundID() {
		return fundId;
	}
	
	public Long getShares() {
		return shares;
	}
	
	public void setAmount(String a) {
		amount = Long.parseLong(sanitize(a.trim()));
	}

	public void setAction(String s) {
		action = s;
	}
	
	public void setFundID(String fundId) {
		this.fundId = Integer.parseInt(fundId); 
	}
	
	public void setShares(String shares) {
		this.shares = Long.parseLong(shares); 
	}
	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (amountAsString == null || amountAsString.length() == 0) {
			errors.add("Amount is required.");
		}

		if (errors.size() > 0) {
			return errors;
		}

		if (action == null || action.length() == 0) {
			errors.add("Action is required.");
		}

		if (errors.size() > 0)
			return errors;

		if (!action.equals("Buy"))
			errors.add("Invalid action.");

		try {
			Long.parseLong(sanitize(amountAsString.trim()));
		} catch (NumberFormatException e) {
			errors.add("invalid amount input.");
		}

		return errors;
	}

	private String sanitize(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;")
				.replace(">", "&gt;").replace("\"", "&quot;");
	}
}
