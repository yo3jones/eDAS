<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="edas" uri="http://www.unlv.edu/cs/edas" %>
<html>
<head>
<edas:imports/>
<c:url var="resourceUrl" value="/resources"/>
<script type="text/javascript" src="${resourceUrl}/svg-plugin.js"></script>
<script type="text/javascript" src="${resourceUrl}/graph-widget-states.js"></script>
<script type="text/javascript" src="${resourceUrl}/graph-comm-widget.js"></script>
<script type="text/javascript" src="${resourceUrl}/graph-widget.js"></script>

<script type="text/javascript" src="${resourceUrl}/design/designGraphDetails.js"></script>
<link rel="stylesheet" type="text/css" href="${resourceUrl}/design/designGraphDetails.css">
</head>
<body>
<edas:header/>
<c:url var="baseUrl" value="/"/>
<input type="hidden" id="base-url" value="${baseUrl}"/>
<input type="hidden" id="graph-id" value="${model.id}"/>
<div class="graph-details-content edas-content">
	<div class="graph-details-fields">
		<fieldset class="ui-widget">
			<p><label>Name:</label><input type="text" id="graph-details-name"/></p>
			<div class="edas-button-bar">
				<c:url var="graphDetailsBackUrl" value="/design/graphDetails"/>
				<a href="${graphDetailsBackUrl}"><button id="graph-details-back">Back</button></a>
				<button id="graph-details-save">Save</button>
			</div>
		</fieldset>
	</div>
	<div class="graph-details-graph ui-corner-all">
		<div id="graph"></div>
	</div>
</div>
</body>
</html>