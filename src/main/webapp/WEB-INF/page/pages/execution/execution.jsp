<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="edas" uri="http://www.unlv.edu/cs/edas" %>
<html>
<head>
	<edas:imports/>
	<c:url var="resourceUrl" value="/resources"/>
	<script type="text/javascript" src="${resourceUrl}/graph-comm-widget.js"></script>
	<script type="text/javascript" src="${resourceUrl}/execution/execution.js"></script>
	<link rel="stylesheet" type="text/css" href="${resourceUrl}/execution/execution.css">
</head>
<body>
	<edas:header/>
	<div class="edas-content execution-content">
	<c:url var="roundUrl" value="/api/v1/executions/${model.run.stringId}"/>
	<input type="hidden" id="execution-round-url" value="${roundUrl}">
		<div class="execution-info ui-widget">
			<p><label>Round:</label><span id="execution-round"></span></p>
			<p><label>Message Count:</label><span id="execution-message-count"></span></p>
			<p><label>Run:</label><span>${model.run.name}</span></p>
			<p><label>Algorithm:</label><span>${model.algorithm.name}</span></p>
			<p><label>Graph:</label><span>${model.designGraphDetails.name}</span></p>
		</div>
		<div class="execution-control">
			<button id="execution-prev-button">Prev</button>
			<button id="execution-next-button">Next</button>
		</div>
		<div id="execution-graph-container" class="ui-corner-all">
			${svg}
		</div>
	</div>
</body>
</html>