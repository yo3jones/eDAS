package edu.unlv.cs.edas.design.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.unlv.cs.edas.design.controller.DesignGraphDetailsApiController;
import edu.unlv.cs.edas.design.dom.DesignGraphDomAdapter;
import edu.unlv.cs.edas.design.domain.DesignEdge;
import edu.unlv.cs.edas.design.domain.DesignGraph;
import edu.unlv.cs.edas.design.domain.DesignGraphDetails;
import edu.unlv.cs.edas.design.domain.DesignHashGraph;
import edu.unlv.cs.edas.design.domain.DesignVertex;
import edu.unlv.cs.edas.design.domain.Position;
import edu.unlv.cs.edas.design.manager.DesignGraphDetailsManager;
import edu.unlv.cs.graph.EdgeKey;

public class DesignGraphApiControllerTest {

	private DesignGraphDetailsApiController controller;
	
	private DesignGraphDetailsManager manager;
	private DesignGraphDomAdapter domAdapter;
	
	@Before
	public void setUp() {
		manager = mock(DesignGraphDetailsManager.class);
		domAdapter = mock(DesignGraphDomAdapter.class);
		
		controller = new DesignGraphDetailsApiController();
		controller.manager = manager;
		controller.domAdapter = domAdapter;
	}
	
	@Test
	public void testPutGraph() {
		String id = "some id";
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		DesignGraph graph = mock(DesignGraph.class);
		
		
		when(manager.get("some id")).thenReturn(graphDetails);
		
		controller.putDesignGraph(id, graphDetails);
		
		assertEquals(graph, graphDetails.getGraph());
		verify(manager).save(graphDetails);
	}
	
	@Test
	public void testGetGraphJson() {
		String id = "some id";
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		DesignGraph graph = new DesignHashGraph();
		graph.putVertex(1, new DesignVertex());
		
		graphDetails.setGraph(graph);
		when(manager.get(id)).thenReturn(graphDetails);
		
		DesignGraphDetails actual = controller.getDesignGraphJson(id);
		
		assertEquals(actual.getClass(), DesignHashGraph.class);
		assertEquals(graph, actual);
	}
	
	@Test
	public void testGetGraphSvg() throws Exception {
		String id = "some id";
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		DesignGraph graph = new DesignHashGraph();
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		
		graphDetails.setGraph(graph);
		when(manager.get(id)).thenReturn(graphDetails);
		when(domAdapter.getDomFromGraph(graph)).thenReturn(document);
		
		String actual = controller.getGraphSvg(id);
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", actual);
	}
	
	@Test
	public void testPostVertex() {
		String id = "some id";
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		DesignGraph graph = new DesignHashGraph();
		DesignVertex vertex = new DesignVertex();
		
		graphDetails.setGraph(graph);
		vertex.setLabel("");
		vertex.setPosition(new Position(50.0, 50.0));
		
		when(manager.get(id)).thenReturn(graphDetails);
		
		DesignVertex actual = controller.postVertex(id);
		
		assertEquals(vertex, actual);
		verify(manager).save(graphDetails);
		assertTrue(graph.containsVertex(1));
		assertEquals(vertex, graph.getVertex(1));
		verify(manager).save(graphDetails);
	}
	
	@Test
	public void testPutVertex() {
		String id = "some id";
		Integer vertexId = 1;
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		DesignGraph graph = new DesignHashGraph();
		DesignVertex vertex = new DesignVertex();
		
		graphDetails.setGraph(graph);
		when(manager.get(id)).thenReturn(graphDetails);
		
		controller.putVertex(id, vertexId, vertex);
		
		assertTrue(graph.containsVertex(1));
		assertEquals(vertex, graph.getVertex(1));
		verify(manager).save(graphDetails);
	}
	
	@Test
	public void testDeleteVertex() {
		String id = "some id";
		Integer vertexId = 1;
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		DesignGraph graph = new DesignHashGraph();
		
		graph.putVertex(vertexId, new DesignVertex());
		graphDetails.setGraph(graph);
		when(manager.get(id)).thenReturn(graphDetails);
		
		controller.deleteVertex(id, vertexId);
		
		assertFalse(graph.containsVertex(vertexId));
		verify(manager).save(graphDetails);
	}
	
	@Test
	public void testPostEdge() {
		String id = "some id";
		Integer fromVertexId = 1;
		Integer toVertexId = 2;
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		DesignGraph graph = new DesignHashGraph();
		
		graph.putVertex(fromVertexId, new DesignVertex());
		graph.putVertex(toVertexId, new DesignVertex());
		
		graphDetails.setGraph(graph);
		when(manager.get(id)).thenReturn(graphDetails);
		
		DesignEdge actual = controller.postEdge(id, fromVertexId, toVertexId);
		
		EdgeKey<Integer> edgeKey = new EdgeKey<Integer>(fromVertexId, toVertexId);
		assertTrue(graph.containsEdge(edgeKey));
		assertEquals(new DesignEdge(), graph.getEdge(edgeKey));
		assertEquals(new DesignEdge(), actual);
		verify(manager).save(graphDetails);
	}
	
	@Test
	public void testPutEdge() {
		String id = "some id";
		Integer fromVertexId = 1;
		Integer toVertexId = 2;
		EdgeKey<Integer> edgeKey = new EdgeKey<Integer>(fromVertexId, toVertexId);
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		DesignGraph graph = new DesignHashGraph();
		DesignEdge edge = new DesignEdge();
		
		graphDetails.setGraph(graph);
		graph.putVertex(fromVertexId, new DesignVertex());
		graph.putVertex(toVertexId, new DesignVertex());
		when(manager.get(id)).thenReturn(graphDetails);
		
		controller.putEdge(id, fromVertexId, toVertexId, edge);
		
		assertTrue(graph.containsEdge(edgeKey));
		assertEquals(edge, graph.getEdge(edgeKey));
		verify(manager).save(graphDetails);
	}
	
	@Test
	public void testDeleteEdge() {
		String id ="some id";
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		DesignGraph graph = new DesignHashGraph();
		Integer fromVertexId = 1;
		Integer toVertexId = 2;
		EdgeKey<Integer> edgeKey = new EdgeKey<Integer>(fromVertexId, toVertexId);
		
		graph.putVertex(fromVertexId, new DesignVertex());
		graph.putVertex(toVertexId, new DesignVertex());
		graph.putEdge(edgeKey, new DesignEdge());
		graphDetails.setGraph(graph);
		when(manager.get(id)).thenReturn(graphDetails);	
		
		controller.deleteEdge(id, fromVertexId, toVertexId);
		
		assertFalse(graph.containsEdge(edgeKey));
		verify(manager).save(graphDetails);
	}
	
}
