package edu.unlv.cs.edas.graph.manager;

import edu.unlv.cs.edas.graph.domain.Edge;
import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.GraphId;
import edu.unlv.cs.edas.graph.domain.Vertex;

/**
 * A manager to store graphs in the eDAS application.
 * 
 * @author Chris Jones
 * 
 * @param <I>
 *            The graph ID type.
 * @param <V>
 *            The vertex type.
 * @param <E>
 *            The edge type.
 * @param <G>
 *            The graph type.
 */
public interface GraphManager<I extends GraphId, V extends Vertex, E extends Edge, G extends Graph<V, E>> {

	/**
	 * Returns a graph stored in this manager.
	 * 
	 * @param graphId
	 *            The ID of the graph to return.
	 * @return A graph stored in this manager.
	 */
	G getGraph(I graphId);
	
	/**
	 * Adds a graph to this manager and returns the ID used to retrieve it.
	 * 
	 * @param graph
	 *            The graph being stored.
	 * @return The ID used to retrieve the stored graph.
	 */
	I addGraph(G graph);
	
}
