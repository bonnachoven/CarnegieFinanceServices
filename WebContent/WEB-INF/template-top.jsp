 <% /*Name:Xu Zhao
  Andrew ID:xuzhao
  Course Number:08600
  Date:12/05/2014
*/ %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>Carnegie Finance System</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
</head>

<body>
<%@ page import="databean.Fund" %>

<table cellpadding="4" cellspacing="0">
    <tr>
	    <!-- Banner row across the top -->
        <td width="130" bgcolor="#99FF99">
        
        <td bgcolor="#99FF99">&nbsp;  </td>
        <td width="500" bgcolor="#99FF99">
            <p align="center">
            
        <font size="6">Favorite Website</font>

			</p>
		</td>
    </tr>
	
	<!-- Spacer row -->
	<tr>
		<td bgcolor="#99FF99" style="font-size:5px">&nbsp;</td>
		<td colspan="2" style="font-size:5px">&nbsp;</td>
	</tr>
	

		<td bgcolor="#99FF99" valign="top" height="500">
			<!-- Navigation bar is one table cell down the left side -->
            <p align="left">

	         
	         <c:set var="user" value="${user}" />
	         
	         <c:choose>
	         	<c:when test="${user==null}">
	         		<span class="menu-item"><a href="login.do">Login</a></span><br/>
					<span class="menu-item"><a href="register.do">Register</a></span><br/>
	         	</c:when>
	         	<c:otherwise>
	         		         		<span class="menu-head">${user.userFirstName} ${user.userLastName}</span><br/>
	         	
					<span class="menu-item"><a href="add.do">Manage Your Favorites</a></span><br/>
					<span class="menu-item"><a href="change-pwd.do">Change Password</a></span><br/>
					<span class="menu-item"><a href="logout.do">Logout</a></span><br/>
	         	</c:otherwise>
	         	
	         </c:choose>

				<span class="menu-item">&nbsp;</span><br/>
				<span class="menu-head">Favorites From:</span><br/>
				
				<c:forEach var="usr" items="${userList}">
			    <span class="menu-item">
			    
					<a href="list.do?email=${usr.userEmail}">${usr.userFirstName} ${usr.userLastName}</a>
				</span>
				<br/>
				</c:forEach>
				
     
	       
	

			</p>
		</td>
		<td>
			<!-- Padding (blank space) between navbar and content -->
		</td>
		<td  valign="top">
