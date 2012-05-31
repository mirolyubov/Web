<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, ru.spbstu.telematics.objectCatalog.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Style View</title>
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
		<h2>Style view</h2>
		
		<div class="data">
			<table>
				<th>ID</th><th>Family</th><th>is Mandatory</th><th>is Multiple</th><th></th><th></th>
				<%=ViewStyleServlet.printStyles() %>
			</table>
		</div>
		<p>
			<h3>Add a Style</h3>
			<form name="addStyle" method="get" action="/foo/styles/add_style.jsp">
				Family: <select name="familyId">
					<%=ViewStyleServlet.printFamilyOptions() %>
				</select>
				<select name="isMandatory">
					<option selected="selected" value="f">is not mandatory</option>
					<option value="t">is mandatory</option>
				</select>
				<select name="isMultiple">
					<option selected="selected" value="f">is not multiple</option>
					<option value="t">is multiple</option>
				</select>
				<input type="submit" value="Add a Style"/>
			</form>
		</p>
	</div>
</body>
</html>