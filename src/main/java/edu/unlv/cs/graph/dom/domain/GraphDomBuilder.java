package edu.unlv.cs.graph.dom.domain;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.unlv.cs.graph.EdgeKey;
import edu.unlv.cs.graph.Graph;

/**
 * Represents an object that is used in generating DOM documents from a graph.
 * <p>
 * This is used in implementations of {@link GraphDomAdapter}
 * 
 * @author Chris Jones
 * 
 * @param <K>
 *            The key of the vertices of the graph
 * @param <V>
 *            The vertices of the graph
 * @param <E>
 *            The edges of the graph
 * @param <C>
 *            The context of the generation
 * @see Graph
 * @see GraphDomAdapter
 */
public interface GraphDomBuilder<K, V, E, C> {

	/**
	 * Returns a new document object that will be used to append all vertices
	 * and edges of the graph.
	 * 
	 * @return A new document object that will be used to append all vertices
	 *         and edges of the graph.
	 */
	Document createDocument();
	
	/**
	 * Returns a new context object that will be passed in for every call to
	 * {@link #createVertex(Object, Object, Object)} and
	 * {@link #createEdge(Object, EdgeKey, Object)}.
	 * <p>
	 * This is useful for accessing the document and graph.
	 * 
	 * @param document
	 *            The document being generated.
	 * @param graph
	 *            The graph.
	 * @return A new context object that will be passed in for every call to
	 *         {@link #createVertex(Object, Object, Object)} and
	 *         {@link #createEdge(Object, EdgeKey, Object)}.
	 */
	C createContext(Document document, Graph<K, V, E> graph);
	
	/**
	 * Returns a new node that represents the given vertex of the graph.
	 * 
	 * @param context
	 *            The context that was created by
	 *            {@link #createContext(Document, Graph)}.
	 * @param key
	 *            The key of the vertex.
	 * @param vertex
	 *            The vertex value.
	 * @return A new node that represents the given vertex of the graph.
	 */
	Node createVertex(C context, K key, V vertex);
	
	/**
	 * Returns a new node that represents the given edge of the graph.
	 * 
	 * @param context
	 *            The context that was created by
	 *            {@link #createContext(Document, Graph)}.
	 * @param key
	 *            The key of the edge.
	 * @param edge
	 *            The edge value.
	 * @return A new node that represents the given edge of the graph.
	 */
	Node createEdge(C context, EdgeKey<K> key, E edge);
	
}
