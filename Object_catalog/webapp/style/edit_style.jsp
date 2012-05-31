<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, ru.spbstu.telematics.objectCatalog.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Style</title>
<link rel="stylesheet" href="/foo/styles/main.css"/>
<script src="/foo/scripts/main.js"></script>
</head>
<body>
	<div class="main">
		<h2>Edit Style</h2>		
		<div class="data">
			<form name="editClassStyle" method="get" action="/foo/styles/edit_style.jsp">
				Family: <select name="familyId">
					<%=EditStyleServlet.printFamilyOptions() %>
				</select>
				<select name="isMandatory">
					<%=EditStyleServlet.printMandatoryOpt() %>
				</select>
				<select name="isMultiple">
					<%=EditStyleServlet.printMultipleOpt() %>
				</select>
				<input type="hidden" name="styleId" value="${styleId}"/>
				<input type="submit" value="Edit Style"/>
			</form>
		</div>
	</div>
</body>
</html>