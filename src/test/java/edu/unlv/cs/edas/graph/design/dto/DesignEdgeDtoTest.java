package edu.unlv.cs.edas.graph.design.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DesignEdgeDtoTest {

	private DesignEdgeDto dto;
	private DesignEdgeDto same;
	private DesignEdgeDto dto2;
	
	@Before
	public void setUp() {
		dto = new DesignEdgeDto();
		dto.setWeight(1);
		
		same = new DesignEdgeDto();
		same.setWeight(1);
		
		dto2 = new DesignEdgeDto();
		dto2.setWeight(2);
	}
	
	@Test
	public void testE() {
		assertEquals(1, dto.getWeight().intValue());
	}
	
	@Test
	public void testEquals() {
		assertTrue(dto.equals(dto));
		assertFalse(dto.equals(null));
		assertFalse(dto.equals(this));
		assertTrue(dto.equals(same));
		assertFalse(dto.equals(dto2));
	}
	
	@Test
	public void testToString() {
		assertEquals("DesignEdgeDto[weight=1]", dto.toString());
	}
	
}
