package edu.unlv.cs.graph.dom.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.unlv.cs.graph.EdgeKey;
import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.dom.domain.GraphDomAdapter;
import edu.unlv.cs.graph.dom.domain.GraphDomBuilder;

/**
 * An implementation of {@link GraphDomAdapter}. This implementation simply
 * calls into the given {@link GraphDomBuilder} and appends all the nodes the
 * generated document.
 * 
 * @author Chris Jones
 * 
 * @param <K>
 *            The key of the vertices.
 * @param <V>
 *            The vertex values.
 * @param <E>
 *            The edge values.
 */
public class GraphDomAdapterImpl<K, V, E> implements GraphDomAdapter<K, V, E> {

	/**
	 * The DOM builder used to generate the document, context and nodes.
	 */
	private GraphDomBuilder<K, V, E, ?> builder;
	
	@Override
	public Document getDomFromGraph(Graph<K, V, E> graph) {
		return getDomFromGraphInternal(graph);
	}

	private <C> Document getDomFromGraphInternal(Graph<K, V, E> graph) {
		GraphDomBuilder<K, V, E, C> builder = getBuilder();
		
		// create the document that all the nodes will be appended to
		Document document = builder.createDocument();
		
		// create the context that will be used to build the DOM
		C context = builder.createContext(document, graph);
		
		// add vertices
		for (K key : graph.getVertexSet()) {
			V vertex = graph.getVertex(key);
			Node vertexNode = builder.createVertex(context, key, vertex);
			document.appendChild(vertexNode);
		}
		
		// add edges
		for (EdgeKey<K> key : graph.getEdgeSet()) {
			E edge = graph.getEdge(key);
			Node edgeNode = builder.createEdge(context, key, edge);
			document.appendChild(edgeNode);
		}
		
		return document;
	}
	
	@SuppressWarnings("unchecked")
	private <C> GraphDomBuilder<K, V, E, C> getBuilder() {
		return (GraphDomBuilder<K, V, E, C>) builder;
	}
	
	/**
	 * Sets the DOM builder to be used to generate the document.
	 * 
	 * @param builder
	 *            The DOM builder to be used to generate the document.
	 */
	public void setBuilder(GraphDomBuilder<K, V, E, ?> builder) {
		this.builder = builder;
	}
	
}
