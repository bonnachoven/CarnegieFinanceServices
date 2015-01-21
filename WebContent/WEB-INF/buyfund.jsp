<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-cus.jsp" />

<div class="row">
	<div class="col-lg-12">
		<h2 class="page-header">Buy Funds</h2>
	</div>
</div>

<form>
	<label>Select the fund you want to buy:</label>
	<div class="form-group">
		<!-- Add the account stocks below -->
		<select class="form-control" name="fund">
			<option></option>

			<c:choose>
				<c:when test="${ (empty fundList) }"></c:when>
				<c:otherwise>
					<c:forEach var="u" items="${ fundList }">
						<option>${ u.getName() }</option>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</select> 
	</div>
	<label>Amount to Buy:</label>
	<div class="form-group input-group">
		<span class="input-group-addon">$</span>
		<!-- Add the account amount below -->
		<input type="text" class="form-control" name="amount"
			placeholder="Type only numbers in format 1000.00">
	</div>
	<br>
	<button type="submit" class="btn btn-primary">Buy!</button>
</form>
</body>
</html>