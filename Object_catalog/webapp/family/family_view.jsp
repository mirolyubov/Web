<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, ru.spbstu.telematics.objectCatalog.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Family View</title>
<link rel="stylesheet" href="/foo/styles/main.css"/>
<script src="/foo/scripts/main.js"></script>
</head>
<body>
	<div class="main">
		<div>
			<input type="button" value="Objects" onclick="location.href='/foo/objects/object_view.jsp'">
			<input type="button" value="Classes" onclick="location.href='/foo/classes/class_view.jsp'">
			<input type="button" value="Styles" onclick="location.href='/foo/styles/style_view.jsp'">
			<input type="button" value="Family" onclick="location.href='/foo/families/family_view.jsp'">
		</div>
		<h2>Family view</h2>
		
		<% ArrayList<TClass> classes = (ArrayList<TClass>)request.getAttribute("classes"); %>
		
		<div class="data">
			<table>
				<th>ID</th><th>Family Name</th><th>Description</th><th></th><th></th>
				<%=ViewFamilyServlet.printFamilies() %>
			</table>
		</div>
		<p>
			<h3>Add a Family</h3>
			<form name="addFamily" method="get" action="/foo/families/add_family.jsp">
				Name: <input id="className" type="text" name="familyName"/>
				Description: <input type="text" name="description" />
				<input type="submit" value="Add a Family"/>
			</form>
		</p>
	</div>
</body>
</html>