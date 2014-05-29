package edu.unlv.cs.edas.execute.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import edu.unlv.cs.graph.HashGraph;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=Visibility.NONE)
public class ExecutionHashGraph extends HashGraph<Integer, ExecutionVertex, ExecutionEdge> 
		implements ExecutionGraph {

	private static final long serialVersionUID = 5871668208764666162L;

	public ExecutionHashGraph() {
		super();
	}
	
	public ExecutionHashGraph(ExecutionGraph graph) {
		super(graph);
	}
	
}
