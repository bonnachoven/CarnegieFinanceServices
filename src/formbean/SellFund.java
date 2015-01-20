package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFund extends FormBean {
	private String share;
	private String ticker;
	private String name;

	public String getShareAmount() {
		return share;
	}
	public String getFundName() {
		return name;
	}
	public String getTicker() {
		return ticker;
	}


	public int getShareAmountAsInt() {
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be
		// thrown!
		return Integer.parseInt(share);
	}

	public void setShareAmount(String share) {
		this.share = share;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (share == null || share.length() == 0) {
			errors.add("Share amount is required");
			return errors;
		}

		try {
			int shr = Integer.parseInt(share);
			if (shr < 0) {
				errors.add("Share amount cannot be negative!");
				
			}
		} catch (NumberFormatException e) {
			errors.add("Share amount is not an integer");
		}
		return errors;

	}

}

