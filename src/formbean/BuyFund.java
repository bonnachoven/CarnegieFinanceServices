package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFund extends FormBean {
	private String amount;
	private String ticker;
	private String name;

	public String getCheckAmount() {
		return amount;
	}
	public String getFundName() {
		return name;
	}
	public String getTicker() {
		return ticker;
	}


	public int getCheckAmountAsInt() {
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be
		// thrown!
		return Integer.parseInt(amount);
	}

	public void setCheckAmount(String amount) {
		this.amount = amount;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (amount == null || amount.length() == 0) {
			errors.add("Amount is required");
			return errors;
		}

		try {
			int amt = Integer.parseInt(amount);
			if (amt < 0) {
				errors.add("Amount cannot be negative!");
				
			}
		} catch (NumberFormatException e) {
			errors.add("Amount is not an integer");
		}
		return errors;

	}

}