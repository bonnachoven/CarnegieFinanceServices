 <% /*Name:Xu Zhao
  Andrew ID:xuzhao
  Course Number:08600
  Date:12/05/2014
*/ %> 
<%@page import="java.util.List"%>
<%@page import="databean.Favorite"%>

<%@page import="databean.User"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Favorite -- Error Page</title>
    </head>
    
	<body>
	
		<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />

<p>
    <a href="login.do">Back to login</a>
</p>

<jsp:include page="template-bottom.jsp" />

	
	</body>
</html>