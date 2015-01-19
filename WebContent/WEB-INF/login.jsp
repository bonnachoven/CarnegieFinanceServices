 <% /*Name:Xu Zhao
  Andrew ID:xuzhao
  Course Number:08600
  Date:11/22/2014
*/ %> 
<%@page import="java.util.List"%>
<%@page import="databean.Favorite"%>
<%@page import="databean.User"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Login Page</title>
    </head>
    
	<body>
		<jsp:include page="error-list.jsp" />
	
	<jsp:include page="template-top.jsp" />

	
		<h2>Login</h2>

<p style="font-size:medium">
	Please login below or click <a href="register.do">here</a> to register as a new user.
</p>


		<form method="post" action="login.do">
		    <table>
		        <tr>
		            <td style="font-size: x-large">User Email:</td>
		            <td>
		                <input type="text" name="userEmail" value="${form.userEmail}" />
		            </td>
		        </tr>
		        <tr>
		            <td style="font-size: x-large">Password:</td>
		            <td><input type="password" name="password" /></td>
		        </tr>
		        <tr>
		            <td colspan="2" style="text-align: center;">
		                <input type="submit" name="button" value="Login" />
		            </td>
		        </tr>
			</table>
		</form>
	</body>
</html>