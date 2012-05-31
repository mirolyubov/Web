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
		<h2>Edit Class</h2>		
		<div class="data">
			<form name="addClass" method="get" action="/foo/classes/edit_class.jsp">
				Name: <input id="className" type="text" name="className" value="${classObj.name}"/>
				Description: <input type="text" name="description" value="${classObj.description}" />
				<input type="hidden" name="classId" value="${classId}"/>
				<input type="submit" value="Edit Class"/>
			</form>
		</div>
	</div>
</body>
</html>