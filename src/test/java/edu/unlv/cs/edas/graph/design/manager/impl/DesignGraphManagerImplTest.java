package edu.unlv.cs.edas.graph.design.manager.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.unlv.cs.edas.graph.design.DesignGraphId;

public class DesignGraphManagerImplTest {

	private DesignGraphManagerImpl manager;
	
	@Before
	public void setUp() {
		manager = new DesignGraphManagerImpl();
	}
	
	@Test
	public void testCreateNextId() {
		assertEquals(new DesignGraphId(1001L), manager.createNextId());
		assertEquals(new DesignGraphId(1002L), manager.createNextId());
		assertEquals(new DesignGraphId(1003L), manager.createNextId());
		assertEquals(new DesignGraphId(1004L), manager.createNextId());
	}
	
}
