$(function() {
	$("#runs-new-button").button({
		text: false,
		icons: {primary: "ui-icon-circle-plus"}
	});
	$(".runs-open-button").button({
		text: false,
		icons: {primary: "ui-icon-extlink"}
	});
	$(".runs-delete-button").button({
		text: false,
		icons: {primary: "ui-icon-trash"}
	}).click(function() {
		$("#runs-delete-id").val($(this).val());
		$("#runs-delete-form").submit();
	});
});