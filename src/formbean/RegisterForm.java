//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean{
    private String userEmail;
    private String password;
    private String userFirstName;
    private String userLastName;
	private String confirm ;

	
    public String getUserEmail()  { return userEmail; }
    public String getPassword()  { return password; }
	public String getConfirm()   { return confirm;   }
	public String getUserFirstName()  { return userFirstName; }
    public String getUserLastName()  { return userLastName; }
    


	
    public void setUserEmail(String s)  { userEmail = s.trim(); }
    public void setPassword(String s)  { password = s.trim(); }
	public void setConfirm(String s)   { confirm   = s.trim();                  }
	public void setUserFirstName(String s)  { userFirstName = s.trim(); }
    public void setUserLastName(String s)  { userLastName = s.trim(); }
    



    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (userFirstName == null || userFirstName.length() == 0) errors.add("User firstname is required");
        if (userLastName == null || userLastName.length() == 0) errors.add("User lastname is required");
        if (userEmail == null || userEmail.length() == 0) errors.add("User email is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}
	
		
		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
		
     if (errors.size() > 0) return errors;
	return errors;

		
    }
}
