<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<c:url var="resourceUrl" value="/resources/"/>
<head>
<script type="text/javascript" src="${resourceUrl}lib/jquery-1.11.0.min.js"></script>
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
               "label": "",
               "x": 24,
               "y": 343
          },
          "2": {
               "label": "",
               "x": 216,
               "y": 522
          },
          "3": {
               "label": "",
               "x": 138,
               "y": 305
          },
          "4": {
               "label": "",
               "x": 204,
               "y": 222
          },
          "5": {
               "label": "",
               "x": 168,
               "y": 144
          },
          "6": {
               "label": "",
               "x": 366,
               "y": 278
          },
          "7": {
               "label": "",
               "x": 360,
               "y": 68
          },
          "8": {
               "label": "",
               "x": 485,
               "y": 131
          },
          "9": {
               "label": "",
               "x": 497,
               "y": 19
          },
          "10": {
               "label": "",
               "x": 607,
               "y": 140
          }
     },
     "edges": {
          "10-2": {
               "weight": 18
          },
          "5-4": {
               "weight": 2
          },
          "9-8": {
               "weight": 1
          },
          "7-9": {
               "weight": 4
          },
          "7-8": {
               "weight": 5
          },
          "5-1": {
               "weight": 6
          },
          "7-6": {
               "weight": 7
          },
          "5-3": {
               "weight": 4
          },
          "4-3": {
               "weight": 2
          },
          "3-6": {
               "weight": 9
          },
          "10-6": {
               "weight": 10
          },
          "4-6": {
               "weight": 8
          },
          "10-8": {
               "weight": 3
          },
          "3-2": {
               "weight": 9
          },
          "9-10": {
               "weight": 4
          },
          "6-2": {
               "weight": 8
          },
          "7-4": {
               "weight": 9
          },
          "7-5": {
               "weight": 9
          },
          "8-6": {
               "weight": 9
          },
          "1-3": {
               "weight": 3
          },
          "1-2": {
               "weight": 9
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