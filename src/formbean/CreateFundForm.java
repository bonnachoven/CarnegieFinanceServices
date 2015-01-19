//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean{
    private String fundName;
    private String ticker;

	
    public String getFundName()  { return fundName; }
    public String getTicker()  { return ticker; }

    


	
    public void setFundName(String s)  { fundName = s.trim(); }
    public void setTicker(String s)  { ticker = s.trim(); }




    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (fundName == null || fundName.length() == 0) errors.add("Fund name is required");
        if (ticker == null || ticker.length() == 0) errors.add("Ticker is required");
        if (ticker.length() > 4) errors.add("Ticker name is between 1 to 4 characters");
      
		
     if (errors.size() > 0) return errors;
	return errors;

		
    }
}
