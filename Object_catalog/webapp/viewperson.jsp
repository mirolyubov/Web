<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View person</title>
</head>
<body>
<h2>Person view</h2>
<script type="text/javascript">
var str = encodeURI('http://localhost/id=1;drop table person;');
document.write(str);
</script>
<div id="w"></div>
<p>
 name is: ${p}
 <br>age is : ${a.name}
<%--

<br>friend is : ${p.description}
<br>id is : ${p.id}
<br>${man.dbError}
<br>${err} --%>
<% Integer id = (Integer)request.getAttribute("p");%>
<%=id %>
</p>
</body>
</html>