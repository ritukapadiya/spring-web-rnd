<%@page import="com.bean.CountryBean"%>
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

.error{
	color:red;
}
</style>
<body>
<h1>Sign up page</h1>
<%-- 
<form action="saveuser" method = "post">
	First name: <input type="text" name="firstName"> <br><br>
	Email: <input type="text" name="email"><br><br>
	password: <input type="text" name="password"><br><br>
	Gender: <input type="radio" name="gender" value="female">Female &nbsp; <input type="radio" name="gender" value="male"> Male <br><br>
	Role: <input type="text" name="role" value="user"> <br><br>
	Hobby: 	<input type="checkbox" name="hobby" value="dance"> Dance &nbsp;
			<input type="checkbox" name="hobby" value="singing"> Singing &nbsp;
			<input type="checkbox" name="hobby" value="swimming"> Swimming &nbsp;
			<input type="checkbox" name="hobby" value="learning"> Learning &nbsp;
	<br><br>
	Country: <select name="country">
		     		<option value="none">----Select----</option>
		     		<option value="in">India</option>
		     		<option value="us">USA</option>
		     		<option value="ch">China</option>
		     		<option value="ge">Germany</option>
		     		<option value="ca">Canada</option>
		         </select>
	<br><br>
	<input type="submit" value="Sign up">
</form>
--%>


<%
List<CountryBean> countries = (List) request.getAttribute("countries");
%>
<s:form action="saveuser" method="post"    modelAttribute="user" enctype="multipart/form-data">
	
		FirstName: <s:input path="firstName" />
		<s:errors path="firstName" cssClass="error"></s:errors>
		<br>
		<br>
		Email: <s:input path="email" />
		<s:errors path="email" cssClass="error"></s:errors>

		<br>
		<br>
		Password: <s:password path="password" />
		<s:errors path="password" cssClass="error"></s:errors>

		<br>
		<br>
		Gender: <s:radiobutton path="gender" value="Female"/> Female
				<s:radiobutton path="gender" value="Male"/> Male
		<s:errors path="gender" cssClass="error"></s:errors>

		<br>
		<br>
		Role: <s:input path="role" value="USER" /> <s:errors path="role" cssClass="error"></s:errors><br><br>
		Hobby: 	<s:checkbox path="hobby" value="dance"/> Dance &nbsp;
			<s:checkbox  path="hobby" value="singing"/> Singing &nbsp;
			<s:checkbox  path="hobby" value="swimming"/> Swimming &nbsp;
			<s:checkbox  path="hobby" value="learning"/> Learning &nbsp;
			<s:errors path="hobby" cssClass="error" ></s:errors>
		<br><br>
 <!--  		Country: <s:select path="country" items="${countries}" itemValue="code" itemLabel="name"  >
				
		         </s:select>
		         
		         <s:errors path="country" cssClass="error"></s:errors>   -->
		         
		Country: <s:select path="country" >
			<s:option value="-1">----select----</s:option>
			<s:options items="${countries2}" itemValue="code" itemLabel="name"></s:options>
		</s:select>
		<br><br>
		Profile : <input type="file" name="profile" />
		<br><br>
		<input type="submit" value="Signup" />


	</s:form>

</body>
</html>