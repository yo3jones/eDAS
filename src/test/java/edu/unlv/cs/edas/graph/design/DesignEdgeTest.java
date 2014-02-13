package edu.unlv.cs.edas.graph.design;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DesignEdgeTest {

	private DesignEdge edge;
	private DesignEdge same;
	private DesignEdge edge2;
	
	@Before
	public void setUp() {
		edge = new DesignEdge();
		edge.setWeight(1);
		
		same = new DesignEdge();
		same.setWeight(1);
		
		edge2 = new DesignEdge();
		edge2.setWeight(2);
	}
	
	@Test
	public void testGet() {
		assertEquals(1, edge.getWeight().intValue());
	}
	
	@Test
	public void testEquals() {
		assertFalse(edge.equals(null));
		assertTrue(edge.equals(edge));
		assertFalse(edge.equals(this));
		assertTrue(edge.equals(same));
		assertFalse(edge.equals(edge2));
	}
	
	@Test
	public void testToString() {
		assertEquals("DesignEdge[weight=1]", edge.toString());
	}
	
}
