function begin(value) {
	state.uid = value;
	state.status = "";
	state.leaderUid = "";
	state.level = 0;
	state.convergecastEdge = null;
	state.min = null;
	state.connectUid = null;
	
	var min = null;
	var minEdge = null;
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		edge.status = "unknown";
		edge.initialized = false;
		edge.uid = null;
		edge.searchMessage = null;
		edge.convergecastMin = null;
		if (min == null || edge.weight < min) {
			min = edge.weight;
			minEdge = i;
		}
	}
	
	edgeStates[minEdge].status = "branch";
	edgeStates[minEdge]._style = "highlight";
	
	for (var j in edgeStates) {
		if (j == minEdge) {
			send(j, {type: "init", uid: value, min: true});
		} else {
			send(j, {type: "init", uid: value, min: false});
		}
	}
}

function onMessage(from, message) {
	if (message.type == "init") {
		handleInit(from, message);
	} else if (message.type == "broadcast-new-leader") {
		handleBroadcastNewLeader(from, message);
	} else if (message.type == "search") {
		handleSearch(from, message);
	} else if (message.type == "convergecast") {
		handleConvergecast(from, message);
	} else if (message.type == "broadcast-min") {
		handleBroadcastMin(from, message);
	} else if (message.type == "connect") {
		handleConnect(from, message);
	}
}

function handleInit(from, message) {
	var edge = edgeStates[from];
	edge.initialized = true;
	edge.uid = message.uid;
	if (message.min == true) {
		if (edge.status == "branch" && state.uid > message.uid) {
			state.status = "leader";
		}
		edge.status = "branch";
		edge._style = "highlight";
	}
	
	if (isInitComplete() == false) {
		return;
	}
	
	initializeEdgeStatus();
	broadcastNewLeader();
}

function isInitComplete() {
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.initialized == false) {
			return false;
		}
	}
	return true;
}

function initializeEdgeStatus() {
	if (state.status == "leader") {
		return;
	}
	
	var branchCount = 0;
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.status == "branch") {
			branchCount++;
		}
	}
	if (branchCount == 1) {
		state.status = "leaf";
	} else {
		state.status = "nonleaf";
	}
}

function broadcastNewLeader() {
	if (state.status != "leader") {
		return;
	}
	state.leaderUid = state.uid;
	state.level++;
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.status != "branch") {
			continue;
		}
		send(i, {type: "broadcast-new-leader", leaderUid: state.leaderUid, level: state.level});
	}
	
	sendSearch();
}

function handleBroadcastNewLeader(from, message) {
	state.min = null;
	state.connectUid = null;
	state.leaderUid = message.leaderUid;
	state.level = message.level;
	if (state.status == "leader") {
		state.status = "nonleaf";
	}
	updateBroadcastEdge(from);
	
	sendSearch();
	doConvergecast();
	
	if (state.status == "leaf") {
		return;
	}
	
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.status != "branch") {
			continue;
		}
		if (i == from) {
			continue;
		}
		send(i, message);
	}
}

function updateBroadcastEdge(from) {
	state.convergecastEdge = from;
}

function sendSearch() {
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.status == "branch"
				|| edge.status == "rejected") {
			continue;
		}
		send(i, {type: "search", leaderUid: state.leaderUid, level: state.level});
	}
}

function handleSearch(from, message) {
	var edge = edgeStates[from];
	edge.searchMessage = message;
	doConvergecast();
}

function doConvergecast() {
	updateSearchEdges();
	var min = getConvergcastMin();
	if (min == null) {
		return;
	}
	
	resetAfterConvergecast();
	
	if (state.status == "leader") {
		sendBroadcastMin(min);
	} else {
		send(state.convergecastEdge, {type: "convergecast", min: min});
	}
}

function updateSearchEdges() {
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (state.uid == "G" && state.level == 2 && edge.uid == "A") {
			log(edge.status);
		}
		if (edge.status != "unknown") {
			continue;
		}
		var searchMessage = edge.searchMessage;
		if (searchMessage == null) {
			continue;
		}
		if (state.level != searchMessage.level) {
			continue;
		}
		if (state.leaderUid == searchMessage.leaderUid) {
			edge.status = "rejected";
			edge._style = "disabled";
		} else {
			edge.status = "outgoing";
		}
		edge.searchMessage = null;
	}
}

function getConvergcastMin() {
	var min = getOutgoingMin();
	if (min == null) {
		return null;
	}
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.status != "branch") {
			continue;
		}
		if (i == state.convergecastEdge) {
			continue;
		}
		var convergecastMin = edge.convergecastMin;
		if (convergecastMin == null) {
			return null;
		}
		if (convergecastMin.value == null) {
			continue;
		}
		if (min.value == null 
				 || convergecastMin.value < min.value) {
			min = convergecastMin;
		}
	}
	return min;
}

function getOutgoingMin() {
	var min = {value: null, fromUid: null, toUid: null};
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.status == "unknown") {
			return null;
		}
		if (edge.status != "outgoing") {
			continue;
		}
		if (min.value == null || edge.weight < min.value) {
			min = {value: edge.weight, fromUid: state.uid, toUid: edge.uid};
		}
	}
	return min;
}

function resetAfterConvergecast() {
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.status == "outgoing") {
			edge.status = "unknown";
		}
		edge.searchMessage = null;
		edge.convergecastMin = null;
	}
}

function handleConvergecast(from, message) {
	var edge = edgeStates[from];
	edge.convergecastMin = message.min;
	doConvergecast();
}

function sendBroadcastMin(min) {
	if (min.value == null) {
		return;
	}
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.status != "branch") {
			continue;
		}
		send(i, {type: "broadcast-min", min: min});
	}
	reactToMin(min);
}

function handleBroadcastMin(from, message) {
	var min = message.min;
	
	if (reactToMin(min)) {
		return;
	}
	
	if (state.status != "leaf") {
		for (var i in edgeStates) {
			var edge = edgeStates[i];
			if (edge.status != "branch") {
				continue;
			}
			if (i == from) {
				continue;
			}
			send(i, {type: "broadcast-min", min: min});
		}
	}
}

function reactToMin(min) {
	if (min.fromUid != state.uid) {
		return false;
	}
	var to = getEdgeByUid(min.toUid);
	var toEdge = edgeStates[to];
	state.status = "nonleaf";
	toEdge.status = "branch";
	toEdge._style = "highlight";
	
	if (!findNewLeaderForConnect(to)) {
		send(to, {type: "connect"});
	}
	return true;
}

function getEdgeByUid(uid) {
	for (var i in edgeStates) {
		var edge = edgeStates[i];
		if (edge.uid == uid) {
			return i;
		}
	}
	return null;
}

function handleConnect(from, message) {
	var edge = edgeStates[from];
	state.status = "nonleaf";
	edge.status = "branch";
	edge._style = "highlight";
	findNewLeaderForConnect(from);
}

function findNewLeaderForConnect(from) {	
	if (state.connectUid == null) {
		state.connectUid = from;
		return;
	}
	if (state.connectUid != from) {
		return;
	}
	if (state.uid > edgeStates[from].uid) {
		state.status = "leader";
		broadcastNewLeader();
		return true;
	}
	return false;
}