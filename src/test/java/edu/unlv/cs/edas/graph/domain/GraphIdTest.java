package edu.unlv.cs.edas.graph.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GraphIdTest {

	private GraphId id;
	private GraphId sameId;
	private GraphId otherId;
	
	@Before
	public void setUp() {
		id = new GraphId(1L);
		sameId = new GraphId(1L);
		otherId = new GraphId(2L);
	}
	
	@Test
	public void testConstruct() {
		try {
			new GraphId(null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
	@Test
	public void testGetId() {
		assertEquals(1L, id.getId().longValue());
	}
	
	@Test
	public void testToString() {
		assertEquals("GraphId[id=1]", id.toString());
	}
	
	@Test
	public void testEquals() {
		assertFalse(id.equals(null));
		assertTrue(id.equals(id));
		assertFalse(id.equals(this));
		assertTrue(id.equals(sameId));
		assertFalse(id.equals(otherId));
	}
	
	@Test
	public void testHashCode() {
		assertEquals(id.hashCode(), sameId.hashCode());
		assertNotSame(id.hashCode(), otherId.hashCode());
	}
	
}
