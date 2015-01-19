 <% /*Name:Xu Zhao
  Andrew ID:xuzhao
  Course Number:08600
  Date:12/05/2014
*/ %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:forEach var="error" items="${errors}">
	<c:if test="${error!= null && fn:length(error) > 0}">
		<p style="font-size:medium; color:red">${error}<br></p> 
	</c:if>
	
</c:forEach>