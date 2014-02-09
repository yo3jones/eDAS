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
               "id": {
                    "id": 1
               },
               "label": "A",
               "position": {
                    "x": 150,
                    "y": 50
               }
          },
          "2": {
               "id": {
                    "id": 2
               },
               "label": "B",
               "position": {
                    "x": 50,
                    "y": 200
               }
          },
          "3": {
               "id": {
                    "id": 3
               },
               "label": "C",
               "position": {
                    "x": 250,
                    "y": 200
               }
          }
     },
     "edges": {
          "2-3": {
               "id": {
                    "fromKey": {
                         "id": 2
                    },
                    "toKey": {
                         "id": 3
                    }
               }
          },
          "1-3": {
               "id": {
                    "fromKey": {
                         "id": 1
                    },
                    "toKey": {
                         "id": 3
                    }
               }
          },
          "1-2": {
               "id": {
                    "fromKey": {
                         "id": 1
                    },
                    "toKey": {
                         "id": 2
                    }
               }
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