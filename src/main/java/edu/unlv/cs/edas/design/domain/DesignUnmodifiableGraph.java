package edu.unlv.cs.edas.design.domain;

import edu.unlv.cs.graph.UnmodifiableGraph;

public class DesignUnmodifiableGraph extends UnmodifiableGraph<Integer, DesignVertex, DesignEdge> 
		implements DesignGraph {

	private static final long serialVersionUID = 7501495294038925453L;

	public DesignUnmodifiableGraph(DesignGraph graph) {
		super(graph);
	}
	
}
