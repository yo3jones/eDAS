package edu.unlv.cs.edas.graph.design;

import edu.unlv.cs.edas.graph.domain.Edge;
import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.graph.EdgeKey;

public class DesignEdge extends Edge {

	DesignEdge() {
		super();
	}
	
	public DesignEdge(EdgeKey<Key> id) throws IllegalArgumentException {
		super(id);
	}

}
