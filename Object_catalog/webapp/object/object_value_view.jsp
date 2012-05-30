<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, ru.spbstu.telematics.objectCatalog.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Object Value View</title>
<link rel="stylesheet" href="/foo/styles/main.css"/>
</head>
<body>
	<div class="main">
		<h2>Object Value view</h2>
		<% ArrayList<TObjectValue> values = (ArrayList<TObjectValue>)request.getAttribute("values"); %>
		<p><a href="/foo/objects/object_view.jsp">Back</a>&nbsp;Object name: ${objectName}</p>
		<div class="data">
			<table>
				<th>Value</th><th>Is mandatory</th><th>Is multiple</th>
				<%
					for (int i=0; i<values.size(); i++) {
						out.println("<tr><td>"+values.get(i).getValue()+"</td>");
						out.println("<td>"+values.get(i).isMandatory()+"</td>");
						out.println("<td>"+values.get(i).isMultiple()+"</td></tr>");
					}
				%>
			</table>
		</div>
		<div class="panel_wrap">
			<div id="panel">
				<div>
					<input type="text" name="value_1"/>
						<select name="style_1">
					</select>
				</div>
			</div>
			<input type="button" value="+1"/>
			<input type="button" value="Add Fields"/>
		</div>
		<p><input type="button" value="Delete" onclick="location.href='/foo/objects/delete_object.jsp?objectId=${objectId}'"/></p>
	</div>
</body>
</html>