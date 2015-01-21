<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-cus.jsp" />

<div class="row">
	<div class="col-lg-12">
		<h2 class="page-header">Sell Funds</h2>
	</div>
</div>

	<div class="col-lg-6">

		<div class="form-group">

			<label>Select the fund you want to sell:</label>

			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Fund</th>
						<th>Ticker</th>
						<th>Shares held</th>
						<th>Shares to sell</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="fund" items="${fundList}">
						
						<form action="depositCheck.do" method="post">
							<tr>
								<td>${ fund.getName() }</td>
								<td>${ fund.getTicker() }</td>
								<td>${ fund.getShares() }</td>
								<td>
									 <input name="shares"  value="0"  class="form-control" >
									 <input name="fundId"  type="hidden" value="${ fund.getFundId() }"  class="form-control" >
							 		<button type="submit" name="action" value="sell" 	class="btn btn-primary">Sell!</button>
								</td>
								</tr>
							</form>
						
					</c:forEach>
				</tbody>
			</table>
		</div>

</body>
</html>