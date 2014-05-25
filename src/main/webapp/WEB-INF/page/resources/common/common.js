$(function() {
	$("#edas-header-logout-button").button();
	
	$("TABLE.edas-table").each(function() {
		$(this).addClass("ui-widget");
	});
	
	$(".edas-table TH").each(function() {
		$(this).addClass("ui-priority-secondary ui-widget-header");
	});
	
	$(".edas-table TD").each(function() {
		$(this).addClass("ui-widget-content");
	});
	
});