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
	
	GraphState.prototype.init = function () {
		
	};
	
	GraphState.prototype._clearClasses = function() {
		this.widget.element.removeClass("edas-graph-state-select " +
				"edas-graph-state-line " +
				"edas-graph-state-delete");
	};
	
	GraphState.prototype.onMouseDown = function(event) {
		var target = $(event.target);
		var position = this._getPositionFromEvent(event);
		if (target.is("circle.vertex-circle, text.vertex-label")) {
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
		var svgPosition = this.widget.getSvgContainer().offset();
		return {
			x: event.pageX - svgPosition.left,
			y: event.pageY - svgPosition.top
		};
	};
	
	GraphState.prototype._onDragStartVertex = function(position) {
		
	};
	
	GraphState.prototype._onDragVertex = function(position) {
		
	};
	
	GraphState.prototype._onDragStopVertex = function(position) {
		
	};
	
	GraphState.prototype.onClickVertex = function(event) {
		
	};
	
	GraphState.prototype._showInput = function(event, val, onEnter) {
		$("#edas-graph-vertex-label-dialog-input")
		.val(val)
		.off("keypress")
		.keypress($.proxy(function(event) {
			if (event.which == 13) {
				var value = $("#edas-graph-vertex-label-dialog-input").val();
				onEnter(value);
				$("#edas-graph-vertex-label-dialog").dialog("close");
			}
		}, this));
		$("#edas-graph-vertex-label-dialog")
			.dialog("option", "position", {my: "left center", at: "right center", of: event})
			.dialog("open");
		$("#edas-graph-vertex-label-dialog-input").select();
	};
	
	GraphState.prototype.onDblClickVertex = function(event) {
		var vertexId = this._getVertexIdFromEvent(event);
		var labelElement = this._getLabelFromEvent(event);
		
		this._showInput(event, labelElement.text(), $.proxy(function(value) {
			labelElement.text(value);
			this.widget.options.graph.vertices[vertexId].label = value;
			this.widget.saveVertex(vertexId);
		}, this));
	};
	
	GraphState.prototype.onDblClickEdge = function(event) {
		var target = $(event.target);
		var edge = target.parent();
		var weight = edge.find("text.edge-weight");
		var edgeId = edge.sAttr("edgeId");
		
		this._showInput(event, weight.text(), $.proxy(function(value) {
			if (!$.isNumeric(value)) {
				return
			}
			value = parseInt(value);
			weight.text(value);
			this.widget.options.graph.edges[edgeId].weight = value;
			this.widget.saveEdge(edgeId);
		}, this));
	};
	
	GraphState.prototype.onHoverInVertex = function (event) {
		
	};
	
	GraphState.prototype.onHoverOutVertex = function (event) {
		
	};
	
	GraphState.prototype.onClickEdge = function(event) {
		
	};
	
	GraphState.prototype.onHoverInEdge = function (event) {
		
	};
	
	GraphState.prototype.onHoverOutEdge = function (event) {
		
	};
	
	GraphState.prototype._getVertexIdFromEvent = function (event) {
		return $(event.target).parent().sAttr("vertexId");
	};
	
	GraphState.prototype._getVertexIdFromVertex = function (vertex) {
		return vertex.parent().sAttr("vertexId");
	};
	
	GraphState.prototype._getVertexFromEvent = function (event) {
		return $(".vertex-circle", $(event.target).parent());
	};
	
	GraphState.prototype._getLabelFromEvent = function (event) {
		return $(".vertex-label", $(event.target).parent());
	};
	
	GraphState.prototype._getLabelFromVertex = function (vertex) {
		return $(".vertex-label", vertex.parent());
	};
	
	function SelectGraphState(widget) {
		GraphState.call(this, widget);
	};
	
	SelectGraphState.prototype = new GraphState();
	
	SelectGraphState.prototype.constructor = GraphState;
	
	SelectGraphState.prototype.init = function() {
		this._clearClasses();
		this.widget.element.addClass("edas-graph-state-select");
	};
	
	SelectGraphState.prototype._onDragVertex = function(position) {
		var vertexId = this._getVertexIdFromVertex(this.dragStartVertex);
		var labelElement = this._getLabelFromVertex(this.dragStartVertex);
		
		var x = position.x;
		var y = position.y;
		
		this.dragStartVertex.cx(x);
		this.dragStartVertex.cy(y);
		
		labelElement.x(x);
		labelElement.y(y);
		
		var edges = $("g.edge-container-" + vertexId);
		
		var me = this;
		edges.sByAttr("vertexId1", vertexId).find("line").sAttr("x1", x).sAttr("y1", y);
		edges.sByAttr("vertexId2", vertexId).find("line").sAttr("x2", x).sAttr("y2", y);
		edges.each(function () {
			var edge = $(".edge-line", this);
			var weight = $(".edge-weight", this);
			var p = me._getLinePosition(edge);
			var mp = me._getMidPoint(p);
			var weightOffset = me._getWeightOffset(p);
			weight.sAttr("x", mp.x);
			weight.sAttr("y", mp.y);
			weight.sAttr("dx", weightOffset.x);
			weight.sAttr("dy", weightOffset.y);
		});
		
		this.widget.options.graph.vertices[vertexId].x = x;
		this.widget.options.graph.vertices[vertexId].y = y;
	};
	
	SelectGraphState.prototype._getLinePosition = function(line) {
		return {
			x1: parseInt(line.sAttr("x1")),
			y1: parseInt(line.sAttr("y1")),
			x2: parseInt(line.sAttr("x2")),
			y2: parseInt(line.sAttr("y2")),
			d: parseInt(line.parent().sAttr("weightDistance"))
		};
	};
	
	SelectGraphState.prototype._getMidPoint = function(p) {
		return {
			x: (p.x1 + p.x2) / 2,
			y: (p.y1 + p.y2) / 2
		};
	};
	
	SelectGraphState.prototype._getWeightOffset = function(p) {
		var lineDx = p.x1 - p.x2;
		var lineDy = p.y1 - p.y2;
		
		var dx;
		var dy;
		
		if (lineDx == 0) {
			dx = p.d;
			dy = 0;
		} else if (lineDy == 0) {
			dx = 0;
			dy = p.d;
		} else {
			var lineM = lineDy / lineDx;
			var m = -1 * (1 / lineM);
			var k = Math.sqrt(1 + m * m);
			dx = Math.round(p.d / k);
			dy = Math.round((m * p.d) / k);
		}
		
		return {
			x: dx,
			y: dy
		};
	};
	
	SelectGraphState.prototype._onDragStopVertex = function(event, ui) {
		var vertexId = this._getVertexIdFromVertex(this.dragStartVertex);
		this.widget.saveVertex(vertexId);
	};
	
	function LineGraphState(widget) {
		GraphState.call(this, widget);
	};
	
	LineGraphState.prototype = new GraphState();
	
	LineGraphState.prototype.constructor = GraphState;
	
	LineGraphState.prototype.init = function() {
		this._clearClasses();
		this.widget.element.addClass("edas-graph-state-line");
	};
	
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
	
	DeleteGraphState.prototype.init = function() {
		this._clearClasses();
		this.widget.element.addClass("edas-graph-state-delete");
	};
	
	DeleteGraphState.prototype.onClickVertex = function(event) {
		var vertexId = this._getVertexIdFromEvent(event);
		this.widget.deleteVertex(vertexId);
	};
	
	DeleteGraphState.prototype.onClickEdge = function(event) {
		var edge = $(event.target).parent();
		var startVertexId = edge.sAttr("vertexId1");
		var endVertexId = edge.sAttr("vertexId2");
		this.widget.deleteEdge(startVertexId, endVertexId);
	};
} (jQuery));