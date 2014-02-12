package edu.unlv.cs.edas.graph.design;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.unlv.cs.edas.graph.domain.Key;

public class DesignGraphTest {

	private DesignGraph graph;
	
	@Before
	public void setUp() {
		graph = new DesignGraph();
	}
	
	@Test
	public void testGetNextVertexId() {
		assertEquals(1, graph.getNextVertexId().intValue());
		assertEquals(2, graph.getNextVertexId().intValue());
		graph.putVertex(new Key(99), new DesignVertex());
		assertEquals(100, graph.getNextVertexId().intValue());
		graph.clear();
		assertEquals(1, graph.getNextVertexId().intValue());
	}
	
}
