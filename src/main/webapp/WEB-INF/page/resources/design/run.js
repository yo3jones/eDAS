$(function() {
	$("#run-save-button").button().click(function() {
		$("#run-put-form").submit();
	});
	$("#run-back-button").button();
	
	var graphValues = [];
	$("#run-graphs").children(".run-graph-value").each(function() {
		graphValues.push({
			label: $(this).children(".run-graph-name").val(),
			id: $(this).children(".run-graph-id").val()
		});
	});
	
	$("#run-graph-name").autocomplete({
		source: graphValues,
		change: function(event, ui) {
			$("#run-graph-id").val(ui.item ? ui.item.id : "");
			$("#run-graph-name").val(ui.item ? ui.item.label : "");
		}
	});
	
	var algorithmValues = [];
	$("#run-algorithms").children(".run-algorithm-value").each(function() {
		algorithmValues.push({
			label: $(this).children(".run-algorithm-name").val(),
			id: $(this).children(".run-algorithm-id").val()
		});
	});
	
	$("#run-algorithm-name").autocomplete({
		source: algorithmValues,
		change: function(event, ui) {
			$("#run-algorithm-id").val(ui.item ? ui.item.id : "");
			$("#run-algorithm-name").val(ui.item ? ui.item.label : "");
		}
	});
});