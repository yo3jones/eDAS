<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
</head>
<body>
<c:if test="${model.showMessage}">
	<div>${model.message}</div>
</c:if>
<c:if test="${model.showCreate}">
	<form:form method="post">
		<label>User Name: </label><input type="text" name="name"><br/>
		<label>Password: </label><input type="password" name="password"><br/>
		<input type="submit">
	</form:form>
</c:if>
</body>
</html>