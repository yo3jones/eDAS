package edu.unlv.cs.edas.graph.design;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DesignEdgeTest {

	private DesignEdge edge;
	private DesignEdge same;
	
	@Before
	public void setUp() {
		edge = new DesignEdge();
		same = new DesignEdge();
	}
	
	@Test
	public void testEquals() {
		assertFalse(edge.equals(null));
		assertTrue(edge.equals(edge));
		assertFalse(edge.equals(this));
		assertTrue(edge.equals(same));
	}
	
	@Test
	public void testToString() {
		assertEquals("DesignEdge[]", edge.toString());
	}
	
}
