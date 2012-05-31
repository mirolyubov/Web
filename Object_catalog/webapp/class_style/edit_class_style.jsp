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
			<form name="editClassStyle" method="get" action="/foo/class_styles/edit_class_style.jsp">
				Style: <select name="newStyleId">
							<%=EditClassStyleServlet.printStylesOptions() %>
						</select>
						<input type="hidden" name="classId" value="${classId}"/>
						<input type="hidden" name="styleId" value="${styleId}"/>
				<input type="submit" value="Edit Class"/>
			</form>
		</div>
	</div>
</body>
</html>