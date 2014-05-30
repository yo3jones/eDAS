var state = {};

var NULL = {'NULL_MESSAGE': null};

function isNotNull(msg) {
	return !('NULL_MESSAGE' in msg);
}

function send(neighbor, message) {
	_mc.send(neighbor, message);
}