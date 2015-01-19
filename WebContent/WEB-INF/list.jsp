 <% /*Name:Xu Zhao
  Andrew ID:xuzhao
  Course Number:08600
  Date:12/05/2014
*/ %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />

<%@ page import="databean.Favorite" %>

<p>
	<table>
		<c:forEach var="favorite" items="${favoriteList}">
			<tr>
				<td>
			    <a href="update.do?id=${favorite.id}" onclick = "javascript:window.open('${favorite.url}')">${favorite.url}</a>
				</td>
			</tr>
			<tr>
				<td>${favorite.comment}</td>
			</tr>
			<tr>
				<td>${favorite.clickCount} clicks!</td>
			</tr>
		</c:forEach>
	
	</table>

</p>
<jsp:include page="template-bottom.jsp" />
