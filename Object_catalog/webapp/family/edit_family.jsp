<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, ru.spbstu.telematics.objectCatalog.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Family</title>
<link rel="stylesheet" href="/foo/styles/main.css"/>
<script src="/foo/scripts/main.js"></script>
</head>
<body>
	<div class="main">
		<h2>Edit Family</h2>		
		<div class="data">
			<form name="addFamily" method="get" action="/foo/families/edit_family.jsp">
				Name: <input id="familyName" type="text" name="familyName" value="${familyObj.name}"/>
				Description: <input type="text" name="description" value="${familyObj.description}" />
				<input type="hidden" name="familyId" value="${familyId}"/>
				<input type="submit" value="Edit Family"/>
			</form>
		</div>
	</div>
</body>
</html>