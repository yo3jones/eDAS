package edu.unlv.cs.edas.graph.design.adapter;

import edu.unlv.cs.edas.graph.adapter.GraphAdapter;
import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.design.dto.DesignEdgeDto;
import edu.unlv.cs.edas.graph.design.dto.DesignGraphDto;
import edu.unlv.cs.edas.graph.design.dto.DesignVertexDto;

/**
 * A helper {@link GraphAdapter} interface to prevent clients from having to
 * specify generic types.
 * 
 * @author Chris Jones
 * 
 */
public interface DesignGraphAdapter extends GraphAdapter<DesignVertex, DesignEdge, DesignGraph, 
	Integer, DesignVertexDto, DesignEdgeDto, DesignGraphDto> {

}
