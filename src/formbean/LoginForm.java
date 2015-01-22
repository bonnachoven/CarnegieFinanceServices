//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package formbean;


import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean{
    private String userName;
    private String userEmail;
    private String password;
    private int userId;
    private String type;
	
    public String getUserEmail()  { return userEmail; }
    public String getUserName()  { return userName; }
    public String getPassword()  { return password; }
    public int getUserId() {	return userId; }
    public String getType() {	return type;}
    
    
    public void setUserName(String s)  { userName = s.trim(); }
    public void setUserEmail(String s)  { userEmail = s.trim(); }
    public void setPassword(String s)  { password = s.trim(); }
    public void setUserId(int t) {	userId = t; }
    public void setType(String s) { type = s.trim();}
    
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (userName == null || userName.length() == 0) errors.add("User Name is required");
        if (password == null || password.length() == 0) errors.add("Password is required");

        if (errors.size() > 0) return errors;

        if (userName.matches(".*[<>\"].*")) errors.add("User Name may not contain angle brackets or quotes");

        return errors;
    }
}
