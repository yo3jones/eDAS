package edu.unlv.cs.edas.graph.design.controller.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.DesignGraphId;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.design.Position;
import edu.unlv.cs.edas.graph.design.adapter.DesignGraphAdapter;
import edu.unlv.cs.edas.graph.design.controller.api.response.CreateResponse;
import edu.unlv.cs.edas.graph.design.dom.DesignGraphDomAdapter;
import edu.unlv.cs.edas.graph.design.dto.DesignEdgeDto;
import edu.unlv.cs.edas.graph.design.dto.DesignGraphDto;
import edu.unlv.cs.edas.graph.design.dto.DesignVertexDto;
import edu.unlv.cs.edas.graph.design.manager.DesignGraphManager;
import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.graph.EdgeKey;

public class DesignGraphApiControllerTest {

	private DesignGraphApiController controller;
	
	private DesignGraphAdapter adapter;
	private DesignGraphManager manager;
	private DesignGraphDomAdapter domAdapter;
	
	private DesignGraph graph;
	private DesignGraphDto graphDto;
	
	@Before
	public void setUp() {
		graph = mock(DesignGraph.class);
		graphDto = mock(DesignGraphDto.class);
		
		adapter = mock(DesignGraphAdapter.class);
		manager = mock(DesignGraphManager.class);
		domAdapter = mock(DesignGraphDomAdapter.class);
		
		controller = new DesignGraphApiController();
		controller.adapter = adapter;
		controller.manager = manager;
		controller.domAdapter = domAdapter;
	}
	
	@Test
	public void testPostGraph() {
		DesignGraphId graphId = new DesignGraphId(1L);
		
		when(manager.addGraph(any(DesignGraph.class))).thenReturn(graphId);
		
		CreateResponse actual = controller.postGraph();
		assertEquals(1L, actual.getId().longValue());
	}
	
	@Test
	public void testPutGraph() {
		Long id = 1L;
		
		when(manager.getGraph(new DesignGraphId(id))).thenReturn(graph);
		
		controller.putGraph(id, graphDto);
		
		verify(adapter).updateGraphFromDto(graph, graphDto);
	}
	
	@Test
	public void testGetGraphJson() {
		Long id = 1L;
		
		when(manager.getGraph(new DesignGraphId(id))).thenReturn(graph);
		when(adapter.createDto(graph)).thenReturn(graphDto);
		
		DesignGraphDto actual = controller.getGraphJson(id);
		
		assertEquals(graphDto, actual);
	}
	
	@Test
	public void testGetGraphSvg() throws Exception {
		Long id = 1L;
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		
		when(manager.getGraph(new DesignGraphId(id))).thenReturn(graph);
		when(domAdapter.getDomFromGraph(graph)).thenReturn(document);
		
		String actual = controller.getGraphSvg(id);
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", actual);
	}
	
	@Test
	public void testPostVertex() {
		Long id = 1L;
		Integer vertexId = 2;
		DesignVertex vertex = new DesignVertex();
		DesignVertexDto vertexDto = mock(DesignVertexDto.class);
		
		vertex.setLabel("");
		vertex.setPosition(new Position(50, 50));
		
		when(manager.getGraph(new DesignGraphId(id))).thenReturn(graph);
		when(graph.getNextVertexId()).thenReturn(vertexId);
		when(adapter.createDtoVertex(vertex)).thenReturn(vertexDto);
		
		DesignVertexDto actual = controller.postVertex(id);
		
		assertEquals(vertexDto, actual);
		verify(graph).putVertex(new Key(2), vertex);
	}
	
	@Test
	public void testPutVertex() {
		Long id = 1L;
		Integer vertexId = 2;
		DesignVertexDto vertexDto = mock(DesignVertexDto.class);
		DesignVertex vertex = mock(DesignVertex.class);
		
		when(adapter.createVertex(vertexDto)).thenReturn(vertex);
		when(manager.getGraph(new DesignGraphId(id))).thenReturn(graph);
		
		controller.putVertex(id, vertexId, vertexDto);
		
		verify(graph).putVertex(new Key(vertexId), vertex);
	}
	
	@Test
	public void testDeleteVertex() {
		Long id = 1L;
		Integer vertexId = 2;
		
		when(manager.getGraph(new DesignGraphId(id))).thenReturn(graph);
		
		controller.deleteVertex(id, vertexId);
		
		verify(graph).removeVertex(new Key(vertexId));
	}
	
	@Test
	public void testPostEdge() {
		Long id = 1L;
		Integer fromVertexId = 2;
		Integer toVertexId = 3;
		DesignEdgeDto edgeDto = mock(DesignEdgeDto.class);
		DesignEdge edge = new DesignEdge();
		
		when(manager.getGraph(new DesignGraphId(id))).thenReturn(graph);
		when(adapter.createDtoEdge(edge)).thenReturn(edgeDto);
		
		DesignEdgeDto actual = controller.postEdge(id, fromVertexId, toVertexId);
		
		assertEquals(edgeDto, actual);
		verify(graph).putEdge(new EdgeKey<Key>(new Key(fromVertexId), new Key(toVertexId)), edge);
	}
	
	@Test
	public void testDeleteEdge() {
		Long id = 1L;
		Integer fromVertexId = 2;
		Integer toVertexId = 3;
		
		when(manager.getGraph(new DesignGraphId(id))).thenReturn(graph);
		
		controller.deleteEdge(id, fromVertexId, toVertexId);
		
		verify(graph).removeEdge(new EdgeKey<Key>(new Key(fromVertexId), new Key(toVertexId)));
	}
	
}
