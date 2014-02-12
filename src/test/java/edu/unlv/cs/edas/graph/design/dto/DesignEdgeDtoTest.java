package edu.unlv.cs.edas.graph.design.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DesignEdgeDtoTest {

	private DesignEdgeDto dto;
	private DesignEdgeDto same;
	
	@Before
	public void setUp() {
		dto = new DesignEdgeDto();
		same = new DesignEdgeDto();
	}
	
	@Test
	public void testE() {
		assertEquals("e", dto.getE());
		dto.setE("f");
		assertEquals("f", dto.getE());
	}
	
	@Test
	public void testEquals() {
		assertTrue(dto.equals(dto));
		assertFalse(dto.equals(null));
		assertFalse(dto.equals(this));
		assertTrue(dto.equals(same));
	}
	
	@Test
	public void testToString() {
		assertEquals("DesignEdgeDto[]", dto.toString());
	}
	
}
