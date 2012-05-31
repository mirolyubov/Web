<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, ru.spbstu.telematics.objectCatalog.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Class View</title>
<link rel="stylesheet" href="/foo/styles/main.css"/>
<script src="/foo/scripts/main.js"></script>
</head>
<body>
	<div class="main">
		<h2>Class view</h2>
		
		<% ArrayList<TClass> classes = (ArrayList<TClass>)request.getAttribute("classes"); %>
		
		<div class="data">
			<table>
				<th>ID</th><th>Object Name</th><th>Description</th><th></th><th></th>
				<%=ViewClassServlet.printClasses(classes) %>
			</table>
		</div>
		<p>
			<h3>Add a Class</h3>
			<form name="addClass" method="get" action="/foo/classes/add_class.jsp">
				Name: <input id="className" type="text" name="className"/>
				Description: <input type="text" name="description" />
				<input type="submit" value="Add a Class"/>
			</form>
		</p>
	</div>
</body>
</html>