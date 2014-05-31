function begin(value) {
	state = {
		u: value,
		status: 'unknown',
		phase: 0
	};
	
	edgeStates[0].send = {v: value, f: 'out', h: 1};
	edgeStates[1].send = {v: value, f: 'out', h: 1};
	
	send(0, edgeStates[0].send);
	send(1, edgeStates[1].send);
}

function onMessages(messages) {
	edgeStates[0].send = NULL;
	edgeStates[1].send = NULL;
	
	handleOutMessage(0, messages[0]);
	handleOutMessage(1, messages[1]);
	
	handleInMessage(0, messages[0]);
	handleInMessage(1, messages[1]);
	
	if (isNotNull(messages[0])
			&& isNotNull(messages[1]) 
			&& messages[0].v == state.u 
			&& messages[1].v == state.u 
			&& messages[0].f == 'in' 
			&& messages[1].f == 'in' 
			&& messages[0].h == 1 
			&& messages[1].h == 1) {
		state.phase = state.phase + 1;
		edgeStates[0].send = {
				v: state.u, 
				f: 'out', 
				h: Math.pow(2, state.phase)};
		edgeStates[1].send = {
				v: state.u, 
				f: 'out', 
				h: Math.pow(2, state.phase)};
	}
	
	send(0, edgeStates[0].send);
	send(1, edgeStates[1].send);
}

function handleOutMessage(from, message) {
	var opposite = from == 0 ? 1 : 0;
	if (isNotNull(message) && message.f == 'out') {
		if (message.v > state.u) {
			if (message.h > 1){
				edgeStates[opposite].send = {
						v: message.v, 
						f: 'out', 
						h: message.h - 1};
			}
			if (message.h == 1) {
				edgeStates[from].send = {
						v: message.v, 
						f: 'in', 
						h: 1};
			}
		}
		if (message.v == state.u) {
			state.status = 'leader';
			state._style = 'highlight';
		}
	}
}

function handleInMessage(from, message) {
	var opposite = from == 0 ? 1 : 0;
	if (isNotNull(message) 
			&& message.f == 'in' 
			&& message.v != state.u) {
		edgeStates[opposite].send = {v: message.v, f: 'in', h: 1};
	}
}