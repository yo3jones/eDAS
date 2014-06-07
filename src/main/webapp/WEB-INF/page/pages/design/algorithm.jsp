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
			<div class="edas-input-line"><label>Name:</label><input type="text" name="name" value="${model.name}"></div>
			<div class="edas-input-line"><label>State Display Pattern:</label><input type="text" name="stateDisplayPattern" value="${model.stateDisplayPattern}"></div>
			<div class="edas-input-line"><label>Message Display Pattern:</label><input type="text" name="messageDisplayPattern" value="${model.messageDisplayPattern}"></div>
			<div class="edas-input-line"><label>Bidirectional:</label><input type="checkbox" name="bidirectional" ${model.bidirectional ? "checked" : ""}></div>
			<c:if test="${showCompileError}">
				<div class="edas-input-line ui-widget ui-state-error ui-corner-all">
					<span class="ui-state-error-text">${compileErrorMessage}</span>
				</div>
			</c:if>
			<div class="edas-input-line"><label>Algorithm:</label><textarea rows="20" name="algorithm">${model.algorithm}</textarea></div>
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