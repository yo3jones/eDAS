$(function() {
	$("#algorithms-new").button({
		text: false,
		icons: {primary: "ui-icon-circle-plus"}
	});
	$(".algorithm-open").button({
		text: false,
		icons: {primary: "ui-icon-extlink"}
	});
	$(".algorithm-delete").button({
		text: false,
		icons: {primary: "ui-icon-trash"}
	}).click(function() {
		$("#algorithms-delete-id").val($(this).val());
		$("#algorithms-delete-form").submit();
	});
});