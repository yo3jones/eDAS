<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="edas" uri="http://www.unlv.edu/cs/edas" %>
<html>
<head>
<edas:imports/>
<c:url var="resourceUrl" value="/resources/design"/>
<script type="text/javascript" src="${resourceUrl}/runs.js"></script>
</head>
<body>
<edas:header/>
<div class="edas-content">
	<form:form id="runs-delete-form" method="DELETE">
		<input type="hidden" id="runs-delete-id" name="id">
	</form:form>
	<table class="edas-table">
		<thead>
			<tr>
				<th>Name</th>
				<th>Graph</th>
				<th>Algorithm</th>
				<th colspan="2">
					<form:form method="POST">
						<button id="runs-new-button">New</button>
					</form:form>
				</th>
			</tr>
		</thead>
		<c:forEach items="${models}" var="model">
			<tr>
				<td>${model.run.name}</td>
				<td>${model.graphName}</td>
				<td>${model.algorithmName}</td>
				<c:url var="openUrl" value="/design/runs/${model.run.stringId}"/>
				<td><a href="${openUrl}"><button class="runs-open-button">Open</button></a></td>
				<td><button class="runs-delete-button" value="${model.run.stringId}">Open</button></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>