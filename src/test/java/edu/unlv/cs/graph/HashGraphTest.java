package edu.unlv.cs.graph;

import static junit.framework.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class HashGraphTest {
	
	public static HashGraph<String, String, String> toGraph(String[][][] values) {
		HashGraph<String, String, String> graph = new HashGraph<String, String, String>();
		
		String[][] vertices = values[0];
		for (String[] vertex : vertices) {
			String key = vertex[0];
			String value = vertex[1];
			graph.putVertex(key, value);
		}
		
		String[][] edges = values[1];
		for (String[] edge : edges) {
			EdgeKey<String> key = new EdgeKey<String>(edge[0], edge[1]);
			String value = edge[2];
			graph.putEdge(key, value);
		}
		
		return graph;
	}
	
	private HashGraph<String, String, String> graph;
	
	@Before
	public void setUp() throws Exception {
		graph = new HashGraph<String, String, String>();
	}
	
	@Test
	public void testPutVertex() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		
		assertEquals(2, graph.getVertexSize());
		assertEquals("one value", graph.getVertex("one"));
		assertEquals("two value", graph.getVertex("two"));
		assertNull(graph.getVertex("three"));
		
		graph.putVertex("one", "other one value");
		assertEquals(2, graph.getVertexSize());
		assertEquals("other one value", graph.getVertex("one"));
		
		try {
			graph.putVertex(null, "value");
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
	@Test
	public void testPutEdge() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		graph.putVertex("three", "three value");
		
		graph.putEdge(new EdgeKey<String>("one", "two"), "one - two");
		graph.putEdge(new EdgeKey<String>("two", "three"), "two - three");
		
		assertEquals(2, graph.getEdgeSize());
		assertEquals("one - two", graph.getEdge(new EdgeKey<String>("one", "two")));
		assertEquals("two - three", graph.getEdge(new EdgeKey<String>("two", "three")));
		assertNull(graph.getEdge(new EdgeKey<String>("two", "one")));
		
		graph.putEdge(new EdgeKey<String>("one", "two"), "other - one - two");
		assertEquals(2, graph.getEdgeSize());
		assertEquals("other - one - two", graph.getEdge(new EdgeKey<String>("one", "two")));
		
		try {
			graph.putEdge(null, "");
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
		
		try {
			graph.putEdge(new EdgeKey<String>("four", "one"), "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add edge because the from vertex does not exist. "
					+ "[fromVertex=four]", e.getMessage());
		}
		
		try {
			graph.putEdge(new EdgeKey<String>("one", "four"), "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add edge because the to vertex does not exist. [toVertex=four]", 
					e.getMessage());
		}
	}
	
	@Test
	public void testRemoveEdge() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		
		graph.putEdge(new EdgeKey<String>("one", "two"), "one - two");
		graph.putEdge(new EdgeKey<String>("two", "one"), "two - one");
		
		assertEquals(2, graph.getEdgeSize());
		
		assertEquals("one - two", graph.removeEdge(new EdgeKey<String>("one", "two")));
		
		assertEquals(1, graph.getEdgeSize());
		assertEquals("two - one", graph.getEdge(new EdgeKey<String>("two", "one")));
		assertNull(graph.getEdge(new EdgeKey<String>("one", "two")));
		
		assertNull(graph.removeEdge(new EdgeKey<String>("three", "one")));
		assertNull(graph.removeEdge(new EdgeKey<String>("one", "three")));
		
		try {
			graph.removeEdge(null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
	@Test
	public void testRemoveVertex() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		graph.putVertex("three", "three value");
		
		graph.putEdge(new EdgeKey<String>("one", "two"), "one - two");
		graph.putEdge(new EdgeKey<String>("two", "one"), "two - one");
		graph.putEdge(new EdgeKey<String>("two", "three"), "two - three");
		
		assertEquals(3, graph.getVertexSize());
		assertEquals(3, graph.getEdgeSize());
		
		assertEquals("two value", graph.removeVertex("two"));
		assertEquals(2, graph.getVertexSize());
		assertEquals(0, graph.getEdgeSize());
		assertNull(graph.getVertex("two"));
		
		assertNull(graph.removeVertex("four"));
		
		try {
			graph.removeVertex(null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
	@Test
	public void testClear() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		graph.putVertex("three", "three value");
		
		graph.putEdge(new EdgeKey<String>("one", "two"), "one - two");
		graph.putEdge(new EdgeKey<String>("two", "one"), "two - one");
		graph.putEdge(new EdgeKey<String>("two", "three"), "two - three");
		
		graph.clear();
		
		assertEquals(0, graph.getVertexSize());
		assertEquals(0, graph.getEdgeSize());
	}
	
	@Test
	public void testGetAdjacentVertices() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		graph.putVertex("three", "three value");
		
		graph.putEdge(new EdgeKey<String>("one", "two"), "one - two");
		graph.putEdge(new EdgeKey<String>("two", "one"), "two - one");
		graph.putEdge(new EdgeKey<String>("two", "three"), "two - three");
		
		Set<String> adjacentVertices = graph.getAdjacentVertices("two");
		assertNotNull(adjacentVertices);
		assertEquals(2, adjacentVertices.size());
		
		Set<String> expected = new HashSet<String>(Arrays.asList("one", "three"));
		Set<String> actual = new HashSet<String>(adjacentVertices);
		assertEquals(expected, actual);
		
		try {
			graph.getAdjacentVertices(null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
		
		try {
			graph.getAdjacentVertices("four");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot get the adjacent vertices for a vertex that is not in this graph. "
					+ "[key=four]", e.getMessage());
		}
	}
	
	@Test
	public void testGetVertexKeySet() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		graph.putVertex("three", "three value");
		
		Set<String> expected = new HashSet<String>(Arrays.asList("one", "two", "three"));
		Set<String> actual = new HashSet<String>(graph.getVertexSet());
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetEdgeSet() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		graph.putVertex("three", "three value");
		
		graph.putEdge(new EdgeKey<String>("one", "two"), "one - two");
		graph.putEdge(new EdgeKey<String>("two", "one"), "two - one");
		graph.putEdge(new EdgeKey<String>("two", "three"), "two - three");
		
		Set<EdgeKey<String>> expected = new HashSet<EdgeKey<String>>(Arrays.asList(
				new EdgeKey<String>("one", "two"),
				new EdgeKey<String>("two", "one"),
				new EdgeKey<String>("two", "three")));
		
		Set<EdgeKey<String>> actual = new HashSet<EdgeKey<String>>(graph.getEdgeSet());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testContainsVertex() throws Exception {
		graph.putVertex("one", "one value");
		
		assertTrue(graph.containsVertex("one"));
		assertFalse(graph.containsVertex("two"));
		
		try {
			graph.containsVertex(null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
	@Test
	public void testContainsEdge() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		
		graph.putEdge(new EdgeKey<String>("one", "two"), "one - two");
		
		assertTrue(graph.containsEdge(new EdgeKey<String>("one", "two")));
		assertFalse(graph.containsEdge(new EdgeKey<String>("two", "one")));
		assertFalse(graph.containsEdge(new EdgeKey<String>("one", "three")));
		assertFalse(graph.containsEdge(new EdgeKey<String>("three", "one")));
		
		try {
			graph.containsEdge(null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
}
