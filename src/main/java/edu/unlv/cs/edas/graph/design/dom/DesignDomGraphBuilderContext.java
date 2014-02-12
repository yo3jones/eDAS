package edu.unlv.cs.edas.graph.design.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.dom.impl.AbstractGraphDomBuilderContext;

/**
 * An {@link AbstractGraphDomBuilderContext} implementation for building DOMs
 * for design graphs.
 * 
 * @author Chris Jones
 * 
 */
public class DesignDomGraphBuilderContext extends AbstractGraphDomBuilderContext<Key, DesignVertex, 
		DesignEdge> {

	/**
	 * Constructs a design dom graph builder context.
	 * 
	 * @param document
	 *            The document this context is building on.
	 * @param rootElement
	 *            The root element of the DOM being built.
	 * @param graph
	 *            The graph used to generate the DOM.
	 */
	public DesignDomGraphBuilderContext(Document document, Element rootElement, 
			Graph<Key, DesignVertex, DesignEdge> graph) {
		super(document, rootElement, graph);
	}
	
}
