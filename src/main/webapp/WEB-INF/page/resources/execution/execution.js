$(function() {
	
	$("body").keydown(function(event) {
		if (event.keyCode == 37) {
			$("#execution-prev-button").button().click();
		}
		if (event.keyCode == 39) {
			$("#execution-next-button").button().click();
		}
	});
	
	$("#execution-prev-button").button().click(function() {
		var round = getRound();
		
		if (round == null) {
			return;
		}
		
		var hash = "";
		if (round.round == 0 && round.step == "e") {
			hash = "#";
		} else if (round.step == "e") {
			hash = "#" + (round.round - 1) + "m";
		} else {
			hash = "#" + round.round + "e";
		}
		location.hash = hash;
	});
	
	$("#execution-next-button").button().click(function() {
		var round = getRound();
		
		if (round == null) {
			location.hash = "#0e";
			return;
		}
		
		var hash = "";
		if (round.step == "m") {
			hash = "#" + (round.round + 1) + "e";
		} else {
			hash = "#" + round.round + "m";
		}
		location.hash = hash;
	});
	
	var comm = $("<div></div>").graphComm({
		baseUrl: $("#execution-round-url").val(),
		error: function(event, message) {
			handleError(message);
		}
	}).appendTo($("body"));
	
	var handleError = function(message) {
		var messageDiv = $("<span></span>")
			.html(message)
			.addClass("execution-error")
			.appendTo(".execution-content");
		messageDiv.dialog({
			appendTo: ".execution-content",
			dialogClass: "ui-state-error",
			modal: true,
			width: "400px",
			closeOnEscape: false});
	};
	
	var getRound = function() {
		var hash = location.hash;
		if (hash == "") {
			return null;
		}
		
		var roundRegex = /[0-9]+/;
		if (!roundRegex.test(hash)) {
			return null;
		}
		var round = parseInt(roundRegex.exec(hash));
		
		var stepRegex = /[em]/;
		if (!stepRegex.test(hash)) {
			return null;
		}
		var step = stepRegex.exec(hash);
		
		return {round: round, step: step};
	};
	
	var load = function() {
		var round = getRound();
		if (round == null) {
			$("#execution-round").html("0");
			$("#execution-message-count").html("0");
			$(".execution-vertex-state").remove();
			$(".execution-edge-message").remove();
			$("CIRCLE.vertex-circle").each(function() {
				restoreClass(this);
			});
			$("G.edge-container").each(function() {
				var line = $(this).children("LINE.edge-line");
				restoreClass(line.get(0));
			});
		} else {
			loadRound(round);
		}
	};
	
	var loadRound = function(round) {
		$("#execution-round").html(round.round + 1);
		comm.graphComm("getJson", "/" + round.round, function(data) {
			for (var i in data.log) {
				if (window.console) {
					window.console.log(data.log[i]);
				}
			}
			if (round.step == "e") {
				$("#execution-message-count").html(data.previousMessageCount);
			} else {
				$("#execution-message-count").html(data.messageCount);
			}
			showState(data, round);
		});
	};
	
	var getOrigClass = function(element) {
		var origClass = element.getAttribute("orig-class");
		if (origClass == null || origClass == "") {
			origClass = element.getAttribute("class");
			element.setAttribute("orig-class", origClass);
		}
		return origClass;
	};
	
	var restoreClass = function(element) {
		var origClass = getOrigClass(element);
		element.setAttribute("class", origClass);
	};
	
	var addClass = function(element, newClass) {
		var origClass = getOrigClass(element);
		element.setAttribute("class", origClass + " " + newClass);
	};
	
	var showState = function(roundData, round) {
		$(".execution-vertex-state").remove();
		$(".execution-edge-message").remove();
		
		$("circle.vertex-circle").each(function() {
			var clientRect = this.getBoundingClientRect();
			var id = $(this).parent().get(0).getAttribute("vertexid");
			
			var state = roundData.graph.vertices[id].state;
			restoreClass(this);
			if ("_style" in state) {
				addClass(this, state._style);
			}
			
			var display = roundData.graph.vertices[id].stateDisplay;
			if (display.length == 0) {
				return;
			}
			
			var div = $("<div class='execution-vertex-state ui-corner-all'>" + display + "</div>")
					.appendTo($(this).offsetParent())
					.css({position: "absolute"});
			
			var top = (($(this).position().top - div.outerHeight() + 14) / $(this).offsetParent().height()) * 100;
			var left = (($(this).position().left + (clientRect.width/2) - (div.outerWidth()/2)) / $(this).offsetParent().width()) * 100;
			
			div.css({
				top: top + "%",
				left: left + "%"});
		});
		
		$("g.edge-container").each(function() {
			var line = $(this).children("line.edge-line");
			var fromVertexId = this.getAttribute("vertexid1");
			var toVertexId = this.getAttribute("vertexid2");
			
			var edge = roundData.graph.edges[fromVertexId + "-" + toVertexId];
			
			restoreClass(line.get(0));
			if ("_style" in edge.state) {
				addClass(line.get(0), edge.state._style);
			}
			
			if (round.step != "m") {
				return;
			}
			
			if (edge.messages.length == 0) {
				return;
			}
			
			var display = edge.messages[0].messageDisplay;
			if (display == null || display == "") {
				return;
			}
			
			var fromVertex = $("circle.vertex-circle-" + fromVertexId);
			var toVertex = $("circle.vertex-circle-" + toVertexId);
			
			var fromVertexBoundingRect = fromVertex.get(0).getBoundingClientRect();
			var toVertexBoundingRect = toVertex.get(0).getBoundingClientRect();
			
			var fromVertexTop = fromVertex.position().top + (fromVertexBoundingRect.height / 2);
			var fromVertexLeft = fromVertex.position().left + (fromVertexBoundingRect.width / 2);
			var toVertexTop = toVertex.position().top + (toVertexBoundingRect.height / 2);
			var toVertexLeft = toVertex.position().left + (toVertexBoundingRect.width / 2);
			
			var div = $("<div class='execution-edge-message ui-corner-all'>" + display + "</div>")
					.appendTo($(this).offsetParent())
					.css({position: "absolute"});
			
			var top = (((toVertexTop + (fromVertexTop - toVertexTop) / 4) - (div.outerHeight() / 2)) / $(this).offsetParent().height()) * 100;
			var left = (((toVertexLeft + (fromVertexLeft - toVertexLeft) / 4) - (div.outerWidth() / 2)) / $(this).offsetParent().width()) * 100;
			
			div.css({
				top: top + "%",
				left: left + "%"});
		});
		
	};
	
	load();
	
	$(window).bind("hashchange", function() {
		load();
	});
});