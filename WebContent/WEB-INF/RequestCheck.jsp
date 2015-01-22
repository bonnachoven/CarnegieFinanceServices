


<jsp:include page="template-cus.jsp" />

<jsp:include page="error-list.jsp" />

<p>
<form method="post" action="requestCheck.do">
	<table>
		<tr>
			<td>Enter Amount:</td>
			<td><input type="text" name="amount" /></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center"><input type="submit"
				name="requestCheck" value="Process Request" /></td>
		</tr>
	</table>
</form>
</p>

