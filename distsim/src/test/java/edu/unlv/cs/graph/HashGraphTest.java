package edu.unlv.cs.graph;

import static junit.framework.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class HashGraphTest {

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
		
		graph.putEdge("one", "two", "one - two");
		graph.putEdge("two", "three", "two - three");
		
		assertEquals(2, graph.getEdgeSize());
		assertEquals("one - two", graph.getEdge("one", "two"));
		assertEquals("two - three", graph.getEdge("two", "three"));
		assertNull(graph.getEdge("two", "one"));
		
		graph.putEdge("one", "two", "other - one - two");
		assertEquals(2, graph.getEdgeSize());
		assertEquals("other - one - two", graph.getEdge("one", "two"));
		
		try {
			graph.putEdge(null, "two", "");
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
		
		try {
			graph.putEdge("one", null, "");
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
		
		try {
			graph.putEdge("four", "one", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add edge because the from vertex does not exist. "
					+ "[fromVertex=four]", e.getMessage());
		}
		
		try {
			graph.putEdge("one", "four", "");
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
		
		graph.putEdge("one", "two", "one - two");
		graph.putEdge("two", "one", "two - one");
		
		assertEquals(2, graph.getEdgeSize());
		
		assertEquals("one - two", graph.removeEdge("one", "two"));
		
		assertEquals(1, graph.getEdgeSize());
		assertEquals("two - one", graph.getEdge("two", "one"));
		assertNull(graph.getEdge("one", "two"));
		
		assertNull(graph.removeEdge("three", "one"));
		assertNull(graph.removeEdge("one", "three"));
		
		try {
			graph.removeEdge(null, "one");
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
		
		try {
			graph.removeEdge("one", null);
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
		
		graph.putEdge("one", "two", "one - two");
		graph.putEdge("two", "one", "two - one");
		graph.putEdge("two", "three", "two - three");
		
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
		
		graph.putEdge("one", "two", "one - two");
		graph.putEdge("two", "one", "two - one");
		graph.putEdge("two", "three", "two - three");
		
		graph.clear();
		
		assertEquals(0, graph.getVertexSize());
		assertEquals(0, graph.getEdgeSize());
	}
	
	@Test
	public void testGetAdjacentVertices() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		graph.putVertex("three", "three value");
		
		graph.putEdge("one", "two", "one - two");
		graph.putEdge("two", "one", "two - one");
		graph.putEdge("two", "three", "two - three");
		
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
	public void testGetKeySet() throws Exception {
		graph.putVertex("one", "one value");
		graph.putVertex("two", "two value");
		graph.putVertex("three", "three value");
		
		Set<String> expected = new HashSet<String>(Arrays.asList("one", "two", "three"));
		Set<String> actual = new HashSet<String>(graph.getKeySet());
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
		
		graph.putEdge("one", "two", "one - two");
		
		assertTrue(graph.containsEdge("one", "two"));
		assertFalse(graph.containsEdge("two", "one"));
		assertFalse(graph.containsEdge("one", "three"));
		assertFalse(graph.containsEdge("three", "one"));
		
		try {
			graph.containsEdge(null, "one");
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
		
		try {
			graph.containsEdge("one", null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
}
