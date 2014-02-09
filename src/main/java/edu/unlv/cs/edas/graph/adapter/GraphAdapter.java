package edu.unlv.cs.edas.graph.adapter;

import edu.unlv.cs.edas.graph.domain.Edge;
import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.GraphDto;
import edu.unlv.cs.edas.graph.domain.Vertex;

public interface GraphAdapter<V extends Vertex, E extends Edge, G extends Graph<V, E>, 
		D extends GraphDto<V, E>> {

	G createGraph(D graphDto);
	
	D createDto(G graph);
	
	void updateGraphFromDto(G graph, D graphDto);
	
}
