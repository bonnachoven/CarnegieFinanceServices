
<%@page import="java.util.List"%>
<%@page import="databean.Fund"%>

<!DOCTYPE html>
<html>
 
<body>
<jsp:include page="error-list.jsp" />

<jsp:include page="template-top.jsp" />




<form method="post">
		<input type="hidden" name="redirect" value="${redirect}"/>

		    <table>
		        <tr>
		            <td style="font-size: x-large">User Email:</td>
		            <td>
		                <input type="text" name="userEmail" value="${form.userEmail}" />
		            </td>
		        </tr>
		        <tr>
		            <td style="font-size: x-large">First Name:</td>
		            <td>
		                <input type="text" name="userFirstName" value="${form.userFirstName}" />
		            </td>
		        </tr>
		        <tr>
		            <td style="font-size: x-large">Last Name:</td>
		            <td>
		                <input type="text" name="userLastName" value="${form.userLastName}" />
		            </td>
		        </tr>

		        <tr>
		            <td style="font-size: x-large">Password:</td>
		            <td><input type="password" name="password" /></td>
		        </tr>
		        <tr>
				<td style="font-size: x-large">Confirm Password: </td>
				<td><input type="password" name="confirm" value=""/></td>
			</tr>
		        <tr>
		            <td colspan="2" style="text-align: center;">
		                <input type="submit" name="button" value="Register" />
		            </td>
		        </tr>
			</table>
		</form>
	
<jsp:include page="template-bottom.jsp" />
	</body>
</html>