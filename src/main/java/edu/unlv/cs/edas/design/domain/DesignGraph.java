package edu.unlv.cs.edas.design.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import edu.unlv.cs.graph.Graph;

@JsonDeserialize(as=DesignHashGraph.class)
public interface DesignGraph extends Graph<Integer, DesignVertex, DesignEdge> {

	
	
}
