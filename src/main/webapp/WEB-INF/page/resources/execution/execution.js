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
		var hash = "";
		if (round.round == 0 && round.step == "e") {
			return;
		}
		if (round.step == "e") {
			hash = "#" + (round.round - 1) + "m";
		} else {
			hash = "#" + round.round + "e";
		}
		location.hash = hash;
	});
	
	$("#execution-next-button").button().click(function() {
		var round = getRound();
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
			location.hash = "#0e";
			return;
		}
		
		loadRound(round);
	};
	
	var loadRound = function(round) {
		$("#execution-round").html(round.round);
		comm.graphComm("getJson", "/" + round.round, function(data) {
			if (round.step == "e") {
				$("#execution-message-count").html(data.previousMessageCount);
			} else {
				$("#execution-message-count").html(data.messageCount);
			}
			showState(data, round);
		});
	};
	
	var showState = function(roundData, round) {
		$(".execution-vertex-state").remove();
		$(".execution-edge-message").remove();
		
		$("CIRCLE.vertex-circle").each(function() {
			var clientRect = this.getBoundingClientRect();
			var id = $(this).parent().get(0).getAttribute("vertexid");
			
			var state = roundData.graph.vertices[id].state;
			var origClass = this.getAttribute("orig-class");
			if (origClass == null || origClass == "") {
				origClass = this.getAttribute("class");
				this.setAttribute("orig-class", origClass);
			}
			this.setAttribute("class", origClass);
			if ("_style" in state) {
				this.setAttribute("class", origClass + " " + state._style);
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
		
		if (round.step != "m") {
			return;
		}
		
		$("G.edge-container").each(function() {
			var fromVertexId = this.getAttribute("vertexid1");
			var toVertexId = this.getAttribute("vertexid2");
			
			var display = roundData.graph.edges[fromVertexId + "-" + toVertexId].messages[0].messageDisplay;
			if (display == null || display == "") {
				return;
			}
			
			var fromVertex = $("CIRCLE.vertex-circle-" + fromVertexId);
			var toVertex = $("CIRCLE.vertex-circle-" + toVertexId);
			
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