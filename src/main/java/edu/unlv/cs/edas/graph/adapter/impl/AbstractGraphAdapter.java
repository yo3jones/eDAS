package edu.unlv.cs.edas.graph.adapter.impl;

import edu.unlv.cs.edas.graph.adapter.GraphAdapter;
import edu.unlv.cs.edas.graph.domain.Edge;
import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.edas.graph.domain.Vertex;
import edu.unlv.cs.edas.graph.dto.EdgeDto;
import edu.unlv.cs.edas.graph.dto.GraphDto;
import edu.unlv.cs.edas.graph.dto.VertexDto;
import edu.unlv.cs.graph.EdgeKey;

/**
 * An abstract implementation of {@link GraphAdapter}. This class will handle
 * the organization of creating the adapted object.
 * 
 * @author Chris Jones
 * 
 * @param <V>
 *            The vertex type.
 * @param <E>
 *            The edge type.
 * @param <G>
 *            The graph type.
 * @param <VD>
 *            The vertex DTO type.
 * @param <ED>
 *            The edge DTO type.
 * @param <D>
 *            The graph DTO type.
 */
public abstract class AbstractGraphAdapter<V extends Vertex, E extends Edge, G extends Graph<V, E>, 
		VD extends VertexDto, ED extends EdgeDto, D extends GraphDto<Integer, VD, ED>> 
		implements GraphAdapter<V, E, G, Integer, VD, ED, D> {

	/**
	 * Returns a new blank graph object.
	 * 
	 * @return A new blank graph object.
	 */
	protected abstract G createGraph();
	
	/**
	 * Returns a new blank graph DTO object.
	 * 
	 * @return A new blank graph DTO object.
	 */
	protected abstract D createDto();
	
	/**
	 * Returns a new blank vertex object.
	 * 
	 * @return A new blank vertex object.
	 */
	protected abstract V createVertex();
	
	/**
	 * Returns a new blank vertex DTO object.
	 * 
	 * @return A new blank vertex DTO object.
	 */
	protected abstract VD createVertexDto();
	
	/**
	 * Updates the given vertex with the given vertex DTO.
	 * 
	 * @param vertex
	 *            The vertex.
	 * @param vertexDto
	 *            The vertex DTO.
	 */
	protected abstract void updateVertexFromDto(V vertex, VD vertexDto);
	
	/**
	 * Updates the given vertex DTO with the given vertex.
	 * 
	 * @param vertexDto
	 *            The vertex DTO.
	 * @param vertex
	 *            The vertex.
	 */
	protected abstract void updateVertexDtoFromVertex(VD vertexDto, V vertex);
	
	/**
	 * Returns a new blank edge object.
	 * 
	 * @return A new blank edge object.
	 */
	protected abstract E createEdge();
	
	/**
	 * Returns a new blank edge DTO object.
	 * 
	 * @return A new blank edge DTO object.
	 */
	protected abstract ED createEdgeDto();
	
	/**
	 * Updates the given edge with the given edge DTO.
	 * 
	 * @param edge
	 *            The edge.
	 * @param edgeDto
	 *            The edge DTO.
	 */
	protected abstract void updateEdgeFromDto(E edge, ED edgeDto);
	
	/**
	 * Updates the given edge DTO with the given edge.
	 * 
	 * @param edgeDto
	 *            The edge DTO.
	 * @param edge
	 *            The edge.
	 */
	protected abstract void updateEdgeDtoFromEdge(ED edgeDto, E edge);
	
	@Override
	public G createGraph(D graphDto) {
		// create an empty graph
		G graph = createGraph();
		
		// update the graph with values from the DTO
		updateGraphFromDto(graph, graphDto);
		
		return graph;
	}
	
	@Override
	public void updateGraphFromDto(G graph, D graphDto) {
		/*
		 * First make sure there are no vertices or edges on the graph to
		 * update.
		 */
		graph.clear();
		
		// traverse all the vertices of the DTO and add them to the graph
		for (String vertexKeyString : graphDto.getVertices().keySet()) {
			Key key = getVertexKeyFromString(vertexKeyString);
			VD vertexDto = graphDto.getVertices().get(vertexKeyString);
			V vertex = createVertex(vertexDto);
			graph.putVertex(key, vertex);
		}
		
		// traverse all the edges of the DTO and add them to the graph
		for (String edgeKeyString : graphDto.getEdges().keySet()) {
			EdgeKey<Key> key = getEdgeKeyFromString(edgeKeyString);
			ED edgeDto = graphDto.getEdges().get(edgeKeyString);
			E edge = createEdge(edgeDto);
			graph.putEdge(key, edge);
		}
	}
	
	@Override
	public D createDto(G graph) {
		// create a new blank graph DTO
		D dto = createDto();
		
		// traverse all the vertices of the graph and add them to the DTO
		for (Key key : graph.getVertexSet()) {
			V vertex = graph.getVertex(key);
			VD vertexDto = createDtoVertex(vertex);
			dto.addVertex(key.getId(), vertexDto);
		}
		
		// traverse all the edges of the graph and add them to the DTO
		for (EdgeKey<Key> key : graph.getEdgeSet()) {
			E edge = graph.getEdge(key);
			ED edgeDto = createDtoEdge(edge);
			dto.addEdge(key.getFromKey().getId(), key.getToKey().getId(), edgeDto);
		}
		
		return dto;
	}
	
	@Override
	public V createVertex(VD vertexDto) {
		V vertex = createVertex();
		updateVertexFromDto(vertex, vertexDto);
		return vertex;
	}
	
	@Override
	public VD createDtoVertex(V vertex) {
		VD vertexDto = createVertexDto();
		updateVertexDtoFromVertex(vertexDto, vertex);
		return vertexDto;
	}
	
	@Override
	public E createEdge(ED edgeDto) {
		E edge = createEdge();
		updateEdgeFromDto(edge, edgeDto);
		return edge;
	}
	
	@Override
	public ED createDtoEdge(E edge) {
		ED edgeDto = createEdgeDto();
		updateEdgeDtoFromEdge(edgeDto, edge);
		return edgeDto;
	}
	
	/**
	 * Returns a vertex key from the given string.
	 * <p>
	 * The string key is assumed to be an integer.
	 * 
	 * @param vertexKeyString
	 *            A string that represents a vertex key.
	 * @return A vertex key from the given string.
	 */
	private Key getVertexKeyFromString(String vertexKeyString) {
		Integer vertexKeyInteger = Integer.parseInt(vertexKeyString);
		return new Key(vertexKeyInteger);
	}
	
	/**
	 * Returns an edge key from the given string.
	 * <p>
	 * The format of the string is assumed to be {fromVertexKey}-{toVertexKey}
	 * where the fromVertexKey and toVertexKeys are integers.
	 * 
	 * @param edgeKeyString
	 *            A string that represents an edge key.
	 * @return An edge key from the given string.
	 */
	private EdgeKey<Key> getEdgeKeyFromString(String edgeKeyString) {
		String[] edgeKeyStringParts = edgeKeyString.split("\\-");
		Key fromVertexKey = getVertexKeyFromString(edgeKeyStringParts[0]);
		Key toVertexKey = getVertexKeyFromString(edgeKeyStringParts[1]);
		return new EdgeKey<Key>(fromVertexKey, toVertexKey);
	}
	
}
