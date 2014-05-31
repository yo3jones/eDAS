function begin(value) {
	state.u = value;
	state.status = 'unknown';
	
	edgeStates[0].send = value;
	
	send(0, edgeStates[0].send);
}

function onMessage(sender, v) {
	edgeStates[0].send = NULL;
	if (v > state.u) {
		edgeStates[0].send = v;
	} else if(state.u == v) {
		state.status = 'leader';
		state._style = 'highlight';
	} else if(v < state.u) {
		// do nothing
	}
	send(0, edgeStates[0].send);
}