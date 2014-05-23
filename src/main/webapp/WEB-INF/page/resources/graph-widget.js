(function($) {
	
	$.widget("edas.graph", {
		
		options: {
			baseUrl: "",
			url: null,
			graphId: null,
			graph: null,
			state: null
		},
		
		_comm: null,
		
		_create: function() {
			this._comm = $('<div></div>').graphComm({
				baseUrl: this.options.baseUrl + "/api/v1/design/graphs",
				error: $.proxy(this._onCommError, this),
				groupComplete: $.proxy(this._showInfo, this)
			}).appendTo(this.element);
			
			var toolbarElement = $('<div id="edas-graph-toolbar"></div>')
				.appendTo(this.element.append())
				.css("position", "absolute")
				.css("top", "0px")
				.css("height", "40px")
				.css("left", "0px")
				.css("right", "0px");
			
			var toolbarImplElement = $('<div id="edas-graph-toolbar-impl" class="ui-widget-header ui-corner-all"></div>')
				.appendTo(toolbarElement)
				.css("display", "inline-block")
				.css("padding", "4px");
			
			$('<button id="edas-graph-button-create-vertex" >Create Vertex</button>')
				.appendTo(toolbarImplElement)
				.button({
					text: false,
					icons: {primary: "ui-icon-circle-plus"}
				}).click($.proxy(this.createVertex, this));
			
			$(	'<span id="edas-graph-state-button-group">' +
					'<input type="radio" id="edas-graph-state-button-select" name="edas-graph-state-button-group">' + 
					'<label for="edas-graph-state-button-select">Select</label>' +
					'<input type="radio" id="edas-graph-state-button-line" name="edas-graph-state-button-group">' +
					'<label for="edas-graph-state-button-line">Line</label>' +
					'<input type="radio" id="edas-graph-state-button-delete" name="edas-graph-state-button-group">' +
					'<label for="edas-graph-state-button-delete">Delete</label>' +
				'</span>').appendTo(toolbarImplElement).buttonset();
			$("#edas-graph-state-button-select")
				.button({text: false, icons: {primary: 'ui-icon-arrow-4'}})
				.click($.proxy(this._changeState, this, new GraphStates.SelectGraphState(this)))
				.click();
			$("#edas-graph-state-button-line")
				.button({text: false, icons: {primary: 'ui-icon-minus'}})
				.click($.proxy(this._changeState, this, new GraphStates.LineGraphState(this)));
			$("#edas-graph-state-button-delete")
				.button({text: false, icons: {primary: 'ui-icon-close'}})
				.click($.proxy(this._changeState, this, new GraphStates.DeleteGraphState(this)));
			
			$('<button id="edas-graph-button-refresh" >Refresh</button>')
			.appendTo(toolbarImplElement)
			.button({
				text: false,
				icons: {primary: "ui-icon-refresh"}
			}).click($.proxy(this.refresh, this));
			
			$('<div id="edas-graph-svg-container"></div>').appendTo(this.element)
				.css("position", "absolute")
				.css("top", "40px")
				.css("bottom", "0px")
				.css("left", "0px")
				.css("right", "0px");
			
			$(  '<div id="edas-graph-vertex-label-dialog">' +
					'<input type="text" id="edas-graph-vertex-label-dialog-input" class="ui-widget-content ui-corner-all">' +
				'</div>')
				.appendTo(this.element)
				.dialog({
					autoOpen: false,
					width: 165,
					height: 40,
					modal: true,
					closeOnEscape: true,
					draggable: false,
					resizable: false
				});
			$(".ui-dialog-titlebar").hide();
			
			$(	'<div id="edas-graph-error-message" class="ui-widget">' +
					'<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">' + 
						'<p>' +
						'<span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>' + 
						'<strong>Alert:</strong>An error occurred' +
						'<button id="edas-graph-error-reload-button">refresh</button>' +
						'</p>' +
					'</div>' +
				'</div>')
				.css("position", "absolute")
				.width("200px")
				.height("50px")
				.appendTo(this.element).hide();
			
			$(	'<div id="edas-graph-info-message" class="ui-widget">' +
					'<div class="ui-state-highlight ui-corner-all" style="padding: 0 .7em;">' +
						'<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>' +
						'The graph is updated</p>' +
					'</div>' +
				'</div>')
				.css("position", "absolute")
				.width("200px")
				.height("50px")
				.appendTo(this.element).hide();
			
			$("#edas-graph-error-reload-button").button({
				text: false,
				icons: {primary: "ui-icon-refresh"}
			}).click($.proxy(this.refresh, this)).css("margin-left", "5px");
			
			this.options.url = this.options.baseUrl + "/api/v1/design/graphs";
			
			if (this.options.graphId) {
				this._loadGraph();
			} else {
				this._createGraph();
			}
		},
		
		refresh: function() {
			this._loadGraph();
			this._hideError();
		},
		
		_changeState: function(newState) {
			this.options.state = newState;
			newState.init();
		},
		
		_onCommError: function() {
			this._showError();
		},
		
		_showError: function() {
			this._hideInfo($.proxy(function() {
				$("#edas-graph-error-message")
				.show()
				.position({my: "center top", at: "center top", of: this.element})
				.hide()
				.show("bounce", 500, $.proxy(function() {
					setTimeout($.proxy(this._hideError, this), 7000);
				}, this));
			}, this));
		},
		
		_hideError: function(then) {
			if ($("#edas-graph-error-message").is(":visible")) {
				$("#edas-graph-error-message").hide("slide", {direction: "up"}, 500, then);
			} else {
				if (then) {
					then();
				}
			}
		},
		
		_showInfo: function() {
			this._hideError($.proxy(function() {
				$("#edas-graph-info-message")
					.show()
					.position({my: "center top", at: "center top", of: this.element})
					.hide()
					.show("slide", {direction: "up"}, 500, $.proxy(function() {
						setTimeout($.proxy(this._hideInfo, this), 1000);
					}, this));
			}, this));
		},
		
		_hideInfo: function(then) {
			if ($("#edas-graph-info-message").is(":visible")) {
				$("#edas-graph-info-message").hide("slide", {direction: "up"}, 500, then);
			} else {
				if (then) {
					then();
				}
			}
		},
		
		getSvg: function() {
			return $("svg", this.element);
		},
		
		getSvgContainer: function() {
			return $("#edas-graph-svg-container");
		},
		
		graph: function() {
			return this.options.graph;
		},
		
		save: function(graph) {
			var url = "/" + this.options.graphId;
			this._comm.graphComm("putJson", url, JSON.stringify(graph), function() {
				$("#edas-graph-state-button-select").click();
			});
			this._loadGraph();
		},
		
		saveVertex: function(vertexId) {
			var vertex = this.options.graph.vertices[vertexId];
			var url = "/" + this.options.graphId + "/vertices/" + vertexId;
			this._comm.graphComm("putJson", url, JSON.stringify(vertex));
		},
		
		createVertex: function() {
			this._comm.graphComm("postJson", "/" + this.options.graphId + "/vertices");
			this._loadGraph();
			$("#edas-graph-state-button-select").click();
		},
		
		deleteVertex: function(vertexId) {
			this._comm.graphComm("deleteJson", "/" + this.options.graphId + "/vertices/" + vertexId);
			this._loadGraph();
		},
		
		createEdge: function(startVertexId, endVertexId) {
			var url = "/" + this.options.graphId + "/edges/" + startVertexId + "-" + endVertexId;
			this._comm.graphComm("postJson", url);
			this._loadGraph();
		},
		
		saveEdge: function(edgeId) {
			var edge = this.options.graph.edges[edgeId];
			var url = "/" + this.options.graphId + "/edges/" + edgeId;
			this._comm.graphComm("putJson", url, JSON.stringify(edge));
		},
		
		deleteEdge: function(startVertexId, endVertexId) {
			var url = "/" + this.options.graphId + "/edges/" + startVertexId + "-" + endVertexId;
			this._comm.graphComm("deleteJson", url);
			this._loadGraph();
		},
		
		_loadGraph: function() {
			$("#edas-graph-svg-container").html("");
			this._loadJsonGraph();
			this._loadSvgGraph();
		},
		
		_loadJsonGraph: function() {
			this._comm.graphComm("getJson", "/" + this.options.graphId, $.proxy(function(data) {
				this.options.graph = data;
			}, this));
		},
		
		_loadSvgGraph: function() {
			this._comm.graphComm("getSvg", "/" + this.options.graphId, $.proxy(function(data) {
				this._presentSvgGraph(data);
			}, this));
		},
		
		_presentSvgGraph: function(data) {
			var importedSVGRootElement = document.importNode(data.documentElement, true);
			$("#edas-graph-svg-container").append(importedSVGRootElement);
			this._makeInteractive();
		},
		
		_makeInteractive: function() {
			this.getSvg().mousedown($.proxy(function(event) {
				this.options.state.onMouseDown(event);
			}, this)).mousemove($.proxy(function(event) {
				this.options.state.onMouseMove(event);
			}, this)).mouseup($.proxy(function(event) {
				this.options.state.onMouseUp(event);
			}, this));
			
			$("circle.vertex-circle, text.vertex-label").dblclick($.proxy(function(event) {
				this.options.state.onDblClickVertex(event);
			}, this)).click($.proxy(function(event) {
				this.options.state.onClickVertex(event);
			}, this)).hover($.proxy(function(event) {
				this.options.state.onHoverInVertex(event);
			}, this), $.proxy(function(event) {
				this.options.state.onHoverOutVertex(event);
			}, this));
			
			$("line.edge-line, line.edge-line-select, text.edge-weight").dblclick($.proxy(function(event) {
				this.options.state.onDblClickEdge(event);
			}, this)).click($.proxy(function(event) {
				this.options.state.onClickEdge(event);
			}, this)).hover($.proxy(function(event) {
				this.options.state.onHoverInEdge(event);
			}, this), $.proxy(function(event) {
				this.options.state.onHoverOutEdge(event);
			}, this));
		},
		
		_createGraph: function() {
			this._comm.graphComm("postJson", "", $.proxy(function(data) {
				this.options.graphId = data.id;
				this._loadGraph();
			}, this));
		},
		
		simulateError: function() {
			this._comm.graphComm("getJson", "badurl");
		}
		
	});
} (jQuery));