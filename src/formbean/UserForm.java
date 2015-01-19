//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class UserForm extends FormBean {
	private String userEmail = "";
	
	public String getUserEmail()  { return userEmail; }
	
	public void setUserName(String s)  { userEmail = trimAndConvert(s,"<>>\"]"); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userEmail == null || userEmail.length() == 0) {
			errors.add("User Name is required");
		}
		
		return errors;
	}
}
