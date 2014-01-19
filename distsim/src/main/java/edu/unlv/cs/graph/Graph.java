package edu.unlv.cs.graph;

import java.util.Set;

/**
 * Interface that represents a graph.
 * <p>
 * Note: this interface does not define whether the graph is directional or
 * bidirectional.
 * 
 * @author Chris Jones
 * 
 * @param <K>
 *            The vertex key type. This object should uniquely identify each
 *            vertex.
 * @param <V>
 *            The value for each vertex.
 * @param <E>
 *            The value for each edge.
 */
public interface Graph<K, V, E> {
	
	/**
	 * Puts a vertex onto this graph. If a vertex with the given key already
	 * exists on the graph it will simply update the vertex's value.
	 * 
	 * @param key
	 *            The key that uniquely identifies the vertex being added.
	 * @param vertex
	 *            The value of the vertex being added.
	 * @return The vertex value that was added to the graph.
	 * @throws IllegalArgumentException
	 *             If the value of key is null.
	 */
	V putVertex(K key, V vertex) throws IllegalArgumentException;
	
	/**
	 * Puts an edge onto this graph given the two end points of the edge.
	 * <p>
	 * Note: This interface does not define whether this is a directional or
	 * bidirectional edge.
	 * 
	 * @param fromKey
	 *            The unique identifier of the vertex that is starting point of
	 *            this edge.
	 * @param toKey
	 *            The unique identifier of the vertex that is the end point of
	 *            this edge.
	 * @param edge
	 *            The value of the edge.
	 * @return The value of the edge that was put on this graph.
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>The fromKey vertex does not exist on this graph.</li> 
	 *             <li>The toKey vertex does not exist on this graph.</li>
	 *             <li>The fromKey is null.</li>
	 *             <li>The toKey is null.</li>
	 *             </ul>
	 */
	E putEdge(K fromKey, K toKey, E edge) throws IllegalArgumentException;
	
	/**
	 * Returns the vertex value for the vertex identified by the given key.
	 * 
	 * @param key
	 *            The key that uniquely identifies the vertex.
	 * @return The vertex value for the vertex identified by the given key.
	 * @throws IllegalArgumentException
	 *             If the key value is null.
	 */
	V getVertex(K key) throws IllegalArgumentException;
	
	/**
	 * Returns the edge value for the edge identified by the given from key and
	 * to key.
	 * 
	 * @param fromKey
	 *            The key that identifies the start vertex of the edge to
	 *            return.
	 * @param toKey
	 *            The key that identifies the end vertex of the edge to return.
	 * @return The edge value for the edge identified by the given from key and
	 *         to key. Or null if no edge exists.
	 * @throws IllegalArgumentException
	 *             If either the fromKey or toKey is null.
	 */
	E getEdge(K fromKey, K toKey) throws IllegalArgumentException;
	
	/**
	 * Removes the vertex identified by the given key.
	 * <p>
	 * This method will also remove any edges that are connected the vertex
	 * being removed.
	 * 
	 * @param key
	 *            The key that identifies the vertex to be removed.
	 * @return The vertex value that was removed, or null of no vertex exists.
	 * @throws IllegalArgumentException
	 *             If the key is null.
	 */
	V removeVertex(K key) throws IllegalArgumentException;
	
	/**
	 * Removes the edge between the vertices identified by the from and to keys.
	 * 
	 * @param fromKey
	 *            The key that uniquely identifies the start vertex of the edge
	 *            to be removed.
	 * @param toKey
	 *            The key that uniquely identifies the end vertex of the edge to
	 *            be removed.
	 * @return The edge value that was removed, or null if no edge existed.
	 * @throws IllegalArgumentException
	 *             If either the fromKey or toKey values are null.
	 */
	E removeEdge(K fromKey, K toKey) throws IllegalArgumentException;
	
	/**
	 * Clears all vertices and edges from this graph.
	 */
	void clear();
	
	/**
	 * Returns a set that contains all vertices that the vertex identified by
	 * the given key can connect to.
	 * 
	 * @param key
	 *            The key that uniquely identifies the vertex.
	 * @return A set that contains all vertices that the vertex identified by
	 *         the given key can connect to. Will return non null empty set if
	 *         there are no adjacent vertices.
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>If key is null</li>
	 *             <li>If there is no vertex on the graph with the given key.</li>
	 *             </ul>
	 */
	Set<K> getAdjacentVertices(K key) throws IllegalArgumentException;
	
	/**
	 * Returns a set of all key's that identify the vertices of this graph.
	 * 
	 * @return A set of all key's that identify the vertices of this graph.
	 */
	Set<K> getKeySet();
	
	/**
	 * Returns the number of vertices on this graph.
	 * 
	 * @return The number of vertices on this graph.
	 */
	int getVertexSize();
	
	/**
	 * Returns the number of edges on this graph.
	 * 
	 * @return The number of edges on this graph.
	 */
	int getEdgeSize();
	
	/**
	 * Returns <tt>true</tt> iff this graph contains a vertex that is identified
	 * with the given key.
	 * 
	 * @param key
	 *            The key that identifies the vertex.
	 * @return <tt>true</tt> iff this graph contains a vertex that is identified
	 *         with the given key.
	 * @throws IllegalArgumentException
	 *             If key is null.
	 */
	boolean containsVertex(K key) throws IllegalArgumentException;
	
	/**
	 * Returns <tt>true</tt> iff there exists an edge between the two vertices
	 * identified by the given keys.
	 * 
	 * @param fromKey
	 *            The key that uniquely identifies the start vertex of the edge
	 *            to look for.
	 * @param toKey
	 *            The key that uniquely identifies the end vertex of the edge to
	 *            look for.
	 * @return <tt>true</tt> iff there exists an edge between the two vertices
	 *         identified by the given keys.
	 * @throws IllegalArgumentException
	 *             If either fromKey or toKey are null.
	 */
	boolean containsEdge(K fromKey, K toKey) throws IllegalArgumentException;
	
}
