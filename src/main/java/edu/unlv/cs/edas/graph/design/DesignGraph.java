package edu.unlv.cs.edas.graph.design;

import java.util.concurrent.atomic.AtomicInteger;

import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.Key;

public class DesignGraph extends Graph<DesignVertex, DesignEdge> {

	private AtomicInteger currentVertexId = new AtomicInteger(0);
	
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
