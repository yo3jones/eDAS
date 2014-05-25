<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="edas" uri="http://www.unlv.edu/cs/edas" %>
<html>
<head>
<edas:imports/>
</head>
<body>
<edas:header/>
<div>${model.userName}</div>
<c:url var="logoutUrl" value="/logout"/>
<form:form action="${logoutUrl}" method="POST">
	<input type="submit" value="Logout"/>
</form:form>
</body>
</html>