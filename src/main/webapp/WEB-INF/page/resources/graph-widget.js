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
			var me = this;
			
			this._comm = $('<div></div>').graphComm({
				baseUrl: this.options.baseUrl + "/api/v1/design/graphs",
				errorCallback: function() {
					$("#edas-graph-error-message").show("bounce", 500, function() {
						setTimeout(function() {
							$("#edas-graph-error-message").hide("slide", {direction: "up"}, 500);
						}, 7000);
					});
				}
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
				})
				.click(function() {
					me.createVertex();
				});
			
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
				.click(function() {
					me.options.state = new GraphStates.SelectGraphState(me);
				})
				.click();
			$("#edas-graph-state-button-line")
				.button({text: false, icons: {primary: 'ui-icon-minus'}})
				.click(function() {
					me.options.state = new GraphStates.LineGraphState(me);
				});
			$("#edas-graph-state-button-delete")
				.button({text: false, icons: {primary: 'ui-icon-close'}})
				.click(function() {
					me.options.state = new GraphStates.DeleteGraphState(me);
				});
			
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
				.appendTo(this.element);
			$("#edas-graph-error-message")
				.position({my: "center top", at: "center top", of: this.element})
				.hide();
			$("#edas-graph-error-reload-button").button({
				text: false,
				icons: {primary: "ui-icon-refresh"}
			}).click(function() {
				me._loadGraph();
				$("#edas-graph-error-message").hide("slide", {direction: "up"}, 500);
			}).css("margin-left", "5px");
			
			this.options.url = this.options.baseUrl + "/api/v1/design/graphs";
			
			if (this.options.graphId) {
				this._loadGraph();
			} else {
				this._createGraph();
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
			var me = this;
			this._comm.graphComm("getJson", "/" + me.options.graphId, function(data) {
				me.options.graph = data;
			});
		},
		
		_loadSvgGraph: function() {
			var me = this;
			this._comm.graphComm("getSvg", "/" + me.options.graphId, function(data) {
				me._presentSvgGraph(data);
			});
		},
		
		_presentSvgGraph: function(data) {
			var importedSVGRootElement = document.importNode(data.documentElement, true);
			$("#edas-graph-svg-container").append(importedSVGRootElement);
			this._makeInteractive();
		},
		
		_makeInteractive: function() {
			var me = this;
			this.getSvg().mousedown(function(event) {
				me.options.state.onMouseDown(event);
			}).mousemove(function(event) {
				me.options.state.onMouseMove(event);
			}).mouseup(function(event) {
				me.options.state.onMouseUp(event);
			});
			
			$("circle, text").dblclick(function(event) {
				me.options.state.onDblClickVertex(event);
			}).click(function(event) {
				me.options.state.onClickVertex(event);
			}).hover(function(event) {
				me.options.state.onHoverInVertex(event);
			}, function(event) {
				me.options.state.onHoverOutVertex(event);
			});
			
			$("line").dblclick(function(event) {
				me.options.state.onDblClickEdge(event);
			}).click(function(event) {
				me.options.state.onClickEdge(event);
			}).hover(function(event) {
				me.options.state.onHoverInEdge(event);
			}, function(event) {
				me.options.state.onHoverOutEdge(event);
			});
		},
		
		_createGraph: function() {
			var me = this;
			this._comm.graphComm("postJson", "?empty=true", function(data) {
				me.options.graphId = data.id;
				me._loadGraph();
			});
		},
		
		simulateError: function() {
			this._comm.graphComm("getJson", "badurl");
		}
		
	});
} (jQuery));