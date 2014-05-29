package edu.unlv.cs.edas.execute.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.unlv.cs.edas.execute.domain.ExecutionEdge;
import edu.unlv.cs.edas.execute.domain.ExecutionVertex;
import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.dom.impl.AbstractGraphDomBuilderContext;

public class ExecutionDomGraphBuilderContext extends AbstractGraphDomBuilderContext<Integer, 
		ExecutionVertex, ExecutionEdge> {

	public ExecutionDomGraphBuilderContext(Document document, Element rootElement, 
			Graph<Integer, ExecutionVertex, ExecutionEdge> graph) {
		super(document, rootElement, graph);
	}

}
