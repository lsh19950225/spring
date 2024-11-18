<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! - ${user}
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h3>
	<a href="/board/list">/board/list</a>
</h3>

</body>
</html>
