package edu.unlv.cs.edas.graph.adapter;

import edu.unlv.cs.edas.graph.domain.Edge;
import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.Vertex;
import edu.unlv.cs.edas.graph.dto.EdgeDto;
import edu.unlv.cs.edas.graph.dto.GraphDto;
import edu.unlv.cs.edas.graph.dto.VertexDto;

/**
 * Represents an adapter for converting Graphs and Graph DTOs.
 * 
 * @author Chris Jones
 * 
 * @param <V>
 *            The vertices type.
 * @param <E>
 *            The edges type.
 * @param <G>
 *            The graph type.
 * @param <K>
 *            The key type for the graph DTO.
 * @param <VD>
 *            The vertex DTOs type.
 * @param <ED>
 *            The edge DTOs type.
 * @param <D>
 *            The graph DTO type.
 */
public interface GraphAdapter<V extends Vertex, E extends Edge, G extends Graph<V, E>, 
		K, VD extends VertexDto, ED extends EdgeDto, D extends GraphDto<K, VD, ED>> {

	/**
	 * Returns a new graph from the given graph DTO.
	 * 
	 * @param graphDto
	 *            The graph DTO.
	 * @return A new graph from the given graph DTO.
	 */
	G createGraph(D graphDto);
	
	/**
	 * Returns a new graph DTO from the given graph.
	 * 
	 * @param graph
	 *            The graph.
	 * @return A new graph DTO from the given graph.
	 */
	D createDto(G graph);
	
	/**
	 * Updates the given graph with the values on the given graph DTO.
	 * 
	 * @param graph
	 *            The graph.
	 * @param graphDto
	 *            The graph DTO.
	 */
	void updateGraphFromDto(G graph, D graphDto);
	
	/**
	 * Returns a new vertex from the given vertex DTO.
	 * 
	 * @param vertexDto
	 *            The vertex DTO.
	 * @return A new vertex from the given vertex DTO.
	 */
	V createVertex(VD vertexDto);
	
	/**
	 * Returns a new vertex DTO from the given vertex.
	 * 
	 * @param vertex
	 *            The vertex.
	 * @return A new vertex DTO from the given vertex.
	 */
	VD createDtoVertex(V vertex);
	
	/**
	 * Returns a new edge from the given edge DTO.
	 * 
	 * @param edgeDto
	 *            The edge DTO.
	 * @return A new edge from the given edge DTO.
	 */
	E createEdge(ED edgeDto);
	
	/**
	 * Returns a new edge DTO from the given edge.
	 * 
	 * @param edge
	 *            The edge.
	 * @return A new edge DTO from the given edge.
	 */
	ED createDtoEdge(E edge);
	
}
