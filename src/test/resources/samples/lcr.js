function begin(value) {
	state.u = value;
	state.send = value;
	state.status = 'unknown';
	
	send(0, state.send);
}

function onMessage(sender, v) {
	state.send = NULL;
	if (v > state.u) {
		state.send = v;
	} else if(state.u == v) {
		state.status = 'leader';
		state._style = 'highlight';
	} else if(v < state.u) {
		// do nothing
	}
	send(0, state.send);
}