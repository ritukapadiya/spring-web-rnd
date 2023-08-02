<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>

.error{
	color:red;
}
</style>
<body>
<h1>Login page</h1>



<s:form action="authenticate" method="post" modelAttribute = "loginUser">
	
		
		Email: <s:input path="email" />
		<s:errors path="email" cssClass="error"></s:errors>

		<br>
		<br>
		Password: <s:password path="password" />
		<s:errors path="password" cssClass="error"></s:errors>
		<br><br>
	
		<input type="submit" value="Log in" />


	</s:form>

</body>
</html>