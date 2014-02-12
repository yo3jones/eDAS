package edu.unlv.cs.edas.graph.manager.impl;

import java.util.HashMap;
import java.util.Map;

import edu.unlv.cs.edas.graph.domain.Edge;
import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.GraphId;
import edu.unlv.cs.edas.graph.domain.Vertex;
import edu.unlv.cs.edas.graph.manager.GraphManager;

/**
 * An implementation of {@link GraphManager} for storing graphs.
 * TODO this class will evolve once there is a persistence strategy
 * 
 * @author Chris Jones
 *
 * @param <I>
 * @param <V>
 * @param <E>
 * @param <G>
 */
public abstract class AbstractGraphManager<I extends GraphId, V extends Vertex, E extends Edge, 
		G extends Graph<V, E>> implements GraphManager<I, V, E, G> {

	private Map<I, G> graphs = new HashMap<I, G>();
	
	protected abstract I createNextId();
	
	@Override
	public G getGraph(I graphId) {
		return graphs.get(graphId);
	}
	
	@Override
	public I addGraph(G graph) {
		I id = createNextId();
		graphs.put(id, graph);
		return id;
	}
	
}
