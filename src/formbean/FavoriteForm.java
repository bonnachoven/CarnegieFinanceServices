//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class FavoriteForm extends FormBean {
	private String url;
	private String comment;
	private int id;
	
	public String getUrl()   { return url;   }
	public String getComment()   { return comment;   }
	public int getId()   { return id;   }


	
	public void setUrl(String s)   { url   = trimAndConvert(s,"<>\""); }
	public void setComment(String s)   { comment   = trimAndConvert(s,"<>\""); }
	public void setId(int i)   { id=i; }




	   public List<String> getValidationErrors() {
	        List<String> errors = new ArrayList<String>();

	        if (url == null || url.length() == 0) {
	        	errors.add("URL is required");
	        	}else if (!url.startsWith("http://") && !url.startsWith("HTTP://")) {
	        		errors.add("URL should starts with http://");
	        	}
	        if (comment == null || comment.length() == 0) errors.add("Comment is required");
	    

	        if (errors.size() > 0) return errors;

	        
	        return errors;
   }
}
