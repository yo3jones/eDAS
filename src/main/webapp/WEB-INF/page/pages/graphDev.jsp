<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:url var="resourceUrl" value="/resources/"/>
<head>
<script type="text/javascript" src="${resourceUrl}lib/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="${resourceUrl}lib/jquery-ui-1.10.4.custom.min.js"></script>
<script type="text/javascript" src="${resourceUrl}/svg-plugin.js"></script>

<script type="text/javascript" src="${resourceUrl}/graph-widget-states.js"></script>
<script type="text/javascript" src="${resourceUrl}/graph-comm-widget.js"></script>
<script type="text/javascript" src="${resourceUrl}/graph-widget.js"></script>

<script type="text/javascript" src="${resourceUrl}/graphDev.js"></script>
<link rel="stylesheet" type="text/css" href="${resourceUrl}/lib/blitzer/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="${resourceUrl}/graphDev.css">
</head>
<body>
	<c:url var="graphsUrl" value="/api/v1/design/graphs"/>
	<input type="hidden" id="graphs-url" value="${graphsUrl}"/>
	<c:url var="baseUrl" value="/"/>
	<input type="hidden" id="base-url" value="${baseUrl}"/>
	<div class="left">
		<input type="button" id="load-graph-button" value="Load Graph"/>
		<input type="button" id="export-graph-button" value="Export Graph"/>
		<input type="button" id="simulate-error" value="Simulate Error"/>
		<div class="input-container">
			<textarea id="input" class="ui-widget-content ui-corner-all">{
     "vertices": {
          "1": {
               "label": "A",
               "x": 150,
               "y": 150
          },
          "2": {
               "label": "B",
               "x": 350,
               "y": 150
          },
          "3": {
               "label": "C",
               "x": 150,
               "y": 350
          },
          "4": {
               "label": "D",
               "x": 350,
               "y": 350
          },
          "5": {
               "label": "E",
               "x": 450,
               "y": 250
          },
          "6": {
               "label": "F",
               "x": 250,
               "y": 50
          },
          "7": {
               "label": "G",
               "x": 50,
               "y": 250
          },
          "8": {
               "label": "H",
               "x": 250,
               "y": 450
          },
          "9": {
               "label": "I",
               "x": 250,
               "y": 250
          }
     },
     "edges": {
          "8-4": {
               "e": "e"
          },
          "9-4": {
               "e": "e"
          },
          "9-5": {
               "e": "e"
          },
          "2-5": {
               "e": "e"
          },
          "9-6": {
               "e": "e"
          },
          "2-4": {
               "e": "e"
          },
          "9-7": {
               "e": "e"
          },
          "9-8": {
               "e": "e"
          },
          "3-8": {
               "e": "e"
          },
          "4-5": {
               "e": "e"
          },
          "3-4": {
               "e": "e"
          },
          "6-1": {
               "e": "e"
          },
          "6-2": {
               "e": "e"
          },
          "7-3": {
               "e": "e"
          },
          "9-3": {
               "e": "e"
          },
          "9-2": {
               "e": "e"
          },
          "1-3": {
               "e": "e"
          },
          "9-1": {
               "e": "e"
          },
          "7-1": {
               "e": "e"
          },
          "1-2": {
               "e": "e"
          }
     }
}</textarea>
		</div>
	</div>
	<div class="right">
		<div id="output" class="output-container">
			
		</div>
	</div>
</body>
</html>