package edu.unlv.cs.graph.dom.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.unlv.cs.graph.Graph;

public class AbstractGraphDomBuilderContextTest {

	private static class TestAbstractGraphDomBuilderContext extends 
			AbstractGraphDomBuilderContext<String, String, String> {

		public TestAbstractGraphDomBuilderContext(Document document, 
				Graph<String, String, String> graph) {
			super(document, graph);
		}
		
	}
	
	private TestAbstractGraphDomBuilderContext context;
	
	private Document document;
	private Graph<String, String, String> graph;
	
	@SuppressWarnings("unchecked")
	private Graph<String, String, String> mockGraph() {
		return mock(Graph.class);
	}
	
	@Before
	public void setUp() throws Exception {
		document = mock(Document.class);
		graph = mockGraph();
		
		context = new TestAbstractGraphDomBuilderContext(document, graph);
	}
	
	@Test
	public void testGetDocument() throws Exception {
		assertEquals(document, context.getDocument());
	}
	
	@Test
	public void testGetGraph() throws Exception {
		assertEquals(graph, context.getGraph());
	}
	
}
