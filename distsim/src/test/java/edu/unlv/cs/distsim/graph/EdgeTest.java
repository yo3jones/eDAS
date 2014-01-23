package edu.unlv.cs.distsim.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.unlv.cs.graph.EdgeKey;

public class EdgeTest {

	private Edge expected;
	
	@Before
	public void setUp() throws Exception {
		expected = new Edge(new EdgeKey<Key>(new Key("one"), new Key("two")));
	}
	
	@Test
	public void testConstruct() throws Exception {
		try {
			new Edge(null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
	@Test
	public void testGetters() throws Exception {
		assertEquals(new EdgeKey<Key>(new Key("one"), new Key("two")), expected.getId());
	}
	
}
