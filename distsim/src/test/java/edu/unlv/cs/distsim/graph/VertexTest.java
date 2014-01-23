package edu.unlv.cs.distsim.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VertexTest {

	private Vertex expected;
	
	@Before
	public void setUp() throws Exception {
		expected = new Vertex(new Key("one"));
	}
	
	@Test
	public void testConstruct() throws Exception {
		try {
			new Vertex(null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
	@Test
	public void testGetters() throws Exception {
		assertEquals(new Key("one"), expected.getId());
	}
	
}
