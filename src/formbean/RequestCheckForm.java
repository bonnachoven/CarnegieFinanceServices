package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	private String amount;

	public String getCheckAmount() {
		return amount;
	}
// hope this would not create a problem
	public double getCheckAmountAsDouble() {
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be
		// thrown!
		return Double.parseDouble(amount);
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
		 Double amt = Double.parseDouble(amount);
			if (amt < 0) {
				errors.add("Amount cannot be negative!");
				
			}
		} catch (NumberFormatException e) {
			errors.add("Amount is not an integer");
		}
		return errors;

	}

}
