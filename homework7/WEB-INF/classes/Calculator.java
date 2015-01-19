import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calculator")
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String xString;
	private String yString;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String numberX = request.getParameter("numX");
		String numberY = request.getParameter("numY");
		xString=numberX;
		yString=numberY;
	
        if(numberX!=null){
        	try{
        		Double x=Double.parseDouble(numberX);
        	}catch(NumberFormatException e){
        		if(numberX.trim().equals("")){
    				out.println("<h2><font color=\"red\">X has no input!</font></h2>");
    			}
        		
        		else{
        			out.println("<h2><font color=\"red\"> X is not a number!</font></h2>");
        		}
        }
		
	    if(numberY!=null){
	    	try{
        	    Double y=Double.parseDouble(numberY);
        	}catch(NumberFormatException e){
        		if(numberY.trim().equals("")){
    				out.println("<h2><font color=\"red\">Y has no input!</font></h2>");
    			}else{
        		out.println("<h2><font color=\"red\">Y is not a number!</font></h2>");
    			}
        	}
	    }
		DecimalFormat df=new DecimalFormat("#,##0.00");
	   if(numberX!=null&&numberY!=null){
		try{
			
				Double x=Double.parseDouble(numberX);
				Double y=Double.parseDouble(numberY);
		
			    String button=request.getParameter("button");
			    		        if(button.equals("+")){
		        	out.println(df.format(x)+"+"+df.format(y)+"="+df.format(x+y));
		        }else if(button.equals("-")){
		        	out.println(df.format(x)+"-"+df.format(y)+"="+df.format(x-y));
		        }else if(button.equals("*")){
		        	out.println(df.format(x)+"*"+df.format(y)+"="+df.format(x*y));
		        }else if(button.equals("/")){
		        	
		        	if(y==0){
		        		out.println("<h2><font color=\"red\">Cannot divide by zero!</font></h2>");
		        	}else{
		        		out.println(df.format(x)+"/"+df.format(y)+"="+df.format(x/y));
		        	}
		        }
		        
		        
		}
			
		catch(NumberFormatException e){
				
			e.printStackTrace();
			}
			

			
		}
        
        }

		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
	
		out.println("<title>Calculator</title>");
		out.println("</head>");
		out.println("<body>");

		out.println("  <form method=\"GET\">");
       if ( xString !=null){
    	
		out.println("    X: <input type=\"text\" name=\"numX\" value=\""+xString+"\" >");
        }else{
        	out.println("    X: <input type=\"text\" name=\"numX\" value=\"\">");
        }
       if ( yString !=null){
       	
   		out.println("    Y: <input type=\"text\" name=\"numY\" value=\""+yString+"\" >");
           }else{
           	out.println("    Y: <input type=\"text\" name=\"numY\" value=\"\">");
           }
		
		out.println("    <input type=\"submit\" name=\"button\" value=\"+\"  >");
		out.println("    <input type=\"submit\" name=\"button\" value=\"-\" >");
		out.println("    <input type=\"submit\" name=\"button\" value=\"*\"  >");
	    out.println("    <input type=\"submit\" name=\"button\" value=\"/\"  >");
	    out.println("  </form>");
	
	
		out.println("<£¯body>");
	    out.println("<£¯html>");
	

	
		

	
	
	}
	
	
}

