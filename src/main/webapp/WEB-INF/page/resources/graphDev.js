$(function() {
	var graphWidget = $("#output").graph({
		baseUrl: $("#base-url").val()
	});
	
	$("#load-graph-button").button().click(function() {
		var graph = JSON.parse($("#input").val());
		graphWidget.graph("save", graph);
	});
	
	$("#export-graph-button").button().click(function() {
		$("#input").val(JSON.stringify(graphWidget.graph("graph"), undefined, 5));
	});
	
	$("#simulate-error").button().click(function() {
		graphWidget.graph("simulateError");
	});
	
});