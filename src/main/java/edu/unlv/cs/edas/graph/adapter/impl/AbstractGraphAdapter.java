package edu.unlv.cs.edas.graph.adapter.impl;

import edu.unlv.cs.edas.graph.adapter.GraphAdapter;
import edu.unlv.cs.edas.graph.domain.Edge;
import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.GraphDto;
import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.edas.graph.domain.Vertex;
import edu.unlv.cs.graph.EdgeKey;

public abstract class AbstractGraphAdapter<V extends Vertex, E extends Edge, G extends Graph<V, E>, 
		D extends GraphDto<V, E>> implements GraphAdapter<V, E, G, D> {

	protected abstract G createGraph();
	
	protected abstract D createDto();
	
	@Override
	public G createGraph(D graphDto) {
		G graph = createGraph();
		
		updateGraphFromDto(graph, graphDto);
		
		return graph;
	}
	
	@Override
	public void updateGraphFromDto(G graph, D graphDto) {
		graph.clear();
		for (V vertex : graphDto.getVertices().values()) {
			graph.putVertex(vertex.getId(), vertex);
		}
		
		for (E edge : graphDto.getEdges().values()) {
			graph.putEdge(edge.getId(), edge);
		}
	}
	
	@Override
	public D createDto(G graph) {
		D dto = createDto();
		
		for (Key key : graph.getVertexSet()) {
			V vertex = graph.getVertex(key);
			dto.addVertex(vertex);
		}
		
		for (EdgeKey<Key> key : graph.getEdgeSet()) {
			E edge = graph.getEdge(key);
			dto.addEdge(edge);
		}
		
		return dto;
	}
	
}
