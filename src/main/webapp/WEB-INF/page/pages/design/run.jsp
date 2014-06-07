<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="edas" uri="http://www.unlv.edu/cs/edas" %>
<html>
<head>
<edas:imports/>
<c:url var="resourceUrl" value="/resources/design"/>
<script type="text/javascript" src="${resourceUrl}/run.js"></script>
</head>
<body>
<edas:header/>
<div class="edas-content">
	
	<fieldset>
		<form:form id="run-put-form" method="PUT">
			<div class="edas-input-line"><label class="ui-widget">Name:</label><input class="ui-widget" type="text" name="name" value="${model.run.name}"></div>
			<div class="edas-input-line"><label class="ui-widget">Graph:</label><input  class="ui-widget" type="text" id="run-graph-name" value="${model.graphName}"/></div>
			<input type="hidden" name="graphId" id="run-graph-id" value="${model.graphId}"/>
			<div class="edas-input-line"><label class="ui-widget">Algorithm:</label><input  class="ui-widget" type="text" id="run-algorithm-name" value="${model.algorithmName}"></div>
			<input type="hidden" name="algorithmId" id="run-algorithm-id" value="${model.algorithmId}">
		</form:form>
		<div class="edas-button-bar">
			<c:url var="backUrl" value="/design/runs"/>
			<a href="${backUrl}"><button id="run-back-button">Back</button></a>
			<button id="run-save-button">Save</button>
			<c:url var="executionsUrl" value="/executions/${model.run.stringId}"/>
			<a href="${executionsUrl}"><button id="run-execute-button">Execute</button></a>
		</div>
	</fieldset>
	
	<div id="run-graphs" style="display:none;">
		<c:forEach items="${model.graphs}" var="graphModel">
			<div class="run-graph-value">
				<input type="hidden" class="run-graph-id" value="${graphModel.stringId}"/>
				<input type="hidden" class="run-graph-name" value="${graphModel.name}"/>
			</div>
		</c:forEach>
	</div>
	<div id="run-algorithms" style="display:none;">
		<c:forEach items="${model.algorithms}" var="algorithmModel">
			<div class="run-algorithm-value">
				<input type="hidden" class="run-algorithm-id" value="${algorithmModel.stringId}"/>
				<input type="hidden" class="run-algorithm-name" value="${algorithmModel.name}"/>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>