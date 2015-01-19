 <% /*Name:Xu Zhao
  Andrew ID:xuzhao
  Course Number:08600
  Date:12/05/2014
*/ %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="template-top.jsp" />



<jsp:include page="error-list.jsp" />
<%@ page import="databean.Favorite" %>

<p>
	<form method="post" action="add.do" >
		<table>
			<tr>
				<td>URL</td>
				<td colspan="2">
					<input id="url" type="text" size="40" name="url"/>
				</td>
			</tr>
			<tr>
				<td>Comment: </td>
				<td colspan="2">
					<input id="comment" type="text" size="40" name="comment"/>
				</td>
			</tr>
			 <tr>
			 	<td colspan="3" style="text-align: center;">
			 		<input type="submit" name="action" value="Add Favorite" />
			 	</td>
		      </tr>
		</table>
	</form>
</p>


<p>

		<c:set var="favorite" value="${favorites}"/>        
		<p style="font-size: x-large">${fn:length(favorites)} favorite websites.</p>

		<table >      
        	<c:forEach var="favorite" items="${favorites}">
      
           		<tr>
	           		<td>
				    	<form action="delete.do" method="POST">
	                	<input type="hidden" name="id" value="${favorite.id}" />
	                	<input type="hidden" name="url" value="${favorite.url}" />
						<input type="submit" name="button" value="X" />
	           			</form>
	        		</td>
       			
			    </tr>	
			    
        	    <td style="font-size: x-large">
			    <a href="update.do?id=${favorite.id}" onclick = "javascript:window.open('${favorite.url}')">${favorite.url}</a>
			    </td>
			 
			    <tr>
			    	<td style="font-size: x-large">${favorite.comment}</td>
			    	<td style="font-size: x-large">${favorite.clickCount} clicks!</td>
			    </tr>
			   
			  </c:forEach>   			
		
		
		</table>   
</p>

<jsp:include page="template-bottom.jsp" />
