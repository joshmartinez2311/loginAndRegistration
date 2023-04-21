<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<html>
<head>
<meta charset="UTF-8">
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Login and Registration Page</title>
</head>
<body>
	<div class="container">
		<div>
			<h1>Welcome</h1>
			<p>Please register and become a member of our growing community</p>
		</div>
		<div class="row">
			<div class="col">
				<form:form action="/register" method="post" modelAttribute="newUser">
					<div class="form-group">
						<h2>Register</h2>
						<form:label path="userName">Username:</form:label>
						<form:errors path="userName" class="text-danger"/>
						<form:input path ="userName" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="email">Email:</form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input path ="email" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="password">Password:</form:label>
						<form:errors path="password" class="text-danger"/>
						<form:input path ="password" type="password" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="confirmPassword">Confirm Password:</form:label>
						<form:errors path="confirmPassword" class="text-danger"/>
						<form:input path ="confirmPassword" type="password" class="form-control"/>
					</div>
					<button type="submit" class="btn btn-primary mt-3">Register</button>
				</form:form>
			</div>
			<div class="col">
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<div class="form-group">
						<h2>Login</h2>
						<form:label path="email">Email:</form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input path ="email" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="password">Password:</form:label>
						<form:errors path="password" class="text-danger"/>
						<form:input path="password" type="password" class="form-control"/>
					</div>
					<button type="submit" class="btn btn-primary mt-3">Login</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>