function begin(value) {
	state = {
		u: value,
		status: 'unknown',
		send: [{v: value, f: 'out', h: 1}, {v: value, f: 'out', h: 1}],
		phase: 0
	};
	send(0, state.send[0]);
	send(1, state.send[1]);
}

function onMessages(messages) {
	state.send[0] = NULL;
	state.send[1] = NULL;
	
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
		state.send[0] = {
				v: state.u, 
				f: 'out', 
				h: Math.pow(2, state.phase)};
		state.send[1] = {
				v: state.u, 
				f: 'out', 
				h: Math.pow(2, state.phase)};
	}
	
	send(0, state.send[0]);
	send(1, state.send[1]);
}

function handleOutMessage(from, message) {
	var opposite = from == 0 ? 1 : 0;
	if (isNotNull(message) && message.f == 'out') {
		if (message.v > state.u) {
			if (message.h > 1){
				state.send[opposite] = {
						v: message.v, 
						f: 'out', 
						h: message.h - 1};
			}
			if (message.h == 1) {
				state.send[from] = {
						v: message.v, 
						f: 'in', 
						h: 1};
			}
		}
		if (message.v == state.u) {
			state.status = 'leader';
		}
	}
}

function handleInMessage(from, message) {
	var opposite = from == 0 ? 1 : 0;
	if (isNotNull(message) 
			&& message.f == 'in' 
			&& message.v != state.u) {
		state.send[opposite] = {v: message.v, f: 'in', h: 1};
	}
}