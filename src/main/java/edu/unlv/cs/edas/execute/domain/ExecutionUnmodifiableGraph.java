package edu.unlv.cs.edas.execute.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import edu.unlv.cs.graph.UnmodifiableGraph;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=Visibility.NONE)
public class ExecutionUnmodifiableGraph extends UnmodifiableGraph<Integer, ExecutionVertex, 
		ExecutionEdge> implements ExecutionGraph {

	private static final long serialVersionUID = 6434788883378221880L;

	public ExecutionUnmodifiableGraph(ExecutionGraph graph) {
		super(graph);
	}

}
