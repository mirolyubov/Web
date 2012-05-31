<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, ru.spbstu.telematics.objectCatalog.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Object View</title>
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
		<h2>Object view</h2>
		<div class="filter">
		<% ArrayList<TClass> classes = (ArrayList<TClass>)request.getAttribute("classes"); %>
			<form name="object_filter" method="get" action="object_view.jsp">
				Name: <input type="text" name="objectName" value="${objectName}"/>
				Class: <select name="classId">
					<%=ViewObjectServlet.printClassesOptions(classes)%>
				</select>
				<input type="submit"/>
			</form>
		</div>
		<div class="data">
		<% ArrayList<TObject> objects = (ArrayList<TObject>)request.getAttribute("objects"); %>
			<table>
				<th>ID</th><th>Object Name</th><th>Class Name</th>
				<% 
					for (int i=0; i<objects.size(); i++) {
						out.println("<tr><td>"+objects.get(i).getId()+"</td>");
						out.println("<td><a href='/foo/values/object_value_view.jsp?objectId="+objects.get(i).getId()+"'>"+objects.get(i).getName()+"</a></td>");
						out.println("<td>"+objects.get(i).getClassName()+"</td></tr>");
					}
				%>
			</table>
		</div>
		<p>
			<form name="addObject" method="get" action="/foo/objects/add_object.jsp">
				<input id="objectName" type="text" name="objectName"/>
				<select name="classId" id="classId">
					<%=ViewObjectServlet.printClassesOptions(classes)%>
				</select>
				<input type="button" value="Add an Object" onclick="addObjectFormSubmit()"/>
			</form>
		</p>
	</div>
</body>
</html>