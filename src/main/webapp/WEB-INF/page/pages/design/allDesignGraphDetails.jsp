<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="edas" uri="http://www.unlv.edu/cs/edas" %>
<html>
<head>
<edas:imports/>
<c:url var="resourceUrl" value="/resources"/>
<script type="text/javascript" src="${resourceUrl}/graph-comm-widget.js"></script>
<script type="text/javascript" src="${resourceUrl}/design/allDesignGraphDetails.js"></script>
</head>
<body>
<edas:header/>
<c:url var="apiUrl" value="/api/v1/design/graphDetails"/>
<input type="hidden" id="design-graph-api-url" value="${apiUrl}">
<c:url var="newUrl" value="/design/graphDetails/new"/>
<div class="edas-content">
	<table class="edas-table">
		<thead>
			<tr>
				<th>Name</th>
				<th colspan="2">
					<a href="${newUrl}"><button class="graph-details-new">New</button></a>
				</th>
			</tr>
		</thead>
		<c:forEach items="${model.graphDetails}" var="graphDetails">
			<tr>
				<c:url var="openUrl" value="/design/graphDetails/${graphDetails.stringId}"/>
				<td>${graphDetails.name}</td>
				<td><a href="${openUrl}"><button class="graph-details-open">Open</button></a></td>
				<td><button class="graph-details-delete" value="${graphDetails.stringId}">Delete</button></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>