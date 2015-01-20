<!-- 
Hua-Ming Lee
huamingl
08-600
2014/12/1
!-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>

	<title>Carnegie Finance System</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
	
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js">
	</script>
<meta charset="UTF-8">

</head>



<body>
<%@ page import="databean.UserBean" %>
<div class="container">
		<div class="row">
			<nav class="navbar navbar-default" role="main">
				<div class="navbar-header">
				<a class="navbar-brand" href="#">Carnegie Finance System</a>				
				</div>
				<div>
					<p class="navbar-text">Buy and sell funds here!</p>
				</div>
				<div>
				</div>
			</nav>
		</div>
</div>

<div class="row">
			<div class="col-md-2" role="complementary">
				<div class="sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">Account</li>
						<li class="menu-item"><a href="change-pwd.do">Change Password</a></li>
						<li><a href="#">Create Employee Account</a></li>
						<li><a href="#">Create Customer Account</a></li>
						<li><a href="#">Reset Customer Password</a></li>
						<li><a href="#">View Account</a></li>
						
					</ul>
					<ul class="nav nav-list">
						<li class="nav-header">Transactions</li>
						<li class="active"><a href="#">Transaction History</a></li>
						<li><a href="#">Transition Day</a></li>
					</ul>

					<ul class="nav nav-list">
						<li class="nav-header">Funds</li>
						<li class="active"><a href="#">Create Fund</a></li>
					</ul>

					<ul class="nav nav-list">
						<li class="nav-header">Payment</li>
						<li class="active"><a href="#">Desposit Check</a></li>
					</ul>

				</div>
			</div>
		
		<td>
			<!-- Padding (blank space) between navbar and content -->
		</td>
		<td  valign="top">
