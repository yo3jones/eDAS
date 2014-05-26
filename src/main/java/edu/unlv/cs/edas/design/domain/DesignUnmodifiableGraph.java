package edu.unlv.cs.edas.design.domain;

import edu.unlv.cs.graph.UnmodifiableGraph;

public class DesignUnmodifiableGraph extends UnmodifiableGraph<Integer, DesignVertex, DesignEdge> 
		implements DesignGraph {

	public DesignUnmodifiableGraph(DesignGraph graph) {
		super(graph);
	}
	
}
