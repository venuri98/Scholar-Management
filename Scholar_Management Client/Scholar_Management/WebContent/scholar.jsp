<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "model.ScholarManagement" %>
      
      
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Scholar Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/scholars.js"></script>
</head>

<body>
		<div class="container"><div class="row"><div class="col-6">
		<h1>Scholar Management</h1>
		<form id="formItem" name="formItem">
			userName:
			<input id="name" name="name" type="text"class="form-control form-control-sm">
			<br> 
			email:
			<input id="email" name="email" type="text"class="form-control form-control-sm">
			<br> 
			approvedProjectTitle:
			<input id="approvedProjectTitle" name="approvedProjectTitle" type="text"class="form-control form-control-sm">
			<br> 
			approvedDate:
			<input id="approvedDate" name="approvedDate" type="text"class="form-control form-control-sm">
			<br>
			<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
			<input type="hidden" id="sid" name="sid" value="">
		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divItemsGrid">
		<%
			ScholarManagement scholarObj = new ScholarManagement();
			out.print(scholarObj.readscholordata());
		%>
		</div>
		</div> </div> </div>
</body>
</html>