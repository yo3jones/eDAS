package edu.unlv.cs.edas.design.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.w3c.dom.Document;

import edu.unlv.cs.edas.design.controller.DesignGraphDetailsApiController;
import edu.unlv.cs.edas.design.dom.DesignGraphDomAdapter;
import edu.unlv.cs.edas.design.domain.DesignEdge;
import edu.unlv.cs.edas.design.domain.DesignGraph;
import edu.unlv.cs.edas.design.domain.DesignGraphDetails;
import edu.unlv.cs.edas.design.domain.DesignHashGraph;
import edu.unlv.cs.edas.design.domain.DesignVertex;
import edu.unlv.cs.edas.design.domain.ImmutableDesignGraphDetails;
import edu.unlv.cs.edas.design.domain.MutableDesignGraphDetails;
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
		MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails();
		ImmutableDesignGraphDetails immutableGraphDetails = new ImmutableDesignGraphDetails(
				mutableGraphDetails);
		
		when(manager.get("some id")).thenReturn(immutableGraphDetails);
		
		controller.putDesignGraph(id, mutableGraphDetails);
		
		verify(manager).save(mutableGraphDetails);
	}
	
	@Test
	public void testGetGraphJson() {
		String id = "some id";
		MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails();
		ImmutableDesignGraphDetails immutableGraphDetails = new ImmutableDesignGraphDetails(
				mutableGraphDetails);
		DesignGraph graph = new DesignHashGraph();
		graph.putVertex(1, new DesignVertex("", new Position(0.0, 0.0)));
		
		mutableGraphDetails.setGraph(graph);
		when(manager.get(id)).thenReturn(immutableGraphDetails);
		
		DesignGraphDetails actual = controller.getDesignGraphJson(id);
		
		assertEquals(graph, actual.getGraph());
	}
	
	@Test
	public void testGetGraphSvg() throws Exception {
		String id = "some id";
		MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails();
		ImmutableDesignGraphDetails immutableGraphDetails = new ImmutableDesignGraphDetails(
				mutableGraphDetails);
		DesignGraph graph = new DesignHashGraph();
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		
		mutableGraphDetails.setGraph(graph);
		when(manager.get(id)).thenReturn(immutableGraphDetails);
		when(domAdapter.getDomFromGraph(graph)).thenReturn(document);
		
		String actual = controller.getGraphSvg(id);
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", actual);
	}
	
	@Test
	public void testPostVertex() {
		String id = "some id";
		MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails();
		ImmutableDesignGraphDetails immutableGraphDetails = new ImmutableDesignGraphDetails(
				mutableGraphDetails);
		DesignVertex vertex = new DesignVertex("", new Position(5.0, 5.0));
		final MutableObject<MutableDesignGraphDetails> actualGraphDetails = new MutableObject<>();
		
		when(manager.get(id)).thenReturn(immutableGraphDetails);
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				actualGraphDetails.setValue((MutableDesignGraphDetails) 
						invocation.getArguments()[0]);
				return null;
			}
		}).when(manager).save(any(MutableDesignGraphDetails.class));
		
		DesignVertex actual = controller.postVertex(id);
		DesignGraph actualGraph = actualGraphDetails.getValue().getGraph();
		
		assertEquals(vertex, actual);
		assertTrue(actualGraphDetails.getValue().getGraph().containsVertex(1));
		assertEquals(vertex, actualGraph.getVertex(1));
	}
	
	@Test
	public void testPutVertex() {
		String id = "some id";
		Integer vertexId = 1;
		MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails();
		ImmutableDesignGraphDetails immutableGraphDetails = new ImmutableDesignGraphDetails(
				mutableGraphDetails);
		DesignVertex vertex = new DesignVertex("", new Position(0.0, 0.0));
		
		when(manager.get(id)).thenReturn(immutableGraphDetails);
		final MutableObject<MutableDesignGraphDetails> actualGraphDetails = new MutableObject<>();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				actualGraphDetails.setValue((MutableDesignGraphDetails) 
						invocation.getArguments()[0]);
				return null;
			}
		}).when(manager).save(any(MutableDesignGraphDetails.class));
		
		controller.putVertex(id, vertexId, vertex);
		
		DesignGraph actual = actualGraphDetails.getValue().getGraph();
		
		assertTrue(actual.containsVertex(1));
		assertEquals(vertex, actual.getVertex(1));
	}
	
	@Test
	public void testDeleteVertex() {
		String id = "some id";
		Integer vertexId = 1;
		MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails();
		ImmutableDesignGraphDetails immutableGraphDetails = new ImmutableDesignGraphDetails(
				mutableGraphDetails);
		
		mutableGraphDetails.getGraph().putVertex(vertexId, 
				new DesignVertex("", new Position(0.0, 0.0)));
		
		when(manager.get(id)).thenReturn(immutableGraphDetails);
		final MutableObject<MutableDesignGraphDetails> actualGraphDetails = new MutableObject<>();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				actualGraphDetails.setValue((MutableDesignGraphDetails) 
						invocation.getArguments()[0]);
				return null;
			}
		}).when(manager).save(any(MutableDesignGraphDetails.class));
		
		controller.deleteVertex(id, vertexId);
		
		DesignGraph actual = actualGraphDetails.getValue().getGraph();
		assertFalse(actual.containsVertex(vertexId));
	}
	
	@Test
	public void testPostEdge() {
		String id = "some id";
		Integer fromVertexId = 1;
		Integer toVertexId = 2;
		MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails();
		ImmutableDesignGraphDetails immutableGraphDetails = new ImmutableDesignGraphDetails(
				mutableGraphDetails);
		DesignGraph graph = mutableGraphDetails.getGraph();
		
		graph.putVertex(fromVertexId, new DesignVertex("", new Position(0.0, 0.0)));
		graph.putVertex(toVertexId, new DesignVertex("", new Position(0.0, 0.0)));
		
		when(manager.get(id)).thenReturn(immutableGraphDetails);
		final MutableObject<MutableDesignGraphDetails> actualGraphDetails = new MutableObject<>();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				actualGraphDetails.setValue((MutableDesignGraphDetails) 
						invocation.getArguments()[0]);
				return null;
			}
		}).when(manager).save(any(MutableDesignGraphDetails.class));
		
		DesignEdge actual = controller.postEdge(id, fromVertexId, toVertexId);
		DesignGraph actualGraph = actualGraphDetails.getValue().getGraph();
		
		EdgeKey<Integer> edgeKey = new EdgeKey<Integer>(fromVertexId, toVertexId);
		assertTrue(actualGraph.containsEdge(edgeKey));
		assertEquals(new DesignEdge(), actualGraph.getEdge(edgeKey));
		assertEquals(new DesignEdge(), actual);
	}
	
	@Test
	public void testPutEdge() {
		String id = "some id";
		Integer fromVertexId = 1;
		Integer toVertexId = 2;
		EdgeKey<Integer> edgeKey = new EdgeKey<Integer>(fromVertexId, toVertexId);
		MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails();
		ImmutableDesignGraphDetails immutableGraphDetails = new ImmutableDesignGraphDetails(
				mutableGraphDetails);
		DesignGraph graph = mutableGraphDetails.getGraph();
		DesignEdge edge = new DesignEdge();
		
		graph.putVertex(fromVertexId, new DesignVertex("", new Position(0.0, 0.0)));
		graph.putVertex(toVertexId, new DesignVertex("", new Position(0.0, 0.0)));
		
		when(manager.get(id)).thenReturn(immutableGraphDetails);
		final MutableObject<MutableDesignGraphDetails> actualGraphDetails = new MutableObject<>();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				actualGraphDetails.setValue((MutableDesignGraphDetails) 
						invocation.getArguments()[0]);
				return null;
			}
		}).when(manager).save(any(MutableDesignGraphDetails.class));
		
		controller.putEdge(id, fromVertexId, toVertexId, edge);
		
		DesignGraph actualGraph = actualGraphDetails.getValue().getGraph();
		assertTrue(actualGraph.containsEdge(edgeKey));
		assertEquals(edge, actualGraph.getEdge(edgeKey));
	}
	
	@Test
	public void testDeleteEdge() {
		String id ="some id";
		MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails();
		ImmutableDesignGraphDetails immutableGraphDetails = new ImmutableDesignGraphDetails(
				mutableGraphDetails);
		DesignGraph graph = mutableGraphDetails.getGraph();
		Integer fromVertexId = 1;
		Integer toVertexId = 2;
		EdgeKey<Integer> edgeKey = new EdgeKey<Integer>(fromVertexId, toVertexId);
		
		graph.putVertex(fromVertexId, new DesignVertex("", new Position(0.0, 0.0)));
		graph.putVertex(toVertexId, new DesignVertex("", new Position(0.0, 0.0)));
		graph.putEdge(edgeKey, new DesignEdge());
		
		when(manager.get(id)).thenReturn(immutableGraphDetails);
		final MutableObject<MutableDesignGraphDetails> actualGraphDetails = new MutableObject<>();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				actualGraphDetails.setValue((MutableDesignGraphDetails) 
						invocation.getArguments()[0]);
				return null;
			}
		}).when(manager).save(any(MutableDesignGraphDetails.class));
		
		controller.deleteEdge(id, fromVertexId, toVertexId);
		
		DesignGraph actualGraph = actualGraphDetails.getValue().getGraph();
		assertFalse(actualGraph.containsEdge(edgeKey));
	}
	
}
