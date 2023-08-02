<%@page import="com.controller.AdminController"%>
<%@page import="com.bean.UserCountryBean"%>
<%@page import="com.bean.UserBean"%>
<%@page import="java.util.List"%>
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
#p{
	position: relative;
	left: 1050px;
}
</style>
<body>
<h1>List of users</h1>

<%
List<UserCountryBean> userList = (List) request.getAttribute("users");
AdminController a = new AdminController();
%>



	<form action="dashboard_search" method="get" name = "f">
		<input type="text" placeholder="search user" onkeyup="search()" name="searchValue" value="${searchValue}">
	    
	    </form>
	    <a href="pdf" id="p">Download pdf</a>
	<br><br>
	

	<table border='1'>
		<thead>
			<tr>
				<th>Profile</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>EmailName</th>
				<th>Password</th>
				<th>Country</th>
				<th>Action </th>
			</tr>
		</thead>
		<tbody>
		<% for(UserCountryBean userCountryBean: userList){ %>
			<tr>
				<td> <img src="<%=  userCountryBean.getProfilePath() %>" width="50px" height="50px"> </td>  
				<td> <%= userCountryBean.getFirstName() %> </td>
				<td> <%= userCountryBean.getRole() %> </td>
				<td> <%= userCountryBean.getEmail() %> </td>
				<td> <%= userCountryBean.getPassword() %> </td>
				<td> <%= userCountryBean.getName() %> </td>
				<td> <a href="delete?userId=<%=userCountryBean.getEmail() %>" >Delete </a> 
				    <%--  <a href="ViewUserServlet?userId=<%= userBean.getEmail() %>" >View</a>--%>
				     <a href="update?userId=<%=userCountryBean.getEmail() %>" >Edit</a></td>  
			</tr>
		<% } %>
		</tbody>
	</table>
	 <div> <a href="dashboard_key?key=1">1</a> <a href="dashboard_key?key=2">2</a> <a href="dashboard_key?key=3">3</a> </div>
		
		
</body>
<script type="text/javascript">
 var val
 function search() {
	val = document.f.searchValue.value
	location.href="dashboard_search?searchValue="+val;
}
</script>
</html>