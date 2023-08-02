<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
</head>
<body>
	<s:form action="updateUser" method="post" enctype="multipart/form-data" modelAttribute="userBean">
		 <s:hidden path="email"  />
		Profile: <img src="${userBean.getProfilePath() }" width="100px" height="200px"> 
		<input type="file" name="profile" /><br><br>
		FirstName: <s:input path="firstName"/>
		<br>
		<br>
		Role: <s:input path="role" />
		<br>
		<br>
		<input type="submit" value="Update" />
	</s:form>
</body>
</html>