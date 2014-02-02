package edu.unlv.cs.edas.graph.design.adapter;

import edu.unlv.cs.edas.graph.adapter.GraphAdapter;
import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.DesignGraphDto;
import edu.unlv.cs.edas.graph.design.DesignVertex;

public interface DesignGraphAdapter extends GraphAdapter<DesignVertex, DesignEdge, DesignGraph, 
	DesignGraphDto> {

}
