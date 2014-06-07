function begin(value) {
	state.uid = value;
	state.tempUid = value;
	state.status = "active";
	state.neighbor = null;
	
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
	if (state.neighbor == null) {
		handleFirstMessage(message);
	} else {
		handleSecondMessage(message);
	}
}

function handleFirstMessage(message) {
	if (state.tempUid == message) {
		state._style = "highlight";
	} else {
		state.neighbor = message;
		send(0, message);
	}
}

function handleSecondMessage(message) {
	if (state.neighbor > state.tempUid 
			&& state.neighbor > message) {
		state.tempUid = state.neighbor;
		send(0, state.tempUid);
	} else {
		state.status = "relay";
		state._style = "disabled";
	}
	
	state.neighbor = null;
}

function handleRelay(message) {
	send(0, message);
}