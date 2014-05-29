package edu.unlv.cs.edas.execute.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.unlv.cs.edas.execute.domain.ExecutionEdge;
import edu.unlv.cs.edas.execute.domain.ExecutionVertex;
import edu.unlv.cs.edas.graph.dom.AbstractGraphDomBuilder;
import edu.unlv.cs.graph.Graph;

public class ExecutionDomGraphBuilder extends AbstractGraphDomBuilder<ExecutionVertex, 
		ExecutionEdge, ExecutionDomGraphBuilderContext> {

	@Override
	public ExecutionDomGraphBuilderContext createContext(Document document, Element rootElement,
			Graph<Integer, ExecutionVertex, ExecutionEdge> graph) {
		rootElement.setAttribute("version", "1.1");
		rootElement.setAttribute("width", "100%");
		rootElement.setAttribute("height", "100%");
		return new ExecutionDomGraphBuilderContext(document, rootElement, graph);
	}
	
}
