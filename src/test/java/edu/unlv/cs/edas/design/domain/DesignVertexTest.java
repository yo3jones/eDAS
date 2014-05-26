package edu.unlv.cs.edas.design.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.unlv.cs.edas.design.domain.DesignVertex;
import edu.unlv.cs.edas.design.domain.Position;

public class DesignVertexTest {

	private DesignVertex vertex;
	private DesignVertex same;
	private DesignVertex vertex2;
	private DesignVertex vertex3;
	private DesignVertex vertex4;
	
	@Before
	public void setUp() {
		vertex = new DesignVertex("1", new Position(2.0, 3.0));
		
		same = new DesignVertex("1", new Position(2.0, 3.0));
		
		vertex2 = new DesignVertex("4", new Position(2.0, 3.0));
		
		vertex3 = new DesignVertex("1", new Position(4.0, 3.0));
		
		vertex4 = new DesignVertex("1", new Position(2.0, 4.0));
	}
	
	@Test
	public void test() {
		assertEquals("1", vertex.getLabel());
		assertEquals(new Position(2.0, 3.0), vertex.getPosition());
	}
	
	@Test
	public void testEquals() {
		assertFalse(vertex.equals(null));
		assertTrue(vertex.equals(vertex));
		assertFalse(vertex.equals(this));
		assertTrue(vertex.equals(same));
		assertFalse(vertex.equals(vertex2));
		assertFalse(vertex.equals(vertex3));
		assertFalse(vertex.equals(vertex4));
	}
	
	@Test
	public void testHashCode() {
		assertEquals(vertex.hashCode(), same.hashCode());
		assertNotSame(vertex.hashCode(), vertex2.hashCode());
		assertNotSame(vertex.hashCode(), vertex3.hashCode());
		assertNotSame(vertex.hashCode(), vertex4.hashCode());
	}
	
	@Test
	public void testToString() {
		assertEquals("DesignVertex[label=1,position=Position[x=2,y=3]]", vertex.toString());
	}
	
}
