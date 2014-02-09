package edu.unlv.cs.edas.graph.design.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.dom.impl.AbstractGraphDomBuilderContext;

public class DesignDomGraphBuilderContext extends AbstractGraphDomBuilderContext<Key, DesignVertex, 
		DesignEdge> {

	public DesignDomGraphBuilderContext(Document document, Element rootElement, 
			Graph<Key, DesignVertex, DesignEdge> graph) {
		super(document, rootElement, graph);
	}
	
}
