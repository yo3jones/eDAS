package edu.unlv.cs.edas.graph.manager;

import edu.unlv.cs.edas.graph.domain.Edge;
import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.GraphId;
import edu.unlv.cs.edas.graph.domain.Vertex;

public interface GraphManager<I extends GraphId, V extends Vertex, E extends Edge, G extends Graph<V, E>> {

	G getGraph(I graphId);
	
	I addGraph(G graph);
	
}
