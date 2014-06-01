var state = {};

var NULL = {'NULL_MESSAGE': null};

function isNotNull(msg) {
	return !('NULL_MESSAGE' in msg);
}

function send(neighbor, message) {
	_mc.send(neighbor, message);
}

function getEdgeState(edge) {
	return edgeStates[edge];
}

function log(message) {
	_lc.log(message);
}