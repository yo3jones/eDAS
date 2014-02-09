var GraphStates = {};

(function($) {
	GraphStates.SelectGraphState = SelectGraphState;
	GraphStates.LineGraphState = LineGraphState;
	GraphStates.DeleteGraphState = DeleteGraphState;
	
	function GraphState(widget) {
		this.widget = widget;
		this.dragStartVertex = null;
		this.dragEndVertex = null;
	};
	
	GraphState.prototype.onMouseDown = function(event) {
		var target = $(event.target);
		var position = this._getPositionFromEvent(event);
		if (target.isCircle() || target.isText()) {
			this.dragStartVertex = this._getVertexFromEvent(event);
			this._onDragStartVertex(position);
		}
	};
	
	GraphState.prototype.onMouseMove = function(event) {
		if (this.dragStartVertex) {
			var position = this._getPositionFromEvent(event);
			this._onDragVertex(position);
		}
	};
	
	GraphState.prototype.onMouseUp = function(event) {
		if (this.dragStartVertex) {
			var position = this._getPositionFromEvent(event);
			this.dragEndVertex = this._getVertexFromEvent(event);
			this._onDragStopVertex(position);
			this.dragStartVertex = null;
			this.dragEndVertex = null;
		}
	};
	
	GraphState.prototype._getPositionFromEvent = function(event) {
		if (false) {
			console.log(event.pageX + "," + this.widget.getSvg().offset().left);
			return {
				x: event.originalEvent.layerX, 
				y: event.originalEvent.layerY
			};
		} else {
			var svgPosition = this.widget.getSvgContainer().offset();
			return {
				x: event.pageX - svgPosition.left,
				y: event.pageY - svgPosition.top
			};
		}
	};
	
	GraphState.prototype._onDragStartVertex = function(position) {
		
	};
	
	GraphState.prototype._onDragVertex = function(position) {
		
	};
	
	GraphState.prototype._onDragStopVertex = function(position) {
		
	};
	
	GraphState.prototype.onClickVertex = function(event) {
		
	};
	
	GraphState.prototype.onDblClickVertex = function(event) {
		var vertexName = this._getVertexIdFromEvent(event);
		var labelElement = this._getLabelFromEvent(event);
		var me = this;
		
		$("#edas-graph-vertex-label-dialog-input")
			.val(labelElement.text())
			.off("keypress")
			.keypress(function(event) {
				if (event.which == 13) {
					var label = $("#edas-graph-vertex-label-dialog-input").val();
					labelElement.text(label);
					me.widget.options.graph.vertices[vertexName].label = label;
					me.widget.saveVertex(vertexName);
					$("#edas-graph-vertex-label-dialog").dialog("close");
				}
			});
		$("#edas-graph-vertex-label-dialog")
			.dialog("option", "position", {my: "left center", at: "right center", of: event})
			.dialog("open");
		$("#edas-graph-vertex-label-dialog-input").select();
	};
	
	GraphState.prototype.onHoverInVertex = function (event) {
		
	};
	
	GraphState.prototype.onHoverOutVertex = function (event) {
		
	};
	
	GraphState.prototype.onClickEdge = function(event) {
		
	};
	
	GraphState.prototype.onDblClickEdge = function(event) {
		
	};
	
	GraphState.prototype.onHoverInEdge = function (event) {
		
	};
	
	GraphState.prototype.onHoverOutEdge = function (event) {
		
	};
	
	GraphState.prototype._getVertexIdFromEvent = function (event) {
		var target = $(event.target);
		return target.sAttr("vertexName");
	};
	
	GraphState.prototype._getVertexIdFromVertex = function (vertex) {
		return vertex.sAttr("vertexName");
	};
	
	GraphState.prototype._getVertexFromEvent = function (event) {
		var vertexId = this._getVertexIdFromEvent(event);
		return this.widget.getSvg().sById("-v-" + vertexId);
	};
	
	GraphState.prototype._getLabelFromEvent = function (event) {
		var vertexId = this._getVertexIdFromEvent(event);
		return this.widget.getSvg().sById("-l-" + vertexId);
	};
	
	GraphState.prototype._getLabelFromVertex = function (vertex) {
		var vertexId = this._getVertexIdFromVertex(vertex);
		return this.widget.getSvg().sById("-l-" + vertexId);
	};
	
	function SelectGraphState(widget) {
		GraphState.call(this, widget);
	};
	
	SelectGraphState.prototype = new GraphState();
	
	SelectGraphState.prototype.constructor = GraphState;
	
	SelectGraphState.prototype._onDragVertex = function(position) {
		var vertexName = this._getVertexIdFromVertex(this.dragStartVertex);
		var labelElement = this._getLabelFromVertex(this.dragStartVertex);
		
		var x = position.x;
		var y = position.y;
		
		this.dragStartVertex.cx(x);
		this.dragStartVertex.cy(y);
		
		labelElement.x(x);
		labelElement.y(y);
		
		this.widget.getSvg().sByAttr("vertexName1", vertexName).each(function() {
			$(this).sAttr("x1", x);
			$(this).sAttr("y1", y);
		});
		this.widget.getSvg().sByAttr("vertexName2", vertexName).each(function() {
			$(this).sAttr("x2", x);
			$(this).sAttr("y2", y);
		});
		
		this.widget.options.graph.vertices[vertexName].position.x = x;
		this.widget.options.graph.vertices[vertexName].position.y = y;
	};	
	
	SelectGraphState.prototype._onDragStopVertex = function(event, ui) {
		var vertexName = this._getVertexIdFromVertex(this.dragStartVertex);
		this.widget.saveVertex(vertexName);
	};
	
	function LineGraphState(widget) {
		GraphState.call(this, widget);
	};
	
	LineGraphState.prototype = new GraphState();
	
	LineGraphState.prototype.constructor = GraphState;
	
	LineGraphState.prototype._onDragStartVertex = function(position) {
		var vertexId = this._getVertexIdFromVertex(this.dragStartVertex);
		var x = this.dragStartVertex.cx();
		var y = this.dragStartVertex.cy();
		this.widget.getSvg().sCreateElement("line")
			.sId("edas-graph-line-" + vertexId)
			.sAttr("stroke", "black")
			.sAttr("stroke-width", "2")
			.sAttr("x1", x)
			.sAttr("y1", y)
			.sAttr("x2", x)
			.sAttr("y2", y)
			.prependTo(this.widget.getSvg());
	};
	
	LineGraphState.prototype._onDragVertex = function(position) {
		var vertexId = this._getVertexIdFromVertex(this.dragStartVertex);
		this.widget.getSvg().sById("edas-graph-line-" + vertexId)
			.sAttr("x2", position.x)
			.sAttr("y2", position.y);
	};
	
	LineGraphState.prototype._onDragStopVertex = function(position) {
		var startVertexId = this._getVertexIdFromVertex(this.dragStartVertex);
		var endVertexId = this._getVertexIdFromVertex(this.dragEndVertex);
		var lineElement = this.widget.getSvg().sById("edas-graph-line-" + startVertexId);
		if (this.dragEndVertex.length == 0 || startVertexId === endVertexId) {
			lineElement.remove();
			return;
		}
		
		var x = this.dragEndVertex.cx();
		var y = this.dragEndVertex.cy();
		lineElement
			.sAttr("x2", x)
			.sAttr("y2", y);
		this.widget.createEdge(startVertexId, endVertexId);
	};
	
	function DeleteGraphState(widget) {
		GraphState.call(this, widget);
	};
	
	DeleteGraphState.prototype = new GraphState();
	
	DeleteGraphState.prototype.constructor = GraphState;
	
	DeleteGraphState.prototype.onHoverInVertex = function(event) {
		this._getVertexFromEvent(event)
			.sAttr("fill", "red");
	};
	
	DeleteGraphState.prototype.onHoverOutVertex = function(event) {
		this._getVertexFromEvent(event)
			.sAttr("fill", "blue");
	};
	
	DeleteGraphState.prototype.onClickVertex = function(event) {
		var vertexId = this._getVertexIdFromEvent(event);
		this.widget.deleteVertex(vertexId);
	};
	
	DeleteGraphState.prototype.onHoverInEdge = function(event) {
		$(event.target)
			.sAttr("stroke", "red");
	};
	
	DeleteGraphState.prototype.onHoverOutEdge = function(event) {
		$(event.target)
			.sAttr("stroke", "black");
	};
	
	DeleteGraphState.prototype.onClickEdge = function(event) {
		var startVertexId = $(event.target).sAttr("vertexName1");
		var endVertexId = $(event.target).sAttr("vertexName2");
		this.widget.deleteEdge(startVertexId, endVertexId);
	};
} (jQuery));