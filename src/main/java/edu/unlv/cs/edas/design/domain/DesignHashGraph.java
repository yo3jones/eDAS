package edu.unlv.cs.edas.design.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import java.util.Map;

import org.springframework.data.annotation.PersistenceConstructor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.unlv.cs.graph.EdgeKey;
import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.HashGraph;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=Visibility.NONE)
public class DesignHashGraph extends HashGraph<Integer, DesignVertex, DesignEdge> implements 
		DesignGraph {

	private static final long serialVersionUID = 8667250637546891398L;

	@PersistenceConstructor
	@JsonCreator
	DesignHashGraph(@JsonProperty("vertices") Map<Integer, DesignVertex> vertices, 
			@JsonProperty("edges") Map<EdgeKey<Integer>, DesignEdge> edges) {
		super();
		for (Integer vertexKey : vertices.keySet()) {
			putVertex(vertexKey, vertices.get(vertexKey));
		}
		for (EdgeKey<Integer> edgeKey : edges.keySet()) {
			putEdge(edgeKey, edges.get(edgeKey));
		}
	}
	
	public DesignHashGraph() {
		super();
	}
	
	public DesignHashGraph(Graph<Integer, DesignVertex, DesignEdge> graph) {
		super(graph);
	}
	
}
