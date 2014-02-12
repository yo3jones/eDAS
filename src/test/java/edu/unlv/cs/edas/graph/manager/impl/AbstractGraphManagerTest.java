package edu.unlv.cs.edas.graph.manager.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import edu.unlv.cs.edas.graph.domain.Edge;
import edu.unlv.cs.edas.graph.domain.Graph;
import edu.unlv.cs.edas.graph.domain.GraphId;
import edu.unlv.cs.edas.graph.domain.Vertex;

public class AbstractGraphManagerTest {

	private static class TestGraphManager extends AbstractGraphManager<GraphId, Vertex, Edge, Graph<Vertex, Edge>> {

		@Override
		protected GraphId createNextId() {
			return new GraphId(1L);
		}
		
	}
	
	private TestGraphManager manager;
	
	@Before
	public void setUp() {
		manager = new TestGraphManager();
	}
	
	@SuppressWarnings("unchecked")
	private Graph<Vertex, Edge> mockGraph() {
		return mock(Graph.class);
	}
	
	@Test
	public void test() {
		Graph<Vertex, Edge> graph = mockGraph();
		
		GraphId graphId = manager.addGraph(graph);
		Graph<Vertex, Edge>actual = manager.getGraph(graphId);
		
		assertEquals(graph, actual);
	}
	
}
