function begin(value) {
	state.uid = value;
	state.tempUid = value;
	state.status = "active";
	state.neighbor = [null, null];
	
	send(0, state.tempUid);
}

function onMessage(from, message) {
	if (state.status == "active") {
		handleActive(message);
	} else {
		handleRelay(message);
	}
}

function handleActive(message) {
	if (state.neighbor[0] == null) {
		if (state.tempUid == message) {
			state._style = "highlight";
		} else {
			state.neighbor[0] = message;
			send(0, message);
		}
		return;
	}
	
	state.neighbor[1] = message;
	
	if (state.neighbor[0] > state.tempUid && state.neighbor[0] > message) {
		state.tempUid = state.neighbor[0];
		send(0, state.tempUid);
	} else {
		state.status = "relay";
		state._style = "disabled";
	}
	
	state.neighbor = [null, null];
}

function handleRelay(message) {
	send(0, message);
}