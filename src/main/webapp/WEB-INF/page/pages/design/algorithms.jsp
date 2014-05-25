<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="edas" uri="http://www.unlv.edu/cs/edas" %>
<html>
<head>
<edas:imports/>
<c:url var="resourceUrl" value="/resources/design"/>
<script type="text/javascript" src="${resourceUrl}/algorithms.js"></script>
</head>
<body>
<edas:header/>
<div class="edas-content">
	<form:form id="algorithms-delete-form" method="delete">
		<input type="hidden" id="algorithms-delete-id" name="id">
	</form:form>
	<table class="edas-table">
		<thead>
			<tr>
				<th>Name</th>
				<th colspan="2">
					<form:form id="algorithms-new-form" method="post">
						<button id="algorithms-new">New</button>
					</form:form>
				</th>
			</tr>
		</thead>
		<c:forEach items="${models}" var="algorithm">
			<tr>
				<td>${algorithm.name}</td>
				<c:url var="openUrl" value="/design/algorithms/${algorithm.stringId}"/>
				<td><a href="${openUrl}"><button class="algorithm-open">Open</button></a></td>
				<td><button class="algorithm-delete" value="${algorithm.stringId}">Delete</button></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>