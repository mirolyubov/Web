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
		<% ArrayList<TStyle> styles = (ArrayList<TStyle>)request.getAttribute("styles");
			ArrayList<TStyle> stylesAll = (ArrayList<TStyle>)request.getAttribute("stylesAll");  
			String classId = (String)request.getAttribute("classId");	%>
		<p><a href="/foo/classes/class_view.jsp">Back</a>&nbsp;Styles for Class: ${classObj.name}</p>
		<div class="data">
			<table>
				<th>Value</th><th>Is mandatory</th><th>Is multiple</th><th></th><th></th>
				<%
					for (int i=0; i<styles.size(); i++) {
						out.println("<tr><td>"+styles.get(i).getFamily()+"</td>");
						out.println("<td>"+styles.get(i).isMandatory()+"</td>");
						out.println("<td>"+styles.get(i).isMultiple()+"</td>");
	out.println("<td><input type='button' value='Edit' onclick=\"location='/foo/class_styles/edit_class_style.jsp?classId="+classId+"&styleId="+styles.get(i).getStyleId()+"'\"></td>");
	out.println("<td><input type='button' value='Delete' onclick=\"location='/foo/class_styles/delete_class_style.jsp?classId="+classId+"&styleId="+styles.get(i).getStyleId()+"'\"></td></tr>");
					}
				%>
			</table>
		</div>
		<div class="panel_wrap">
				<div>
				<form name="addValue" method="get" action="/foo/class_styles/add_class_style.jsp">
					<select name="styleId">
						<%=ViewObjectValueServlet.printStylesOptions(stylesAll) %>
					</select>
					<input type="hidden" name="classId" value="${classObj.id}"/>
					<input type="submit" value="Add Class-Style Connection" />
				</form>
				</div>
		</div>
	</div>
</body>
</html>