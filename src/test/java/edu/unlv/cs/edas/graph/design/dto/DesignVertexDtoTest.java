package edu.unlv.cs.edas.graph.design.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DesignVertexDtoTest {

	private DesignVertexDto dto;
	private DesignVertexDto same;
	private DesignVertexDto dto2;
	private DesignVertexDto dto3;
	private DesignVertexDto dto4;
	
	@Before
	public void setUp() {
		dto = new DesignVertexDto();
		dto.setLabel("1");
		dto.setX(2);
		dto.setY(3);
		
		same = new DesignVertexDto();
		same.setLabel("1");
		same.setX(2);
		same.setY(3);
		
		dto2 = new DesignVertexDto();
		dto2.setLabel("4");
		dto2.setX(2);
		dto2.setY(3);
		
		dto3 = new DesignVertexDto();
		dto3.setLabel("1");
		dto3.setX(4);
		dto3.setY(3);
		
		dto4 = new DesignVertexDto();
		dto4.setLabel("1");
		dto4.setX(2);
		dto4.setY(4);
	}
	
	@Test
	public void test() {
		assertEquals("1", dto.getLabel());
		assertEquals(2, dto.getX().intValue());
		assertEquals(3, dto.getY().intValue());
	}
	
	@Test
	public void testEquals() {
		assertTrue(dto.equals(dto));
		assertFalse(dto.equals(null));
		assertFalse(dto.equals(this));
		assertTrue(dto.equals(same));
		assertFalse(dto.equals(dto2));
		assertFalse(dto.equals(dto3));
		assertFalse(dto.equals(dto4));
	}
	
	@Test
	public void testToString() {
		assertEquals("DesignVertexDto[label=1,x=2,y=3]", dto.toString());
	}
	
}
