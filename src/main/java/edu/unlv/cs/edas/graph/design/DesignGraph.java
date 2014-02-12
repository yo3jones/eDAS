package edu.unlv.cs.edas.graph.design;

import java.util.concurrent.atomic.AtomicInteger;

import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.Key;

/**
 * An implementation of {@link Graph} for design graphs.
 * 
 * @author Chris Jones
 *
 */
public class DesignGraph extends Graph<DesignVertex, DesignEdge> {

	/**
	 * Stores the current vertex ID so the next unique ID can be generated.
	 */
	private AtomicInteger currentVertexId = new AtomicInteger(0);
	
	/**
	 * Returns the next ID of a new vertex for this graph.
	 * 
	 * @return The next ID of a new vertex for this graph.
	 */
	public Integer getNextVertexId() {
		synchronized(currentVertexId) {
			return currentVertexId.incrementAndGet();
		}
	}
	
	@Override
	public DesignVertex putVertex(Key key, DesignVertex vertex) throws IllegalArgumentException {
		DesignVertex returnValue = super.putVertex(key, vertex);
		synchronized(currentVertexId) {
			currentVertexId.set(Math.max(currentVertexId.get(), key.getId()));
		}
		return returnValue;
	}
	
	@Override
	public void clear() {
		super.clear();
		synchronized(currentVertexId) {
			currentVertexId.set(0);
		}
	}
	
}
