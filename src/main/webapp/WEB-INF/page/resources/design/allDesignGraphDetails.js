$(function() {
	var comm = $("<div></div>").graphComm({
		baseUrl: $("#design-graph-api-url").val()
	}).appendTo($("BODY"));
	
	$(".graph-details-new").button({
		text: false,
		icons: {primary: "ui-icon-circle-plus"}
	});
	
	$(".graph-details-open").button({
		text: false,
		icons: {primary: "ui-icon-extlink"}
	});
	
	$(".graph-details-delete").button({
		text: false,
		icons: {primary: "ui-icon-trash"}
	}).click(function() {
		var id = $(this).val();
		comm.graphComm("deleteJson", "/" + id);
		$(this).closest("TR")
			.children('td, th')
			.animate({padding: 0})
			.wrapInner("<div/>")
			.children().slideUp(function() {
				$(this).closest("TR").remove();
		});
	});
});