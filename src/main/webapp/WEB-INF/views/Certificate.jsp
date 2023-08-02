<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<s:form action="certificate_data" method="post" modelAttribute = "certificateBean">
	
	
		Full Name: <s:input path="name" />
		<br><br>
		
		Email: <s:input path="email" />

		<br>
		<br>
		Course: <s:input path="course" />
		<br><br>
		
		Mentor: <s:input path="mentor" />

		<br>
		<br>
		
		Completion Month-Year: <s:input path="monthYear"/>
		
		<br><br>
	
		<input type="submit" value="Submit" />


	</s:form>


</body>
</html>