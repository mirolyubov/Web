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
		<% ArrayList<TStyle> styles = (ArrayList<TStyle>)request.getAttribute("styles"); 
			String objectId = (String)request.getAttribute("objectId");	%>
		<p><a href="/foo/objects/object_view.jsp">Back</a>&nbsp;Object name: ${objectName}</p>
		<div class="data">
			<table>
				<th>Value</th><th>Is mandatory</th><th>Is multiple</th><th></th>
				<%
					for (int i=0; i<values.size(); i++) {
						out.println("<tr><td>"+values.get(i).getValue()+"</td>");
						out.println("<td>"+values.get(i).isMandatory()+"</td>");
						out.println("<td>"+values.get(i).isMultiple()+"</td>");
	out.println("<td><input type='button' value='delete' onclick=\"location='/foo/values/delete_value.jsp?objectId="+objectId+"&valueId="+values.get(i).getId()+"'\"></td></tr>");
					}
				%>
			</table>
		</div>
		<div class="panel_wrap">
				<div>
				<form name="addValue" method="get" action="/foo/values/add_value.jsp">
					<input type="text" name="value"/>
					<select name="styleId">
						<%=ViewObjectValueServlet.printStylesOptions(styles)%>
					</select>
					<input type="hidden" name="objectId" value="${objectId}"/>
					<input type="submit" value="Add Field" />
				</form>
				</div>
		</div>
		<p><input type="button" value="Delete" onclick="location.href='/foo/objects/delete_object.jsp?objectId=${objectId}'"/></p>
	</div>
</body>
</html>