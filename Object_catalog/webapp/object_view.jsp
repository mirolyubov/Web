<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, ru.spbstu.telematics.objectCatalog.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Object View</title>
<link rel="stylesheet" href="/foo/styles/main.css"/>
</head>
<body>
	<div class="main">
		<h2>Object view</h2>
		<div class="filter">
		<% ArrayList<TClass> classes = (ArrayList<TClass>)request.getAttribute("classes"); %>
			<form name="object_filter" method="get" action="object_view.jsp">
				<select name="classId">
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
						out.println("<td>"+objects.get(i).getName()+"</td>");
						out.println("<td>"+objects.get(i).getClassName()+"</td></tr>");
					}
				%>
			</table>
		</div>
	</div>
</body>
</html>