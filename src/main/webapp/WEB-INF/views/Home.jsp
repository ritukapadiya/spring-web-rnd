<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
FirstName: ${user.firstName} <br><br>
Email: ${user.email} <br><br>
Password: ${user.password} <br><br>
Gender: ${user.gender} <br><br>
Role: ${user.role} <br><br>
Hobby: ${user.hobby } <br><br>
Country: ${user.country}<br><br>
Profile : <img src="${user.profilePath }"/><br><br>
<a href="logout">Logout</a> <br><br>
<a href="certificate">Download certificate</a>
</body>
</html>