package edu.unlv.cs.edas.execute.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.commons.collections4.map.UnmodifiableMap;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.unlv.cs.edas.design.domain.DesignEdge;
import edu.unlv.cs.edas.graph.domain.Edge;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public final class ExecutionEdge implements Edge {

	private static final long serialVersionUID = -2498255078981953488L;

	@JsonIgnore
	private DesignEdge design;
	
	private Map<String, Object> state;
	
	private List<Message> messages;
	
	public ExecutionEdge(DesignEdge design, Map<String, Object> state, List<Message> messages) {
		this.design = design;
		this.state = UnmodifiableMap.unmodifiableMap(new HashMap<String, Object>(state));
		this.messages = UnmodifiableList.unmodifiableList(messages);
	}
	
	public final DesignEdge getDesign() {
		return design;
	}
	
	@Override
	public final Integer getWeight() {
		return design.getWeight();
	}

	public final Map<String, Object> getState() {
		return state;
	}
	
	public final List<Message> getMessages() {
		return messages;
	}
	
}
