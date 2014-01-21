package edu.unlv.cs.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeKeyTest {

	private EdgeKey<String> expected;
	private EdgeKey<String> expectedCopy;
	private EdgeKey<String> expectedOne;
	private EdgeKey<String> expectedTwo;
	
	@Before
	public void setUp() throws Exception {
		expected = new EdgeKey<String>("one", "two");
		expectedCopy = new EdgeKey<String>("one", "two");
		expectedOne = new EdgeKey<String>("one", "one");
		expectedTwo = new EdgeKey<String>("two", "two");
	}
	
	@Test
	public void testConstruct() throws Exception {
		try {
			new EdgeKey<>(null, "two");
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
		
		try {
			new EdgeKey<>("one", null);
			fail();
		} catch (IllegalArgumentException e) {
			// this is good
		}
	}
	
	@Test
	public void testGetters() throws Exception {
		assertEquals("one", expected.getFromKey());
		assertEquals("two", expected.getToKey());
	}

	@Test
	public void testEquals() throws Exception {
		assertFalse(expected.equals(null));
		assertTrue(expected.equals(expected));
		assertFalse(expected.equals(this));
		assertTrue(expected.equals(expectedCopy));
		assertFalse(expected.equals(expectedOne));
		assertFalse(expected.equals(expectedTwo));
	}
	
	@Test
	public void testHashCode() throws Exception {
		assertEquals(expected.hashCode(), expectedCopy.hashCode());
		assertNotSame(expected.hashCode(), expectedOne);
		assertNotSame(expected.hashCode(), expectedTwo);
	}
	
	@Test
	public void testToString() throws Exception {
		assertEquals("EdgeKey[fromKey=one,toKey=two]", expected.toString());
	}
	
}
