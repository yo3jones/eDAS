package edu.unlv.cs.edas.execute.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.UnmodifiableMap;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.unlv.cs.edas.design.domain.DesignVertex;
import edu.unlv.cs.edas.design.domain.Position;
import edu.unlv.cs.edas.graph.domain.Vertex;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class ExecutionVertex implements Vertex {

	private static final long serialVersionUID = -6875228575419819997L;

	@JsonIgnore
	private DesignVertex design;
	
	@JsonIgnore
	private Map<String, Object> state;

	private String stateDisplay;
	
	public ExecutionVertex(DesignVertex design, Map<String, Object> state, String stateDisplay) {
		this.design = design;
		this.state = UnmodifiableMap.unmodifiableMap(new HashMap<String, Object>(state));
		this.stateDisplay = stateDisplay;
	}
	
	public DesignVertex getDesign() {
		return design;
	}
	
	@Override
	public Position getPosition() {
		return design.getPosition();
	}

	@Override
	public String getLabel() {
		return design.getLabel();
	}
	
	public Map<String, Object> getState() {
		return state;
	}
	
	public String getStateDisplay() {
		return stateDisplay;
	}
	
}
