<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="edas" uri="http://www.unlv.edu/cs/edas" %>
<html>
<head>
<edas:imports/>
<c:url var="resourceUrl" value="/resources/design"/>
<script type="text/javascript" src="${resourceUrl}/algorithm.js"></script>
<link rel="stylesheet" type="text/css" href="${resourceUrl}/algorithm.css">
</head>
<body>
<edas:header/>
<div class="edas-content">
	<fieldset class="ui-widget">
		<form:form id="algorithms-put-form" method="PUT">
			<p><label>Name:</label><input type="text" name="name" value="${model.name}"></p>
			<p><label>State Display Pattern:</label><input type="text" name="stateDisplayPattern" value="${model.stateDisplayPattern}"></p>
			<p><label>Message Display Pattern:</label><input type="text" name="messageDisplayPattern" value="${model.messageDisplayPattern}"></p>
			<p><label>Bidirectional:</label><input type="checkbox" name="bidirectional" ${model.bidirectional ? "checked" : ""}></p>
			<c:if test="${showCompileError}">
				<p>
					<div class="ui-widget ui-state-error ui-corner-all">
						<span class="ui-state-error-text">${compileErrorMessage}</span>
					</div>
				</p>
			</c:if>
			<p><label>Algorithm:</label><textarea rows="20" name="algorithm">${model.algorithm}</textarea></p>
		</form:form>
		<div class="edas-button-bar">
			<c:url var="algorithmsUrl" value="/design/algorithms"/>
			<a href="${algorithmsUrl}"><button class="algorithms-back-button">Back</button></a>
			<button id="algorithm-save-button">Save</button>
		</div>
	</fieldset>
</div>
</body>
</html>