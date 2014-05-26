package edu.unlv.cs.edas.design.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.unlv.cs.edas.design.domain.DesignEdge;
import edu.unlv.cs.edas.design.domain.DesignVertex;
import edu.unlv.cs.edas.graph.dom.AbstractGraphDomBuilder;
import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.dom.domain.GraphDomBuilder;

/**
 * A {@link GraphDomBuilder} implementation for creating a SVG DOM for a design graph.
 * 
 * @author Chris Jones
 */
public class DesignDomGraphBuilder extends 
		AbstractGraphDomBuilder<DesignVertex, DesignEdge, DesignDomGraphBuilderContext> {
	
	@Override
	public DesignDomGraphBuilderContext createContext(Document document, Element rootElement, 
			Graph<Integer, DesignVertex, DesignEdge> graph) {
		rootElement.setAttribute("version", "1.1");
		rootElement.setAttribute("width", "100%");
		rootElement.setAttribute("height", "100%");
		return new DesignDomGraphBuilderContext(document, rootElement, graph);
	}
	
}
