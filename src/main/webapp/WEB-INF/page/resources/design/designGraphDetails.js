$(function() {
	var graphId = $("#graph-id").val();
	var graphWidget = $("#graph").graph({
		baseUrl: $("#base-url").val(),
		graphId: graphId,
		graphLoaded: function(event, data) {
			$("#graph-details-name").val(data.name);
		},
		beforeSave: function(event, data) {
			data.name = $("#graph-details-name").val();
		}
	});
	
	$("#graph-details-save").button().click(function() {
		graphWidget.graph("save");
	});
	
	$("#graph-details-back").button();
});