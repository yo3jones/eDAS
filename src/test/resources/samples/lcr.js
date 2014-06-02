function begin(value) {
	state.u = value;
	state.status = 'unknown';
	send(0, value);
}

function onMessage(sender, v) {
	if (v > state.u) {
		send(0, v);
	} else if(state.u == v) {
		state.status = 'leader';
		state._style = 'highlight';
	}
}