package formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean {
	private String amount;
	private String fund;

	public String getAmount() {
		return amount;
	}
	
	public String getFund() {
		return fund;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}

	
	public void setFund(String fund) {
		this.fund = fund; 
	}
	
	public boolean checkDecimal(String input) {
		Pattern p = Pattern.compile("[+-]?[0-9]+.{0,1}[0-9]{0,2}");
		return p.matcher(input).matches();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (amount == null || amount.length() == 0) {
			errors.add("Amount is required.");
		}
		if (fund == null || fund.length() == 0) {
			errors.add("Please choose a fund");
		}
		
		if(!checkDecimal(amount)){
			errors.add("Only numbers with a maximum of 2 decimals places are allowed for amount.");
		}
		
		if (errors.size() > 0)
			return errors;

		try {
			System.out.println("amount is"+amount);
			long amt = Long.parseLong(amount);
			amt = Math.round(amt*100);
			amt=amt/100;
		} catch (NumberFormatException e) {
			errors.add("Invalid amount input.");
		}

		return errors;
	}
}
